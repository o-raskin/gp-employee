package ru.olegraskin.suskills.user.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.olegraskin.suskills.user.domain.User;
import ru.olegraskin.suskills.user.dto.UserDto;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserDto entityToDto(User entity) {
        return modelMapper.map(entity, UserDto.class);
    }

    public User dtoToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }
}
