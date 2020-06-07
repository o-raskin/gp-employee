package ru.olegraskin.suskills.userskill.service;

import ru.olegraskin.suskills.skill.domain.Skill;
import ru.olegraskin.suskills.user.domain.User;
import ru.olegraskin.suskills.userskill.domain.UserSkill;

import java.util.Set;

public interface UserSkillService {

    UserSkill getUserSkillData(Long userId, Long skillId);

    UserSkill getUserSkillData(User user, Skill skill);

    UserSkill update(Long userId, Long skillId, UserSkill userSkill);

    Set<UserSkill> getAllSkillsByUserId(Long userId);
}
