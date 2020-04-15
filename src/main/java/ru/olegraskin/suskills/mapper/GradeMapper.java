package ru.olegraskin.suskills.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.olegraskin.suskills.domain.Grade;
import ru.olegraskin.suskills.dto.GradeDto;

@Component
@RequiredArgsConstructor
public class GradeMapper {

    private final ModelMapper modelMapper;

    public GradeDto entityToDto(Grade entity) {
        return modelMapper.map(entity, GradeDto.class);
    }

    public Grade dtoToEntity(GradeDto dto) {
        return modelMapper.map(dto, Grade.class);
    }
}
