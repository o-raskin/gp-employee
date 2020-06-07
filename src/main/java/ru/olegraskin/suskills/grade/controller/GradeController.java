package ru.olegraskin.suskills.grade.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.olegraskin.suskills.grade.domain.Grade;
import ru.olegraskin.suskills.grade.dto.GradeDto;
import ru.olegraskin.suskills.grade.mapper.GradeMapper;
import ru.olegraskin.suskills.grade.service.GradeService;
import ru.olegraskin.suskills.user.service.UserService;

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
    private final UserService userService;
    private final GradeMapper gradeMapper;

    @GetMapping
    public Set<GradeDto> getGrades() {
        Set<Grade> grades = gradeService.getAll();
        return grades.stream()
                .map(gradeMapper::entityToDto)
                .collect(Collectors.toSet());
    }

    @GetMapping("/{id}")
    public GradeDto getGradeById(@PathVariable("id") long id) {
        Grade grade = gradeService.getById(id);
        return gradeMapper.entityToDto(grade);
    }

    @GetMapping("/user/{userId}")
    public Set<GradeDto> getUserGrades(@PathVariable("userId") Long userId) {
        Set<Grade> grades = userService.getAllUserGrades(userId);
        return grades.stream()
                .map(gradeMapper::entityToDto)
                .collect(Collectors.toSet());
    }

}
