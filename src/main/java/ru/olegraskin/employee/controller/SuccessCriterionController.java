package ru.olegraskin.employee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.olegraskin.employee.domain.SuccessCriterion;
import ru.olegraskin.employee.dto.SuccessCriterionDto;
import ru.olegraskin.employee.mapper.SuccessCriterionMapper;
import ru.olegraskin.employee.service.SuccessCriterionService;

import javax.validation.Valid;

@RestController
@RequestMapping(
        value = "/sc",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class SuccessCriterionController {

    private final SuccessCriterionService successCriterionService;
    private final SuccessCriterionMapper successCriterionMapper;

    @PutMapping("/{id}")
    public SuccessCriterionDto updateFinishDate(@PathVariable("id") Long id,
                                                @RequestBody SuccessCriterionDto dto) {
        dto.setId(id);
        SuccessCriterion sc = successCriterionMapper.convertToEntity(dto);
        SuccessCriterion updatedSC = successCriterionService.update(sc);
        return successCriterionMapper.convertToDTO(updatedSC);
    }
}
