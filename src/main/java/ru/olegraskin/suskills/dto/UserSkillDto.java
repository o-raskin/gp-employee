package ru.olegraskin.suskills.dto;

import lombok.Data;
import ru.olegraskin.suskills.domain.UserSkill;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class UserSkillDto {

    @NotNull
    private Long userId;

    @NotNull
    private Long skillId;

    @NotNull
    private LocalDate startDate;

    private LocalDate endDate;

    @NotNull
    private UserSkill.Status status;
}
