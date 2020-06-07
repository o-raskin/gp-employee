package ru.olegraskin.suskills.user.dto;

import lombok.Data;
import ru.olegraskin.suskills.userskill.dto.UserSkillDto;
import ru.olegraskin.suskills.usersuccesscriterion.dto.UserSuccessCriterionDto;
import ru.olegraskin.suskills.grade.dto.GradeDto;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {

    private Long id;

    private GradeDto grade;

    private int gradeProgress;

    private Set<UserSkillDto> skills = new HashSet<>();

    private Set<UserSuccessCriterionDto> successCriteria = new HashSet<>();
}
