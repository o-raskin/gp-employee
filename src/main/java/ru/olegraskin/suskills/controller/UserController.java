package ru.olegraskin.suskills.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.olegraskin.suskills.domain.Skill;
import ru.olegraskin.suskills.domain.User;
import ru.olegraskin.suskills.dto.GradeDto;
import ru.olegraskin.suskills.dto.SkillDto;
import ru.olegraskin.suskills.dto.UserDto;
import ru.olegraskin.suskills.mapper.SkillMapper;
import ru.olegraskin.suskills.mapper.UserMapper;
import ru.olegraskin.suskills.service.SkillService;
import ru.olegraskin.suskills.service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(
        value = "/users",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/{id}")
    public UserDto getGradeByUserId(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        return userMapper.entityToDto(user);
    }

}
