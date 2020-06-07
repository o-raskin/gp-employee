package ru.olegraskin.suskills.successcriterion.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SuccessCriterionDto {

    private Long id;

    @Size(min = 3, max = 250)
    @NotNull
    private String name;

    @Size(max = 1000)
    private String description;
}
