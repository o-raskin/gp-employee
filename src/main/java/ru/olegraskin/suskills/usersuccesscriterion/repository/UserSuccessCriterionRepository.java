package ru.olegraskin.suskills.usersuccesscriterion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.olegraskin.suskills.successcriterion.domain.SuccessCriterion;
import ru.olegraskin.suskills.user.domain.User;
import ru.olegraskin.suskills.usersuccesscriterion.domain.UserSuccessCriterion;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserSuccessCriterionRepository
        extends JpaRepository<UserSuccessCriterion, UserSuccessCriterion.UserSuccessCriterionId> {

    Optional<UserSuccessCriterion> findByUserAndSuccessCriterion(User user, SuccessCriterion sc);

    Optional<UserSuccessCriterion> findByUserIdAndSuccessCriterionId(Long userId, Long successCriterionId);

    Set<UserSuccessCriterion> findAllByUserIdAndSuccessCriterionIn(Long userId, Set<SuccessCriterion> successCriterionIds);
}
