package ru.olegraskin.suskills.grade.service;

import ru.olegraskin.suskills.grade.domain.Grade;
import ru.olegraskin.suskills.grade.domain.GradeHistoryItem;
import ru.olegraskin.suskills.user.domain.User;

import java.util.Set;

public interface GradeHistoryService {

    Set<GradeHistoryItem> getAllByUserId(Long userId);

    void raiseGrade(User user, Grade grade);
}
