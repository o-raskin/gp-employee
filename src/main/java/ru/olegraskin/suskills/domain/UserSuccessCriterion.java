package ru.olegraskin.suskills.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "user_success_criterion")
@Data
@AllArgsConstructor
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

    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class UserSuccessCriterionId implements Serializable {

        private Long userId;
        private Long successCriterionId;
    }
}
