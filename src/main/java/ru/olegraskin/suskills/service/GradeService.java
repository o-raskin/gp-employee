package ru.olegraskin.suskills.service;

import ru.olegraskin.suskills.domain.Grade;
import ru.olegraskin.suskills.domain.Skill;

public interface GradeService {

    /**
     * Check that skill is learned for grade
     * @param grade
     * @param skill
     * @return check result
     */
    boolean isAlreadyKnownSkill(Grade grade, Skill skill);
}
