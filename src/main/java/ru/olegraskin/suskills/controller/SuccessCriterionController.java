package ru.olegraskin.suskills.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.olegraskin.suskills.domain.SuccessCriterion;
import ru.olegraskin.suskills.dto.SuccessCriterionDto;
import ru.olegraskin.suskills.mapper.SuccessCriterionMapper;
import ru.olegraskin.suskills.service.SuccessCriterionService;

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
