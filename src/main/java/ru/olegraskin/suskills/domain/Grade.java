package ru.olegraskin.suskills.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"position_id", "id"}),
        @UniqueConstraint(columnNames = {"position_id", "previous_grade_id"})
})
@Data
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "position_id")
    private Long positionId;

    @NotNull
    @Size(min = 3, max = 250)
    private String name;

    @Size(max = 1000)
    private String description;

    @Min(value = 0)
    @Max(value = 100)
    private int gradeProgress;

    @EqualsAndHashCode.Exclude
    @OneToOne(cascade = {CascadeType.DETACH})
    @JoinColumn(name = "previous_grade_id")
    private Grade previousGrade;

    @EqualsAndHashCode.Exclude
    @OneToMany
    private Set<Skill> skills = new HashSet<>();
}
