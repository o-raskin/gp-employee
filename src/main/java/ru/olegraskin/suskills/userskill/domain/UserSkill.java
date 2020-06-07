package ru.olegraskin.suskills.userskill.domain;

import lombok.*;
import ru.olegraskin.suskills.skill.domain.Skill;
import ru.olegraskin.suskills.user.domain.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "user_skill")
@Data
@NoArgsConstructor
public class UserSkill {

    @EmbeddedId
    private UserSkillId id;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("userId")
    private User user;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("skillId")
    private Skill skill;

    private Long editorId;

    private LocalDate lastEditDate;

    private LocalDate startDate;

    private LocalDate endDate;

    @NotNull
    @Enumerated
    private Status status = Status.NOT_MANDATORY;

    public UserSkill(User user, Skill skill) {
        this.id = new UserSkillId(user.getId(), skill.getId());
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

        /**
         * Skill is relearning
         */
        RELEARNING
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


