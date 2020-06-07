package ru.olegraskin.suskills.userskill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.olegraskin.suskills.skill.domain.Skill;
import ru.olegraskin.suskills.user.domain.User;
import ru.olegraskin.suskills.userskill.domain.UserSkill;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserSkillRepository extends JpaRepository<UserSkill, UserSkill.UserSkillId> {

    Optional<UserSkill> findUserSkillByUserAndSkill(User user, Skill skill);

    Set<UserSkill> findAllByUserId(Long userId);
}
