package ru.olegraskin.suskills.userskill.dto;

import lombok.Data;
import ru.olegraskin.suskills.userskill.domain.UserSkill;
import ru.olegraskin.suskills.skill.dto.SimpleSkillDto;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class UserSkillDto {

    @NotNull
    private Long userId;

    @NotNull
    private SimpleSkillDto skill;

    private Long editorId;

    private LocalDate lastEditDate;

    @NotNull
    private LocalDate startDate;

    private LocalDate endDate;

    @NotNull
    private UserSkill.Status status;
}
