package ru.olegraskin.suskills.usersuccesscriterion.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.olegraskin.suskills.exception.BadRequestException;
import ru.olegraskin.suskills.skill.domain.Skill;
import ru.olegraskin.suskills.user.domain.User;
import ru.olegraskin.suskills.userskill.domain.UserSkill;
import ru.olegraskin.suskills.userskill.service.UserSkillService;
import ru.olegraskin.suskills.usersuccesscriterion.service.UserSuccessCriterionConditionsChecker;

@Component
@RequiredArgsConstructor
public class UserSuccessCriterionConditionsCheckerImpl implements UserSuccessCriterionConditionsChecker {

    private final UserSkillService userSkillService;

    @Override
    public void checkChangeSkillConditions(Skill skill, User user) {
        UserSkill userskill = userSkillService.getUserSkillData(user.getId(), skill.getId());
        UserSkill.Status status = userskill.getStatus();
        if (status.equals(UserSkill.Status.APPROVED) || status.equals(UserSkill.Status.PENDING)) {
            throw new BadRequestException("Skill status is not allowed to change success criterion!");
        }
    }
}
