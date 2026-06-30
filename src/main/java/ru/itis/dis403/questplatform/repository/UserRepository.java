package ru.itis.dis403.questplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.dis403.questplatform.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}