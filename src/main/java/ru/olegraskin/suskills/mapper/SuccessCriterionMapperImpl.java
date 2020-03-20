package ru.olegraskin.suskills.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.olegraskin.suskills.domain.SuccessCriterion;
import ru.olegraskin.suskills.dto.SuccessCriterionDto;

@Component
@RequiredArgsConstructor
public class SuccessCriterionMapperImpl implements SuccessCriterionMapper {

    private final ModelMapper modelMapper;

    @Override
    public SuccessCriterionDto convertToDTO(SuccessCriterion sc) {
        return modelMapper.map(sc, SuccessCriterionDto.class);
    }

    @Override
    public SuccessCriterion convertToEntity(SuccessCriterionDto dto) {
        return modelMapper.map(dto, SuccessCriterion.class);
    }
}
