package ru.itis.dis403.questplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.dis403.questplatform.model.Role;
import ru.itis.dis403.questplatform.model.RoleName;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}