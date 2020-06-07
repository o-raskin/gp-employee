package ru.olegraskin.suskills.grade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.olegraskin.suskills.grade.domain.Grade;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
}
