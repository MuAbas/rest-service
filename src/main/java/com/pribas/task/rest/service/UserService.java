package com.pribas.task.rest.service;

import com.pribas.task.rest.entity.User;
import com.pribas.task.rest.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/*  Service for User collection
    Handles all the logic and
    calls UserRepository to fetch data    */
@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    /*  fetch all User documents.
        show pageSize amount of documents per page   */
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    // fetch a User document using its id
    public Optional<User> findUser(String id) {
        return userRepository.findById(id);
    }

    // add new User document to User collection
    public String addUser(User user) {
        userRepository.save(user);
        return "User Added::" + user.getId();
    }

    /*  delete User document from
        User collection using its id   */
    public String deleteUser(String id) {
        userRepository.deleteById(id);
        return "Deleted User::" + id;
    }
}
