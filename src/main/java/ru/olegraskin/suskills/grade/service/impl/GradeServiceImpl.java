package ru.olegraskin.suskills.grade.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.olegraskin.suskills.exception.ResourceNotFoundException;
import ru.olegraskin.suskills.grade.domain.Grade;
import ru.olegraskin.suskills.skill.domain.Skill;
import ru.olegraskin.suskills.grade.repository.GradeRepository;
import ru.olegraskin.suskills.grade.service.GradeService;
import ru.olegraskin.suskills.user.domain.User;
import ru.olegraskin.suskills.user.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;

    @Override
    public boolean isAlreadyKnownSkill(Grade originGrade, Skill skill) {

        Grade grade = originGrade;
        while (grade != null) {
            if (grade.getSkills().contains(skill)) {
                return true;
            } else {
                grade = grade.getPreviousGrade();
            }
        }
        return false;
    }

    @Override
    public Set<Grade> getAll() {
        return new HashSet<>(gradeRepository.findAll());
    }

    @Override
    public Grade getById(long id) {
        return gradeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Grade", "id", id));
    }

    @Override
    public Set<Skill> getAllSkillsNeedToKnowForGrade(Grade originGrade) {

        Set<Skill> skills = new HashSet<>();

        Grade grade = originGrade;
        while (grade != null) {
            skills.addAll(grade.getSkills());
            grade = grade.getPreviousGrade();
        }

        return skills;
    }
}
