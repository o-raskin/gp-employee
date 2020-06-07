package ru.olegraskin.suskills.grade.service;

import ru.olegraskin.suskills.grade.domain.Grade;
import ru.olegraskin.suskills.skill.domain.Skill;

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

    Grade getById(long id);

    /**
     * Get all skills that must be learned by user on specified grade.
     * @param grade
     * @return skills
     */
    Set<Skill> getAllSkillsNeedToKnowForGrade(Grade grade);

}
