package ru.olegraskin.employee.mapper;

import ru.olegraskin.employee.domain.Skill;
import ru.olegraskin.employee.dto.SkillDto;

public interface SkillMapper {

    SkillDto convertToDTO(Skill skill);

    Skill convertToEntity(SkillDto dto);
}
