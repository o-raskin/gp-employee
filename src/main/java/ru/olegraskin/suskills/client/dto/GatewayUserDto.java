package ru.olegraskin.suskills.client.dto;

import lombok.Data;

@Data
public class GatewayUserDto {

    private Long id;

    private String role;

    private Long positionId;

    private Long mentorId;

    private int gradeProgress;

    public GatewayUserDto(Long id) {
        this.id = id;
    }
}
