package ru.olegraskin.suskills.usersuccesscriterion.service;

import ru.olegraskin.suskills.successcriterion.domain.SuccessCriterion;
import ru.olegraskin.suskills.user.domain.User;
import ru.olegraskin.suskills.usersuccesscriterion.domain.UserSuccessCriterion;

import java.util.Set;

public interface UserSuccessCriterionService {

    UserSuccessCriterion getByUserIdAndSuccessCriterionId(Long userId, Long scId);

    UserSuccessCriterion getByUserIdAndSuccessCriterionId(User user, SuccessCriterion successCriterion);

    UserSuccessCriterion update(UserSuccessCriterion userSuccessCriterion);

    UserSuccessCriterion save(Long userId, Long scId);

    Set<UserSuccessCriterion> getAllByUserAndSkill(Long userId, Long skillId);
}
