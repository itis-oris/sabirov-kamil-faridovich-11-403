package ru.itis.dis403.questplatform.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dis403.questplatform.form.RegisterForm;
import ru.itis.dis403.questplatform.model.Role;
import ru.itis.dis403.questplatform.model.RoleName;
import ru.itis.dis403.questplatform.model.User;
import ru.itis.dis403.questplatform.repository.RoleRepository;
import ru.itis.dis403.questplatform.repository.UserRepository;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User register(RegisterForm form) {
        log.info("Registration attempt for email: {}", form.getEmail());

        if (userRepository.existsByEmail(form.getEmail())) {
            log.warn("Email already exists: {}", form.getEmail());
            throw new IllegalArgumentException("Пользователь с таким email уже существует");
        }

        User user = new User();
        user.setEmail(form.getEmail());
        user.setUsername(form.getUsername());
        user.setPassword(passwordEncoder.encode(form.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Роль ROLE_USER не найдена в БД"));
        user.setRoles(Set.of(userRole));

        User saved = userRepository.save(user);
        log.info("User registered successfully: id={}", saved.getId());
        return saved;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден: " + email));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден: " + email));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles().stream()
                        .map(role -> role.getName().name().replace("ROLE_", ""))
                        .toArray(String[]::new))
                .build();
    }
}