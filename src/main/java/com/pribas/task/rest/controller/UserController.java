package com.pribas.task.rest.controller;

import com.pribas.task.rest.entity.User;
import com.pribas.task.rest.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public List<User> fetchAllUsers(@RequestParam(required = false, defaultValue = "0") Integer page,
                                    @RequestParam(required = false, defaultValue = "3") Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return userService.getAllUsers(pageable).getContent();
    }

    @GetMapping("/find/{id}")
    public Optional<User> findUser(@PathVariable String id) {
        return userService.findUser(id);
    }

    @PostMapping("/add")
    public String addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id) {
        return userService.deleteUser(id);
    }
}
