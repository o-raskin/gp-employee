package ru.olegraskin.suskills.usersuccesscriterion.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.olegraskin.suskills.successcriterion.domain.SuccessCriterion;
import ru.olegraskin.suskills.user.domain.User;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "user_success_criterion")
@Data
@NoArgsConstructor
public class UserSuccessCriterion {

    @EmbeddedId
    private UserSuccessCriterionId id;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("successCriterionId")
    private SuccessCriterion successCriterion;

    private LocalDate finishDate;

    private boolean achieved;

    public UserSuccessCriterion(User user, SuccessCriterion successCriterion) {
        this.id = new UserSuccessCriterionId(user.getId(), successCriterion.getId());
        this.successCriterion = successCriterion;
        this.user = user;
    }

    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class UserSuccessCriterionId implements Serializable {

        private Long userId;
        private Long successCriterionId;
    }
}
