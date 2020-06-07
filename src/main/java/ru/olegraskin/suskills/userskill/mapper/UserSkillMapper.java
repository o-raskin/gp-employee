package ru.olegraskin.suskills.userskill.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.olegraskin.suskills.skill.domain.Skill;
import ru.olegraskin.suskills.userskill.domain.UserSkill;
import ru.olegraskin.suskills.skill.dto.SimpleSkillDto;
import ru.olegraskin.suskills.userskill.dto.UserSkillDto;
import ru.olegraskin.suskills.exception.BadRequestException;
import ru.olegraskin.suskills.skill.service.SkillService;

@Component
@RequiredArgsConstructor
public class UserSkillMapper {

    private final ModelMapper modelMapper;
    private final SkillService skillService;

    public UserSkillDto entityToDto(UserSkill entity) {
        UserSkillDto dto = modelMapper.map(entity, UserSkillDto.class);

        Skill skill = entity.getSkill();
        SimpleSkillDto simpleUserSkillDto = new SimpleSkillDto();
        simpleUserSkillDto.setId(skill.getId());
        simpleUserSkillDto.setName(skill.getName());
        simpleUserSkillDto.setDescription(skill.getDescription());
        if (skill.getGrade().getPreviousGrade() != null) {
            simpleUserSkillDto.setPreviousGradeId(skill.getGrade().getPreviousGrade().getId());
        }

        dto.setSkill(simpleUserSkillDto);

        return dto;
    }

    public UserSkill dtoToEntity(UserSkillDto dto) {
        UserSkill entity = modelMapper.map(dto, UserSkill.class);

        if (dto.getSkill() == null || dto.getSkill().getId() == null) {
            throw new BadRequestException("Need skill info for work with UserSkill");
        }
        Skill skill = skillService.getById(dto.getSkill().getId());
        entity.setSkill(skill);

        return entity;
    }
}
