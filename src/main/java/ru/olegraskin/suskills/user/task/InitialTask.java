package ru.olegraskin.suskills.user.task;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.olegraskin.suskills.user.domain.User;
import ru.olegraskin.suskills.user.repository.UserRepository;
import ru.olegraskin.suskills.user.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class InitialTask {

    private final UserRepository userRepository;

    @PostConstruct
    public void init() {
        initSystemUser();
    }

    @Transactional
    public void initSystemUser() {
        Optional<User> optionalSystemUser = userRepository.findById(0L);
        if (!optionalSystemUser.isPresent()) {
            User user = new User();
            user.setId(0L);
            user.setGradeProgress(0);
            userRepository.save(user);
        }
    }
}
