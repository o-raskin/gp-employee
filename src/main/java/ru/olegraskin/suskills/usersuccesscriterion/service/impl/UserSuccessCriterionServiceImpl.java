package ru.olegraskin.suskills.usersuccesscriterion.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.olegraskin.suskills.usersuccesscriterion.repository.UserSuccessCriterionRepository;
import ru.olegraskin.suskills.successcriterion.service.SuccessCriterionService;
import ru.olegraskin.suskills.user.service.UserService;
import ru.olegraskin.suskills.usersuccesscriterion.service.UserSuccessCriterionConditionsChecker;
import ru.olegraskin.suskills.usersuccesscriterion.service.UserSuccessCriterionService;
import ru.olegraskin.suskills.skill.domain.Skill;
import ru.olegraskin.suskills.successcriterion.domain.SuccessCriterion;
import ru.olegraskin.suskills.user.domain.User;
import ru.olegraskin.suskills.usersuccesscriterion.domain.UserSuccessCriterion;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserSuccessCriterionServiceImpl implements UserSuccessCriterionService {

    private final UserSuccessCriterionRepository userSuccessCriterionRepository;
    private final SuccessCriterionService successCriterionService;
    private final UserSuccessCriterionConditionsChecker userSuccessCriterionChecker;
    private final UserService userService;

    @Override
    public UserSuccessCriterion getByUserIdAndSuccessCriterionId(Long userId, Long scId) {
        Optional<UserSuccessCriterion> userSuccessCriterion =
                userSuccessCriterionRepository.findByUserIdAndSuccessCriterionId(userId, scId);
        return userSuccessCriterion.orElse(this.save(userId, scId));
    }

    @Override
    public UserSuccessCriterion getByUserIdAndSuccessCriterionId(User user, SuccessCriterion successCriterion) {
        return this.getByUserIdAndSuccessCriterionId(user.getId(), successCriterion.getId());
    }

    @Transactional
    @Override
    public UserSuccessCriterion update(UserSuccessCriterion userSuccessCriterion) {
        Optional<UserSuccessCriterion> optionalUserSuccessCriterion =
                userSuccessCriterionRepository.findByUserIdAndSuccessCriterionId(
                        userSuccessCriterion.getId().getUserId(),
                        userSuccessCriterion.getId().getSuccessCriterionId());
        if (!optionalUserSuccessCriterion.isPresent()) {
            return this.userSuccessCriterionRepository.save(userSuccessCriterion);
        }

        Skill skill = optionalUserSuccessCriterion.get().getSuccessCriterion().getSkill();
        User user = userSuccessCriterion.getUser();
        userSuccessCriterionChecker.checkChangeSkillConditions(skill, user);

        UserSuccessCriterion stored = optionalUserSuccessCriterion.get();
        stored.setAchieved(userSuccessCriterion.isAchieved());
        stored.setFinishDate(userSuccessCriterion.getFinishDate());
        return this.userSuccessCriterionRepository.save(stored);
    }

    @Transactional
    @Override
    public UserSuccessCriterion save(Long userId, Long scId) {
        User user = userService.getUserById(userId);
        SuccessCriterion successCriterion = successCriterionService.getById(scId);
        UserSuccessCriterion usc = new UserSuccessCriterion(user, successCriterion);
        return userSuccessCriterionRepository.save(usc);
    }

    @Override
    public Set<UserSuccessCriterion> getAllByUserAndSkill(Long userId, Long skillId) {

        Set<SuccessCriterion> skillSuccessCriteria = successCriterionService.getAllBySkillId(skillId);

        Set<UserSuccessCriterion> userSuccessCriteria =
                userSuccessCriterionRepository.findAllByUserIdAndSuccessCriterionIn(userId, skillSuccessCriteria);

        if (userSuccessCriteria.size() != skillSuccessCriteria.size()) {

            Set<Long> skillSuccessCriteriaIds = skillSuccessCriteria.stream()
                    .map(SuccessCriterion::getId)
                    .collect(Collectors.toSet());

            Set<Long> userSuccessCriteriaIds = userSuccessCriteria.stream()
                    .map(usc -> usc.getSuccessCriterion().getId())
                    .collect(Collectors.toSet());

            skillSuccessCriteriaIds.removeAll(userSuccessCriteriaIds);

            for (Long scId : skillSuccessCriteriaIds) {
                UserSuccessCriterion usc = this.save(userId, scId);
                userSuccessCriteria.add(usc);
            }
        }

        return userSuccessCriteria;
    }


}
