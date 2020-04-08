package ru.olegraskin.suskills.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "user_skill")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSkill {

    @EmbeddedId
    private UserSkillId id;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("skillId")
    private Skill skill;

    private LocalDate startDate;

    private LocalDate endDate;

    @NotNull
    @Enumerated
    private Status status = Status.NOT_MANDATORY;

    public UserSkill(User user, Skill skill) {
        this.user = user;
        this.skill = skill;
    }

    public enum Status {
        /**
         * Skill not mandatory to promote on next grade.
         */
        NOT_MANDATORY,

        /**
         * Skill mandatory to promote on next grade.
         */
        NEED_TO_KNOW,

        /**
         * Skill in process of learning.
         */
        IN_PROGRESS,

        /**
         * Skill on pending for approving.
         */
        PENDING,

        /**
         * Skill approved.
         */
        APPROVED,
    }

    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class UserSkillId implements Serializable {

        private Long userId;

        private Long skillId;
    }
}


