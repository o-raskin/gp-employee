package ru.olegraskin.suskills.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.olegraskin.suskills.domain.User;
import ru.olegraskin.suskills.domain.UserSkill;
import ru.olegraskin.suskills.repository.UserRepository;
import ru.olegraskin.suskills.service.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUserById(Long id) {
        Optional<User> optional = userRepository.findById(id);
        return optional.orElseGet(() -> this.save(new User(id)));
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
