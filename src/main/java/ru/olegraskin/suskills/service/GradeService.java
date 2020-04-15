package ru.olegraskin.suskills.service;

import ru.olegraskin.suskills.domain.Grade;
import ru.olegraskin.suskills.domain.Skill;

import java.util.Set;

public interface GradeService {

    /**
     * Check that skill is learned for grade
     * @param grade
     * @param skill
     * @return check result
     */
    boolean isAlreadyKnownSkill(Grade grade, Skill skill);

    Set<Grade> getAll();
}
