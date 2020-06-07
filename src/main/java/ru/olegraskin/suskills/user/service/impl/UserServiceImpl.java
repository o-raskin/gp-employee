package ru.olegraskin.suskills.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.olegraskin.suskills.exception.ResourceNotFoundException;
import ru.olegraskin.suskills.grade.domain.Grade;
import ru.olegraskin.suskills.grade.domain.GradeHistoryItem;
import ru.olegraskin.suskills.grade.service.GradeHistoryService;
import ru.olegraskin.suskills.user.domain.User;
import ru.olegraskin.suskills.user.repository.UserRepository;
import ru.olegraskin.suskills.user.service.UserService;
import ru.olegraskin.suskills.userskill.task.UserSkillStatusComputeTasks;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final GradeHistoryService gradeHistoryService;
    private final UserSkillStatusComputeTasks userSkillStatusComputeTasks;

    @Override
    public User getUserById(Long id) {
        Optional<User> optional = userRepository.findById(id);
        return optional.orElseGet(() -> this.save(new User(id)));
    }

    @Transactional
    @Override
    public User update(User user) {
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if (optionalUser.isPresent()) {
            User storedUser = optionalUser.get();
            Grade storedUserGrade = storedUser.getGrade();
            if (!storedUserGrade.equals(user.getGrade())) {
                userSkillStatusComputeTasks.approveAllLearnedSkills(user);
            }
        }
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public User raiseUserGrade(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            throw new ResourceNotFoundException("User", "id", userId);
        }
        User user = optionalUser.get();
        Grade currentUserGrade = user.getGrade();
        if (currentUserGrade.getNextGrade() == null) {
            throw new ResourceNotFoundException("Next grade", "user id", userId);
        }

        user.setGrade(currentUserGrade.getNextGrade());

        gradeHistoryService.raiseGrade(user, currentUserGrade.getNextGrade());

        return userRepository.save(user);
    }

    @Override
    public Set<User> getAll() {
        return new HashSet<>(userRepository.findAll());
    }

    @Override
    public Set<User> getAllByIds(Set<Long> ids) {
        return new HashSet<>(userRepository.findAllById(ids));
    }

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Set<Grade> getAllUserGrades(Long userId) {

        Set<Grade> userGrades = new HashSet<>();

        User user = this.getUserById(userId);
        Grade grade = user.getGrade();
        while(grade != null) {
            userGrades.add(grade);
            grade = grade.getPreviousGrade();
        }

        return userGrades;
    }
}
