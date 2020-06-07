package ru.olegraskin.suskills.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.olegraskin.suskills.user.domain.User;
import ru.olegraskin.suskills.grade.dto.GradeDto;
import ru.olegraskin.suskills.grade.mapper.GradeMapper;
import ru.olegraskin.suskills.user.dto.UserDto;
import ru.olegraskin.suskills.user.mapper.UserMapper;
import ru.olegraskin.suskills.user.service.UserService;

@RestController
@RequestMapping(
        value = "/users/{userId}/grade",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class UserGradeController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public UserDto raiseUserGrade(@PathVariable("userId") Long userId) {
        User user = userService.raiseUserGrade(userId);
        return userMapper.entityToDto(user);
    }

}
