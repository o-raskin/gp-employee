package ru.olegraskin.suskills.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.olegraskin.suskills.domain.Grade;
import ru.olegraskin.suskills.domain.User;
import ru.olegraskin.suskills.dto.GradeDto;
import ru.olegraskin.suskills.dto.UserDto;
import ru.olegraskin.suskills.mapper.GradeMapper;
import ru.olegraskin.suskills.mapper.UserMapper;
import ru.olegraskin.suskills.service.GradeService;
import ru.olegraskin.suskills.service.UserService;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(
        value = "/grades",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class GradeController {

    private final GradeService gradeService;
    private final GradeMapper gradeMapper;

    @GetMapping
    public Set<GradeDto> getGrades() {
        Set<Grade> grades = gradeService.getAll();
        return grades.stream()
                .map(gradeMapper::entityToDto)
                .collect(Collectors.toSet());
    }

}
