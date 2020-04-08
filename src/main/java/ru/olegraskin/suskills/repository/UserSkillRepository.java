package ru.olegraskin.suskills.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.olegraskin.suskills.domain.Skill;
import ru.olegraskin.suskills.domain.User;
import ru.olegraskin.suskills.domain.UserSkill;

import java.util.Optional;

@Repository
public interface UserSkillRepository extends JpaRepository<UserSkill, UserSkill.UserSkillId> {

    Optional<UserSkill> findUserSkillByUserAndSkill(User user, Skill skill);
}
