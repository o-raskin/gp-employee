package ru.olegraskin.suskills.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.olegraskin.suskills.domain.UserSkill;
import ru.olegraskin.suskills.domain.UserSuccessCriterion;

@Repository
public interface UserSuccessCriterionRepository
        extends JpaRepository<UserSuccessCriterion, UserSuccessCriterion.UserSuccessCriterionId> {
}
