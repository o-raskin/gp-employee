package ru.olegraskin.suskills.userskill.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.olegraskin.suskills.exception.BadRequestException;
import ru.olegraskin.suskills.successcriterion.domain.SuccessCriterion;
import ru.olegraskin.suskills.userskill.domain.UserSkill;
import ru.olegraskin.suskills.usersuccesscriterion.repository.UserSuccessCriterionRepository;
import ru.olegraskin.suskills.usersuccesscriterion.service.UserSuccessCriterionService;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserSkillConditionsCheckerImpl {

    private final UserSuccessCriterionService userSuccessCriterionService;
    private final UserSuccessCriterionRepository userSuccessCriterionRepository;

    /**
     * Check that all success criteria of skill is completed.
     *
     * @param userSkill
     */
    private void checkConditionsForPending(UserSkill userSkill) {

        UserSkill.Status status = userSkill.getStatus();
        if (!status.equals(UserSkill.Status.PENDING) && !userSkill.getStatus().equals(UserSkill.Status.PENDING)) {
            return;
        }

        Set<SuccessCriterion> skillSuccessCriteria = userSkill.getSkill().getSuccessCriteria();

        userSkill.getUser().getSuccessCriteria().stream()
                .filter(usc -> skillSuccessCriteria.contains(usc.getSuccessCriterion()))
                .forEach(usc -> {
                    if (!usc.isAchieved()) {
                        throw new BadRequestException("Skill cannot be completed, while all success criterion is not " +
                                "achieved");
                    }
                });
    }
}
