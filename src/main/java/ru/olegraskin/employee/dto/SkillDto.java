package ru.olegraskin.employee.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Data
public class SkillDto {

    private Long id;

    @NotNull
    @Size(min = 3, max = 250)
    private String name;

    @Size(max = 1000)
    private String description;

    private Long parentId;

    @NotNull
    private LocalDate startDate;

    private LocalDate endDate;

    private String status;

    private Set<SuccessCriterionDto> successCriteria;
}
