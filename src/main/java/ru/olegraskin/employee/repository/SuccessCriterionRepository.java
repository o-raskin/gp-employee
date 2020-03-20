package ru.olegraskin.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.olegraskin.employee.domain.SuccessCriterion;

@Repository
public interface SuccessCriterionRepository extends JpaRepository<SuccessCriterion, Long> {
}
