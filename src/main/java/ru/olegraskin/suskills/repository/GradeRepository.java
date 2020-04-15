package ru.olegraskin.suskills.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.olegraskin.suskills.domain.Grade;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
}
