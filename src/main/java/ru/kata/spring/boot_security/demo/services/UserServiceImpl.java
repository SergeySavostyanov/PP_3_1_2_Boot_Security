package ru.kata.spring.boot_security.demo.services;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository peopleRepository;
    public UserServiceImpl(UserRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public User getById(Long id) {
        return peopleRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> listUsers() {
        return peopleRepository.findAll();
    }

    @Transactional
    @Override
    public void saveUser(User person) {
        peopleRepository.save(person);
    }
    @Transactional
    @Override
    public void removeUser(Long id) {
        peopleRepository.deleteById(id);
    }

}
