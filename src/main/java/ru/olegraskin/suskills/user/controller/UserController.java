package ru.olegraskin.suskills.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.olegraskin.suskills.user.domain.User;
import ru.olegraskin.suskills.user.dto.UserDto;
import ru.olegraskin.suskills.user.mapper.UserMapper;
import ru.olegraskin.suskills.usersuccesscriterion.mapper.UserSuccessCriterionMapper;
import ru.olegraskin.suskills.user.service.UserService;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
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
    public UserDto getUserById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        return userMapper.entityToDto(user);
    }

    @PostMapping
    public Set<UserDto> getAllByIds(@RequestBody Set<Long> ids) {
        return userService.getAllByIds(ids).stream()
                .map(userMapper::entityToDto)
                .collect(Collectors.toSet());
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable("id") Long id,
                          @RequestBody UserDto userDto) {
        userDto.setId(id);
        User receivedUser = userMapper.dtoToEntity(userDto);
        User updatedUser = this.userService.update(receivedUser);
        return userMapper.entityToDto(updatedUser);
    }

}
