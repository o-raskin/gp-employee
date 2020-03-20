package ru.olegraskin.suskills.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.olegraskin.suskills.domain.Skill;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

    @Query("select s from Skill s where s.parent is null")
    List<Skill> findAllRoots();
}
