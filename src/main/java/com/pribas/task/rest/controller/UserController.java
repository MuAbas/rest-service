package com.pribas.task.rest.controller;

import com.pribas.task.rest.entity.User;
import com.pribas.task.rest.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// User collection controller, handles requests sent to api/users
@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    /*  GET request to fetch all User documents,
        takes page and pageSize parameters (not required)    */
    @GetMapping("/all")
    public List<User> fetchAllUsers(@RequestParam(required = false, defaultValue = "0") Integer page,
                                    @RequestParam(required = false, defaultValue = "3") Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return userService.getAllUsers(pageable).getContent();
    }

    // GET request to find a specific User document using its id
    @GetMapping("/find/{id}")
    public Optional<User> findUser(@PathVariable String id) {
        return userService.findUser(id);
    }

    /*  POST request to add a new User to User collection
        Request must have a JSON body with required User fields (username(unique), email(unique), password)  */
    @PostMapping("/add")
    public String addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    /*  DELETE request to delete a User from User collection
        takes a path variable 'id'   */
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id) {
        return userService.deleteUser(id);
    }
}
