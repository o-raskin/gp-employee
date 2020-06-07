package ru.olegraskin.suskills.grade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.olegraskin.suskills.grade.domain.Grade;
import ru.olegraskin.suskills.grade.domain.GradeHistoryItem;
import ru.olegraskin.suskills.user.domain.User;

import java.util.Set;

public interface GradeHistoryRepository extends JpaRepository<GradeHistoryItem, GradeHistoryItem.GradeHistoryId> {

    Set<GradeHistoryItem> findAllByUserId(Long userId);

    boolean existsByUserAndGrade(User user, Grade grade);
}
