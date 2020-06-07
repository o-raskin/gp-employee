package ru.olegraskin.suskills.usersuccesscriterion.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class UserSuccessCriterionDto {

    @NotNull
    private Long userId;

    @NotNull
    private Long successCriterionId;

    private LocalDate finishDate;

    private boolean achieved;
}
