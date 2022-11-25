package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

@Service
public class RegistrationService {
    private final UserRepository peopleRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(UserRepository peopleRepository, PasswordEncoder passwordEncoder) {
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(User person) {
        String encodePassword = passwordEncoder.encode(person.getPassword());
        person.setPassword(encodePassword);
        person.setRole("ROLE_USER");
        peopleRepository.save(person);
    }
}
