package ru.olegraskin.suskills.successcriterion.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.olegraskin.suskills.successcriterion.domain.SuccessCriterion;
import ru.olegraskin.suskills.successcriterion.dto.SuccessCriterionDto;

@Component
@RequiredArgsConstructor
public class SuccessCriterionMapper {

    private final ModelMapper modelMapper;

    public SuccessCriterionDto convertToDTO(SuccessCriterion sc) {
        return modelMapper.map(sc, SuccessCriterionDto.class);
    }

    public SuccessCriterion convertToEntity(SuccessCriterionDto dto) {
        return modelMapper.map(dto, SuccessCriterion.class);
    }
}
