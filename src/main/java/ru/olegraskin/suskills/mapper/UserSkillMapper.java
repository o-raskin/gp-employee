package ru.olegraskin.suskills.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.olegraskin.suskills.domain.User;
import ru.olegraskin.suskills.domain.UserSkill;
import ru.olegraskin.suskills.dto.UserDto;
import ru.olegraskin.suskills.dto.UserSkillDto;

@Component
@RequiredArgsConstructor
public class UserSkillMapper {

    private final ModelMapper modelMapper;

    public UserSkillDto entityToDto(UserSkill entity) {
        return modelMapper.map(entity, UserSkillDto.class);
    }
}
