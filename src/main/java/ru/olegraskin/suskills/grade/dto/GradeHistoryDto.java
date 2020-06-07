package ru.olegraskin.suskills.grade.dto;

import lombok.Data;
import ru.olegraskin.suskills.grade.domain.Grade;

import java.time.LocalDate;

@Data
public class GradeHistoryDto {

    private GradeDto grade;

    private Long userId;

    private LocalDate achievedDate;
}
