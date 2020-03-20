package ru.olegraskin.employee.mapper;

import ru.olegraskin.employee.domain.SuccessCriterion;
import ru.olegraskin.employee.dto.SuccessCriterionDto;

public interface SuccessCriterionMapper {

    SuccessCriterionDto convertToDTO(SuccessCriterion sc);

    SuccessCriterion convertToEntity(SuccessCriterionDto dto);
}
