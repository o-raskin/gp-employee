package ru.olegraskin.suskills.service;

import ru.olegraskin.suskills.domain.UserSkill;

public interface UserSkillService {

    UserSkill getUserSkillData(Long userId, Long skillId);
}
