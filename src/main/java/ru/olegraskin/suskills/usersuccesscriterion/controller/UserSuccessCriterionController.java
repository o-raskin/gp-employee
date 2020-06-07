package ru.olegraskin.suskills.usersuccesscriterion.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.olegraskin.suskills.usersuccesscriterion.domain.UserSuccessCriterion;
import ru.olegraskin.suskills.usersuccesscriterion.dto.UserSuccessCriterionDto;
import ru.olegraskin.suskills.usersuccesscriterion.mapper.UserSuccessCriterionMapper;
import ru.olegraskin.suskills.usersuccesscriterion.service.UserSuccessCriterionService;

@RestController
@RequestMapping(
        value = "/users/{userId}/sc/{scId}",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class UserSuccessCriterionController {

    private final UserSuccessCriterionService userSuccessCriterionService;
    private final UserSuccessCriterionMapper userSuccessCriterionMapper;

    @GetMapping
    public UserSuccessCriterionDto getUserSuccessCriteriaData(@PathVariable("userId") Long userId,
                                                         @PathVariable("scId") Long scId) {
        UserSuccessCriterion userSuccessCriterion =
                userSuccessCriterionService.getByUserIdAndSuccessCriterionId(userId, scId);
        return userSuccessCriterionMapper.entityToDto(userSuccessCriterion);
    }

    @PutMapping
    public UserSuccessCriterionDto updateUserSuccessCriterionData(@PathVariable("userId") Long userId,
                                       @PathVariable("scId") Long scId,
                                       @RequestBody UserSuccessCriterionDto userSuccessCriterionDto) {
        userSuccessCriterionDto.setSuccessCriterionId(scId);
        userSuccessCriterionDto.setUserId(userId);
        UserSuccessCriterion userSuccessCriterion = userSuccessCriterionMapper.dtoToEntity(userSuccessCriterionDto);
        UserSuccessCriterion savedUserSuccessCriterion = userSuccessCriterionService.update(userSuccessCriterion);
        return userSuccessCriterionMapper.entityToDto(savedUserSuccessCriterion);
    }

}
