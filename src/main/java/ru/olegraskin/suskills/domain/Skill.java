package ru.olegraskin.suskills.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(min = 3, max = 250)
    private String name;

    @Size(max = 1000)
    private String description;

    @EqualsAndHashCode.Exclude
    @ManyToOne(cascade = {CascadeType.DETACH})
    @JoinColumn(name = "parent_id")
    private Skill parent;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private Set<Skill> children = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<SuccessCriterion> successCriteria = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @OneToMany(
            mappedBy = "skill",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<UserSkill> users = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @ManyToOne(cascade = {CascadeType.DETACH})
    @JoinColumn(name = "grade_id")
    private Grade grade;

}
