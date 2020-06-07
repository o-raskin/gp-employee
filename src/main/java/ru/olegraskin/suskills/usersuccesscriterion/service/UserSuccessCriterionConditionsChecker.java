package ru.olegraskin.suskills.usersuccesscriterion.service;

import ru.olegraskin.suskills.skill.domain.Skill;
import ru.olegraskin.suskills.user.domain.User;

public interface UserSuccessCriterionConditionsChecker {

    void checkChangeSkillConditions(Skill skill, User user);
}
