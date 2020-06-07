package ru.olegraskin.suskills.skill.dto;

import lombok.Data;

@Data
public class SimpleSkillDto {

    private Long id;

    private Long previousGradeId;

    private String name;

    private String description;

}
