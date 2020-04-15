package ru.olegraskin.suskills.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.olegraskin.suskills.domain.Grade;
import ru.olegraskin.suskills.domain.User;
import ru.olegraskin.suskills.dto.GradeDto;
import ru.olegraskin.suskills.dto.UserDto;
import ru.olegraskin.suskills.mapper.GradeMapper;
import ru.olegraskin.suskills.mapper.UserMapper;
import ru.olegraskin.suskills.service.GradeService;
import ru.olegraskin.suskills.service.UserService;

@RestController
@RequestMapping(
        value = "/users/{userId}/grade",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class UserGradeController {

    private final UserService userService;
    private final GradeService gradeService;
    private final UserMapper userMapper;
    private final GradeMapper gradeMapper;

    @GetMapping
    public GradeDto getGradeByUserId(@PathVariable("userId") Long userId) {
        User user = userService.getUserById(userId);
        return gradeMapper.entityToDto(user.getGrade());
    }

}
