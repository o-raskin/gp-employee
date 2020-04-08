package ru.olegraskin.suskills.service;

import ru.olegraskin.suskills.domain.Skill;
import ru.olegraskin.suskills.domain.User;
import ru.olegraskin.suskills.domain.UserSkill;

public interface UserSkillService {

    UserSkill getUserSkillData(User user, Skill skill);
}
