package ru.olegraskin.suskills.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.olegraskin.suskills.domain.User;
import ru.olegraskin.suskills.dto.UserDto;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserDto entityToDto(User entity) {
        return modelMapper.map(entity, UserDto.class);
    }
}
