package ru.olegraskin.suskills.user.service;

import ru.olegraskin.suskills.grade.domain.Grade;
import ru.olegraskin.suskills.user.domain.User;

import java.util.Optional;
import java.util.Set;

public interface UserService {

    User getUserById(Long id);

    User update(User user);

    User raiseUserGrade(Long userId);

    Set<User> getAll();

    Set<User> getAllByIds(Set<Long> ids);

    Set<Grade> getAllUserGrades(Long userId);
}
