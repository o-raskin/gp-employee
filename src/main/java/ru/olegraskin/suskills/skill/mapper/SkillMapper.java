package ru.olegraskin.suskills.skill.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.olegraskin.suskills.skill.domain.Skill;
import ru.olegraskin.suskills.skill.dto.SkillDto;

@Component
@RequiredArgsConstructor
public class SkillMapper {

    private final ModelMapper modelMapper;

    public SkillDto convertToDTO(Skill skill) {
        return modelMapper.map(skill, SkillDto.class);
    }

    public Skill convertToEntity(SkillDto dto) {
        return modelMapper.map(dto, Skill.class);
    }
}
