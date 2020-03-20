package ru.olegraskin.suskills.mapper;

import ru.olegraskin.suskills.domain.SuccessCriterion;
import ru.olegraskin.suskills.dto.SuccessCriterionDto;

public interface SuccessCriterionMapper {

    SuccessCriterionDto convertToDTO(SuccessCriterion sc);

    SuccessCriterion convertToEntity(SuccessCriterionDto dto);
}
