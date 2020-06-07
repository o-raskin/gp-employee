package ru.olegraskin.suskills.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.olegraskin.suskills.user.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
