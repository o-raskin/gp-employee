package ru.olegraskin.suskills.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.olegraskin.suskills.domain.*;
import ru.olegraskin.suskills.exception.BadRequestException;
import ru.olegraskin.suskills.repository.UserSkillRepository;
import ru.olegraskin.suskills.service.GradeService;
import ru.olegraskin.suskills.service.UserSkillService;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserSkillServiceImpl implements UserSkillService {

    private final UserSkillRepository userSkillRepository;
    private final GradeService gradeService;

    @Override
    public UserSkill getUserSkillData(@NonNull User user, @NonNull Skill skill) {
        return userSkillRepository.findUserSkillByUserAndSkill(user, skill)
                .orElseGet(() -> this.save(user, skill));
    }

    public UserSkill save(@NonNull User user, @NonNull Skill skill) {

        UserSkill us = new UserSkill(user, skill);

        us.setStatus(computeSkillStatus(us));

        return userSkillRepository.save(us);
    }

    private UserSkill.Status computeSkillStatus(@NonNull UserSkill us) {

        Grade grade = us.getUser().getGrade();
        Skill skill = us.getSkill();

        if (grade != null) {
            if (grade.getSkills().contains(skill)) {
                return UserSkill.Status.NEED_TO_KNOW;
            }
            if (gradeService.isAlreadyKnownSkill(grade, skill)) {
                return UserSkill.Status.APPROVED;
            }
        }

        return UserSkill.Status.NOT_MANDATORY;
    }

    private void checkAllSuccessCriteriaIsAchieved(UserSkill skill) {

        Set<SuccessCriterion> skillSuccessCriteria = skill.getSkill().getSuccessCriteria();

        skill.getUser().getSuccessCriteria().stream()
                .filter(usc -> skillSuccessCriteria.contains(usc.getSuccessCriterion()))
                .forEach(usc -> {
                    if (!usc.isAchieved()) {
                        throw new BadRequestException("Skill cannot be completed, while all success criterion is not " +
                                "achieved");
                    }
                });
    }
}
