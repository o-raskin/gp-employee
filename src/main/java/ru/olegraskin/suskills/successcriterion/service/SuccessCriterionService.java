package ru.olegraskin.suskills.successcriterion.service;

import ru.olegraskin.suskills.successcriterion.domain.SuccessCriterion;

import java.util.Set;

public interface SuccessCriterionService {

    SuccessCriterion update(SuccessCriterion sc);

    Set<SuccessCriterion> getAllBySkillId(Long skillId);

    SuccessCriterion getById(Long id);
}
