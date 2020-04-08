package ru.olegraskin.suskills.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {

    private Long id;

    private GradeDto grade;

    private int gradeProgress;

    private Set<UserSkillDto> skills = new HashSet<>();

    private Set<UserSuccessCriterionDto> successCriteria = new HashSet<>();

    private Set<UserSkillDto> todoSkills = new HashSet<>();
}
