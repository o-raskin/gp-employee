package ru.olegraskin.suskills.usersuccesscriterion.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.olegraskin.suskills.usersuccesscriterion.domain.UserSuccessCriterion;
import ru.olegraskin.suskills.usersuccesscriterion.dto.UserSuccessCriterionDto;

@Component
@RequiredArgsConstructor
public class UserSuccessCriterionMapper {

    private final ModelMapper modelMapper;

    public UserSuccessCriterionDto entityToDto(UserSuccessCriterion entity) {
        return modelMapper.map(entity, UserSuccessCriterionDto.class);
    }

    public UserSuccessCriterion dtoToEntity(UserSuccessCriterionDto dto) {
        return modelMapper.map(dto, UserSuccessCriterion.class);
    }
}
