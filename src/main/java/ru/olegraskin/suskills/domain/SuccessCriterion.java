package ru.olegraskin.suskills.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class SuccessCriterion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private long id;

    @Size(min = 3, max = 250)
    @NotNull
    private String name;

    @Size(max = 1000)
    private String description;

    @EqualsAndHashCode.Exclude
    @ManyToOne(cascade = {CascadeType.DETACH})
    @JoinColumn(name = "skill_id")
    private Skill skill;

    @EqualsAndHashCode.Exclude
    @OneToMany(
            mappedBy = "successCriterion",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<UserSuccessCriterion> users = new HashSet<>();
}
