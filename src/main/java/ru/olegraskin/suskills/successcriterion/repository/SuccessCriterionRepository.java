package ru.olegraskin.suskills.successcriterion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.olegraskin.suskills.successcriterion.domain.SuccessCriterion;

import java.util.Set;

@Repository
public interface SuccessCriterionRepository extends JpaRepository<SuccessCriterion, Long> {

    Set<SuccessCriterion> findAllBySkillId(Long id);
}
