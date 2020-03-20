package ru.olegraskin.suskills.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.olegraskin.suskills.domain.SuccessCriterion;

@Repository
public interface SuccessCriterionRepository extends JpaRepository<SuccessCriterion, Long> {
}
