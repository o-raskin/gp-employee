package ru.olegraskin.suskills.grade.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.olegraskin.suskills.grade.domain.Grade;
import ru.olegraskin.suskills.skill.domain.Skill;
import ru.olegraskin.suskills.user.domain.User;
import ru.olegraskin.suskills.userskill.domain.UserSkill;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "grade_history")
@Data
@NoArgsConstructor
public class GradeHistoryItem {

    @EmbeddedId
    private GradeHistoryId id;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("gradeId")
    private Grade grade;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    private LocalDate achievedDate;

    public GradeHistoryItem(User user, Grade grade) {
        this.id = new GradeHistoryItem.GradeHistoryId(user.getId(), grade.getId());
        this.user = user;
        this.grade = grade;
    }

    @Embeddable
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GradeHistoryId implements Serializable {
        private Long userId;
        private Long gradeId;
    }
}
