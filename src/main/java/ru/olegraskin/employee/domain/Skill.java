package ru.olegraskin.employee.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Skill {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 3, max = 250)
    private String name;

    @Size(max = 1000)
    private String description;

    @ManyToOne(cascade = {CascadeType.DETACH})
    @JoinColumn(name = "parent_id")
    private Skill parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private Set<Skill> children = new HashSet<>();

    @NotNull
    private LocalDate startDate;

    private LocalDate endDate;

    @NotNull
    @Enumerated
    private Status status;

    @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<SuccessCriterion> successCriteria = new HashSet<>();

    public enum Status {
        /**
         * When skill not mandatory to get next grade.
         */
        NOT_MANDATORY,

        /**
         * When skill mandatory to get next grade.
         */
        NEED_TO_KNOW,

        /**
         * When skill on pending for approving.
         */
        PENDING,

        /**
         * Skill approved.
         */
        APPROVED,
    }
}
