package ru.olegraskin.suskills.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Entity
public class SuccessCriterion {

    @Id
    @GeneratedValue
    private long id;

    @Size(min = 3, max = 250)
    @NotNull
    private String name;

    @Size(max = 1000)
    private String description;

    private boolean achieved;

    private LocalDate finishDate;

    @ManyToOne(cascade = {CascadeType.DETACH})
    @JoinColumn(name = "skill_id")
    private Skill skill;
}
