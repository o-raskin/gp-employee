package ru.olegraskin.suskills.userskill.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.olegraskin.suskills.exception.ResourceNotFoundException;
import ru.olegraskin.suskills.userskill.domain.UserSkill;
import ru.olegraskin.suskills.userskill.dto.UserSkillDto;
import ru.olegraskin.suskills.userskill.mapper.UserSkillMapper;
import ru.olegraskin.suskills.userskill.service.UserSkillService;
import ru.olegraskin.suskills.usersuccesscriterion.dto.UserSuccessCriterionDto;
import ru.olegraskin.suskills.usersuccesscriterion.mapper.UserSuccessCriterionMapper;
import ru.olegraskin.suskills.usersuccesscriterion.service.UserSuccessCriterionService;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping(
        value = "/users/{userId}/skills",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class UserSkillController {

    private final UserSkillService userSkillService;
    private final UserSkillMapper userSkillMapper;
    private final UserSuccessCriterionService userSuccessCriterionService;
    private final UserSuccessCriterionMapper userSuccessCriterionMapper;

    /**
     * Latest changed user skill
     *
     * @param userId
     * @return user skill data
     */
    @GetMapping("/latest")
    public UserSkillDto getLatestChangedUserSkillData(@PathVariable("userId") Long userId) {
        Set<UserSkill> userSkills = userSkillService.getAllSkillsByUserId(userId);
        if (userSkills.size() == 0) {
            throw new ResourceNotFoundException("Latest user skill", "userId", userId);
        }

        return userSkills.stream()
                .filter(us -> us.getEditorId() != null)
                .map(userSkillMapper::entityToDto)
                .max(Comparator.comparing(UserSkillDto::getLastEditDate))
                .orElseThrow(() -> new ResourceNotFoundException("Latest user skill", "userId", userId));
    }

    @GetMapping("/{skillId}")
    public UserSkillDto getUserDataAboutSkill(@PathVariable("userId") Long userId,
                                              @PathVariable("skillId") Long skillId) {
        UserSkill userSkill = userSkillService.getUserSkillData(userId, skillId);
        return userSkillMapper.entityToDto(userSkill);
    }

    @GetMapping
    public Set<UserSkillDto> getUserSkillsData(@PathVariable("userId") Long userId) {
        Set<UserSkill> userSkills = userSkillService.getAllSkillsByUserId(userId);
        return userSkills.stream()
                .map(userSkillMapper::entityToDto)
                .collect(Collectors.toSet());

    }

    @PutMapping("/{skillId}")
    public UserSkillDto updateUserSkillData(@PathVariable("userId") Long userId,
                                            @PathVariable("skillId") Long skillId,
                                            @RequestBody UserSkillDto userSkillDto) {
        UserSkill userSkill = userSkillMapper.dtoToEntity(userSkillDto);
        UserSkill savedUserSkill = userSkillService.update(userId, skillId, userSkill);
        return userSkillMapper.entityToDto(savedUserSkill);
    }

    @GetMapping("/{skillId}/sc")
    public Set<UserSuccessCriterionDto> getAllSuccessCriteriaByUserAndSkill(@PathVariable("userId") Long userId,
                                                                            @PathVariable("skillId") Long skillId) {
        return userSuccessCriterionService.getAllByUserAndSkill(userId, skillId).stream()
                .map(userSuccessCriterionMapper::entityToDto)
                .collect(Collectors.toSet());
    }

    @GetMapping("/stats")
    public Map<Integer, Map<String, List<UserSkillDto>>> getSkillsForStatistics(@PathVariable("userId") Long userId) {

        Set<UserSkill> userSkills = userSkillService.getAllSkillsByUserId(userId);

        return userSkills.stream()
                .filter(userSkill -> userSkill.getStatus().equals(UserSkill.Status.APPROVED))
                .sorted(Comparator.comparing(UserSkill::getEndDate))
                .map(userSkillMapper::entityToDto)
                .collect(Collectors.groupingBy(
                        e -> e.getEndDate().getYear(),
                        Collectors.groupingBy(
                                e -> e.getEndDate().getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH),
                                Collectors.toList())));
    }

}
