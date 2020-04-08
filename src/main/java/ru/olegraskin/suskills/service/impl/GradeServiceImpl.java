package ru.olegraskin.suskills.service.impl;

import ru.olegraskin.suskills.domain.Grade;
import ru.olegraskin.suskills.domain.Skill;
import ru.olegraskin.suskills.service.GradeService;

public class GradeServiceImpl implements GradeService {


    @Override
    public boolean isAlreadyKnownSkill(Grade grade, Skill skill) {

        Grade prevGrade = grade.getPreviousGrade();

        while (prevGrade != null) {
            if (prevGrade.getSkills().contains(skill)) {
                return true;
            } else {
                prevGrade = prevGrade.getPreviousGrade();
            }
        }

        return false;
    }
}
