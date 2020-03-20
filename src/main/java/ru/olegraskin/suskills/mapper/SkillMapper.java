package ru.olegraskin.suskills.mapper;

import ru.olegraskin.suskills.domain.Skill;
import ru.olegraskin.suskills.dto.SkillDto;

public interface SkillMapper {

    SkillDto convertToDTO(Skill skill);

    Skill convertToEntity(SkillDto dto);
}
