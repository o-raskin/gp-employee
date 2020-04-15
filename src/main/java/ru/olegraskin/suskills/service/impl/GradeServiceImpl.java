package ru.olegraskin.suskills.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.olegraskin.suskills.domain.Grade;
import ru.olegraskin.suskills.domain.Skill;
import ru.olegraskin.suskills.repository.GradeRepository;
import ru.olegraskin.suskills.service.GradeService;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;

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

    @Override
    public Set<Grade> getAll() {
        return new HashSet<>(gradeRepository.findAll());
    }
}
