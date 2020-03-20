package ru.olegraskin.suskills.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.olegraskin.suskills.domain.Skill;
import ru.olegraskin.suskills.dto.SkillDto;

@Component
@RequiredArgsConstructor
public class SkillMapperImpl implements SkillMapper{

    private final ModelMapper modelMapper;

    @Override
    public SkillDto convertToDTO(Skill skill) {
        return modelMapper.map(skill, SkillDto.class);
    }

    @Override
    public Skill convertToEntity(SkillDto dto) {
        return modelMapper.map(dto, Skill.class);
    }
}
