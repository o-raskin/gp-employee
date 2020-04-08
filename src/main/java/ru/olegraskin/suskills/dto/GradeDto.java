package ru.olegraskin.suskills.dto;

import lombok.Data;
import ru.olegraskin.suskills.domain.Skill;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class GradeDto {

    private Long id;

    private Long positionId;

    @NotNull
    @Size(min = 3, max = 250)
    private String name;

    @Size(max = 1000)
    private String description;

    private int gradeProgress;

    private Set<Skill> skills = new HashSet<>();
}
