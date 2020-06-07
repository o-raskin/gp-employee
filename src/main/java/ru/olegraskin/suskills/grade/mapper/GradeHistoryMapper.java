package ru.olegraskin.suskills.grade.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.olegraskin.suskills.grade.domain.GradeHistoryItem;
import ru.olegraskin.suskills.grade.dto.GradeHistoryDto;

@Component
@RequiredArgsConstructor
public class GradeHistoryMapper {

    private final ModelMapper modelMapper;

    public GradeHistoryDto entityToDto(GradeHistoryItem entity) {
        return modelMapper.map(entity, GradeHistoryDto.class);
    }

    public GradeHistoryItem dtoToEntity(GradeHistoryDto dto) {
        return modelMapper.map(dto, GradeHistoryItem.class);
    }
}
