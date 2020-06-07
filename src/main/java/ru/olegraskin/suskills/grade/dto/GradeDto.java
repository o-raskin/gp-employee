package ru.olegraskin.suskills.grade.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class GradeDto {

    private Long id;

    private Long positionId;

    private Long previousGradeId;

    private Long nextGradeId;

    @NotNull
    @Size(min = 3, max = 250)
    private String name;

    @Size(max = 1000)
    private String description;
}
