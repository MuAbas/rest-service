package com.pribas.task.rest.service;

import com.pribas.task.rest.entity.User;
import com.pribas.task.rest.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public Optional<User> findUser(String id) {
        return userRepository.findById(id);
    }

    public String addUser(User user) {
        userRepository.save(user);
        return "User Added::" + user.getId();
    }

    public String deleteUser(String id) {
        userRepository.deleteById(id);
        return "Deleted User::" + id;
    }
}
