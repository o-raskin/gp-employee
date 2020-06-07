package ru.olegraskin.suskills.grade.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.olegraskin.suskills.grade.domain.GradeHistoryItem;
import ru.olegraskin.suskills.grade.dto.GradeHistoryDto;
import ru.olegraskin.suskills.grade.mapper.GradeHistoryMapper;
import ru.olegraskin.suskills.grade.service.GradeHistoryService;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(
        value = "/grades/history/{userId}",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class GradeHistoryController {

    private final GradeHistoryService historyService;
    private final GradeHistoryMapper historyMapper;

    @GetMapping
    public Set<GradeHistoryDto> getHistoryForUser(@PathVariable("userId") Long userId) {
        Set<GradeHistoryItem> grades = historyService.getAllByUserId(userId);
        return grades.stream()
                .map(historyMapper::entityToDto)
                .collect(Collectors.toSet());
    }

}
