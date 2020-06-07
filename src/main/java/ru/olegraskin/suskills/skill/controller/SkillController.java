package ru.olegraskin.suskills.skill.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.olegraskin.suskills.skill.domain.Skill;
import ru.olegraskin.suskills.skill.dto.SkillDto;
import ru.olegraskin.suskills.skill.mapper.SkillMapper;
import ru.olegraskin.suskills.skill.service.SkillService;

import javax.validation.Valid;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(
        value = "/skills",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class SkillController {

    private final SkillMapper skillMapper;
    private final SkillService skillService;

    @GetMapping
    public Set<SkillDto> getSkills() {
        return skillService.getAll().stream()
                .map(skillMapper::convertToDTO)
                .collect(Collectors.toSet());
    }

    @GetMapping("/{id}")
    public SkillDto getSkillByID(@PathVariable("id") Long id) {
        Skill skill = skillService.getById(id);
        return skillMapper.convertToDTO(skill);
    }

    @PostMapping
    public SkillDto createSkill(@Valid @RequestBody SkillDto dto) {
        Skill skill = skillMapper.convertToEntity(dto);
        Skill savedSkill = skillService.save(skill);
        return skillMapper.convertToDTO(savedSkill);
    }

    @PutMapping("/{id}")
    public SkillDto updateSkill(@PathVariable("id") Long id,
                                @Valid @RequestBody SkillDto skillDto) {
        skillDto.setId(id);
        Skill skill = skillMapper.convertToEntity(skillDto);
        Skill updatedSkill = skillService.update(skill);
        return skillMapper.convertToDTO(updatedSkill);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        skillService.delete(id);
    }


}
