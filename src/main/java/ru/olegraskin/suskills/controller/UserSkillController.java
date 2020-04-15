package ru.olegraskin.suskills.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.olegraskin.suskills.domain.UserSkill;
import ru.olegraskin.suskills.dto.UserDto;
import ru.olegraskin.suskills.dto.UserSkillDto;
import ru.olegraskin.suskills.mapper.UserMapper;
import ru.olegraskin.suskills.mapper.UserSkillMapper;
import ru.olegraskin.suskills.service.UserSkillService;

@RestController
@RequestMapping(
        value = "/users/{userId}/skill/{skillId}",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class UserSkillController {

    private final UserSkillService userSkillService;
    private final UserSkillMapper userSkillMapper;

    @GetMapping
    public UserSkillDto getUserDataAboutSkill(@PathVariable("userId") Long userId,
                                         @PathVariable("skillId") Long skillId) {
        UserSkill userSkill = userSkillService.getUserSkillData(userId, skillId);
        return userSkillMapper.entityToDto(userSkill);
    }

}
