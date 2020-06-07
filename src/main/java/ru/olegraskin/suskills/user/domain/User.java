package ru.olegraskin.suskills.user.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.olegraskin.suskills.userskill.domain.UserSkill;
import ru.olegraskin.suskills.usersuccesscriterion.domain.UserSuccessCriterion;
import ru.olegraskin.suskills.grade.domain.Grade;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @Column(nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH})
    private Grade grade;

    @Min(value = 0)
    @Max(value = 100)
    private int gradeProgress;

    /**
     * Learned skills by user
     */
    @EqualsAndHashCode.Exclude
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private Set<UserSkill> skills = new HashSet<>();

    /**
     * Achieved success criteria by user
     */
    @EqualsAndHashCode.Exclude
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private Set<UserSuccessCriterion> successCriteria = new HashSet<>();

    public User(Long id) {
        this.id = id;
    }
}
