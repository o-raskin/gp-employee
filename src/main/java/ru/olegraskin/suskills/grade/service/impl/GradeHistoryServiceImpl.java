package ru.olegraskin.suskills.grade.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.olegraskin.suskills.grade.domain.Grade;
import ru.olegraskin.suskills.grade.domain.GradeHistoryItem;
import ru.olegraskin.suskills.grade.repository.GradeHistoryRepository;
import ru.olegraskin.suskills.grade.service.GradeHistoryService;
import ru.olegraskin.suskills.user.domain.User;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GradeHistoryServiceImpl implements GradeHistoryService {

    private final GradeHistoryRepository gradeHistoryRepository;

    @Override
    public Set<GradeHistoryItem> getAllByUserId(Long userId) {
        return gradeHistoryRepository.findAllByUserId(userId);
    }

    @Transactional
    @Override
    public void raiseGrade(User user, Grade grade) {
        GradeHistoryItem gradeHistoryItem = new GradeHistoryItem(user, grade);
        gradeHistoryItem.setAchievedDate(LocalDate.now());

        boolean alreadyAchieved = gradeHistoryRepository.existsByUserAndGrade(user, grade);
        if (!alreadyAchieved) {
            gradeHistoryRepository.save(gradeHistoryItem);
        }
    }
}
