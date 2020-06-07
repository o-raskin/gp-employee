package ru.olegraskin.suskills.skill.dto;

import lombok.Data;
import ru.olegraskin.suskills.successcriterion.dto.SuccessCriterionDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class SkillDto {

    private Long id;

    private Long parentId;

    private Long editorId;

    private Long previousGradeId;

    private Long gradeId;

    @NotNull
    @Size(min = 3, max = 250)
    private String name;

    @Size(max = 1000)
    private String description;

    private Set<SuccessCriterionDto> successCriteria;


}
