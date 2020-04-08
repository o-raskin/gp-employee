package ru.olegraskin.suskills.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.olegraskin.suskills.domain.User;
import ru.olegraskin.suskills.dto.UserDto;
import ru.olegraskin.suskills.mapper.UserMapper;
import ru.olegraskin.suskills.service.UserService;

@RestController
@RequestMapping(
        value = "/users/{userId}/skill/{skillId}",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class UserSkillController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public UserDto getGradeByUserId(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        return userMapper.entityToDto(user);
    }

    @GetMapping("/{userId}/skill/{skillId}")
    public UserDto getUserDataAboutSkill(@PathVariable("userId") Long userId,
                                         @PathVariable("skillId") Long skillId) {
        User user = userService.getUserById(userId);
        return userMapper.entityToDto(user);
    }

}
