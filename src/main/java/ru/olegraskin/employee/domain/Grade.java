package ru.olegraskin.employee.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Grade {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany
    private Set<Skill> skills = new HashSet<>();

}
