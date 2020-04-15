package ru.olegraskin.suskills.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
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
    private final ObjectMapper objectMapper;

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        return userMapper.entityToDto(user);
    }

    @PatchMapping("/{id}")
    public UserDto updateUserWithPatch(@PathVariable Long id, @RequestBody JsonPatch patch)
            throws JsonPatchException, JsonProcessingException {
        User user = userService.getUserById(id);
        JsonNode patchedNode = patch.apply(objectMapper.convertValue(user, JsonNode.class));
        User patchedUser = objectMapper.treeToValue(patchedNode, User.class);
        User updatedUser = userService.update(patchedUser);
        return userMapper.entityToDto(updatedUser);
    }

}
