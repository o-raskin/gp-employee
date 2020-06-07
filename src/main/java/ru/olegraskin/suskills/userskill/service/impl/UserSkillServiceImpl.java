package ru.olegraskin.suskills.userskill.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.olegraskin.suskills.exception.BadRequestException;
import ru.olegraskin.suskills.grade.domain.Grade;
import ru.olegraskin.suskills.userskill.repository.UserSkillRepository;
import ru.olegraskin.suskills.grade.service.GradeService;
import ru.olegraskin.suskills.skill.service.SkillService;
import ru.olegraskin.suskills.user.service.UserService;
import ru.olegraskin.suskills.userskill.service.UserSkillService;
import ru.olegraskin.suskills.skill.domain.Skill;
import ru.olegraskin.suskills.successcriterion.domain.SuccessCriterion;
import ru.olegraskin.suskills.user.domain.User;
import ru.olegraskin.suskills.userskill.domain.UserSkill;
import ru.olegraskin.suskills.usersuccesscriterion.domain.UserSuccessCriterion;
import ru.olegraskin.suskills.usersuccesscriterion.repository.UserSuccessCriterionRepository;
import ru.olegraskin.suskills.usersuccesscriterion.service.UserSuccessCriterionService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserSkillServiceImpl implements UserSkillService {

    private final UserSkillRepository userSkillRepository;
    private final UserSuccessCriterionRepository userSuccessCriterionRepository;
    private final UserService userService;
    private final SkillService skillService;
    private final GradeService gradeService;

    @Override
    public UserSkill getUserSkillData(@NonNull Long userId, @NonNull Long skillId) {
        User user = userService.getUserById(userId);
        Skill skill = skillService.getById(skillId);
        return userSkillRepository.findUserSkillByUserAndSkill(user, skill)
                .orElseGet(() -> this.save(user, skill));
    }

    @Override
    public UserSkill getUserSkillData(@NonNull User user, @NonNull Skill skill) {
        return userSkillRepository.findUserSkillByUserAndSkill(user, skill)
                .orElseGet(() -> this.save(user, skill));
    }

    @Transactional
    @Override
    public UserSkill update(Long userId, Long skillId, UserSkill userSkill) {
        User user = userService.getUserById(userId);
        Skill skill = skillService.getById(skillId);
        Optional<UserSkill> optionalUserSkill = userSkillRepository.findUserSkillByUserAndSkill(user, skill);
        if (!optionalUserSkill.isPresent()) {
            return userSkillRepository.save(userSkill);
        }
        UserSkill storedUserSkill = optionalUserSkill.get();

        storedUserSkill.setEndDate(userSkill.getEndDate());
        storedUserSkill.setStartDate(userSkill.getStartDate());
        storedUserSkill.setEditorId(userSkill.getEditorId());
        storedUserSkill.setLastEditDate(userSkill.getLastEditDate());
        storedUserSkill.setStatus(userSkill.getStatus());

        computeChildrenStatus(userSkill);
//        checkConditionsForPending(userSkill);

//        UserSkill.Status computedStatus = computeSkillStatus(storedUserSkill);
        storedUserSkill.setStatus(userSkill.getStatus());

        return userSkillRepository.save(storedUserSkill);
    }

    @Override
    public Set<UserSkill> getAllSkillsByUserId(Long userId) {
        return userSkillRepository.findAllByUserId(userId);
    }

    @Transactional
    public UserSkill save(@NonNull User user, @NonNull Skill skill) {

        UserSkill us = new UserSkill(user, skill);

        us.setStatus(computeSkillStatus(us));

        return userSkillRepository.save(us);
    }

    private UserSkill.Status computeSkillStatus(@NonNull UserSkill us) {

        if (us.getStatus().equals(UserSkill.Status.PENDING) ||
                us.getStatus().equals(UserSkill.Status.IN_PROGRESS)) {
            return us.getStatus();
        }

        // SYSTEM EDITOR
        us.setEditorId(0L);
        us.setLastEditDate(LocalDate.now());

        Grade grade = us.getUser().getGrade();
        Skill skill = us.getSkill();

        if (grade != null) {

            Grade nextGrade = grade.getNextGrade();
            if (nextGrade != null && nextGrade.getSkills().contains(skill)) {
                return UserSkill.Status.NEED_TO_KNOW;
            }

            if (gradeService.isAlreadyKnownSkill(grade, skill)) {
                us.setStartDate(LocalDate.now());
                us.setEndDate(LocalDate.now());
                return UserSkill.Status.APPROVED;
            }
        }

        return UserSkill.Status.NOT_MANDATORY;
    }

    /**
     * Complete all children skills and success criteria for such statuses as: 'APPROVED', 'PENDING'
     * @param userSkill
     */
    private void computeChildrenStatus(UserSkill userSkill) {

        UserSkill.Status status = userSkill.getStatus();
        if (!status.equals(UserSkill.Status.APPROVED) && !status.equals(UserSkill.Status.PENDING)) {
            return;
        }

        User user = userSkill.getUser();
        Skill skill = userSkill.getSkill();

        Set<Skill> childrenSkills = skill.getChildren();
        for (Skill childSkill : childrenSkills) {

            UserSkill childUserSkill = this.getUserSkillData(user, childSkill);
            childUserSkill.setStatus(status);
            childUserSkill.setEditorId(userSkill.getEditorId());
            childUserSkill.setLastEditDate(userSkill.getLastEditDate());
            childUserSkill.setEndDate(userSkill.getEndDate());

            LocalDate childSkillStartDate = childUserSkill.getStartDate();
            if (childSkillStartDate == null) {
                childUserSkill.setStartDate(userSkill.getEndDate());
            }

            userSkillRepository.save(childUserSkill);

            Set<SuccessCriterion> skillSuccessCriteria = childUserSkill.getSkill().getSuccessCriteria();
            for (SuccessCriterion skillSuccessCriterion : skillSuccessCriteria) {

                Optional<UserSuccessCriterion> optionalUserSuccessCriterion = userSuccessCriterionRepository
                        .findByUserIdAndSuccessCriterionId(user.getId(), skillSuccessCriterion.getId());
                UserSuccessCriterion userSuccessCriterion = optionalUserSuccessCriterion
                        .orElseGet(UserSuccessCriterion::new);
                userSuccessCriterion.setAchieved(true);
                userSuccessCriterion.setFinishDate(userSkill.getEndDate());
                userSuccessCriterionRepository.save(userSuccessCriterion);
            }
        }
    }
}
