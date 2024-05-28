package com.dailype.task.controller;

import com.dailype.task.dto.UserDto;
import com.dailype.task.exception.ManagerNotFoundOrActiveException;
import com.dailype.task.exception.UserNotFoundException;
import com.dailype.task.model.User;
import com.dailype.task.service.UserJpaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserJpaService userJpaService;

    @PostMapping("/add")
    public ResponseEntity<User> createUser(@RequestBody @Valid UserDto userDto) {
        return new ResponseEntity<>(userJpaService.createUser(userDto), HttpStatus.CREATED);
    }

    @GetMapping("/id/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable int userId) throws UserNotFoundException {
        return new ResponseEntity<>(userJpaService.getUserById(userId), HttpStatus.OK);
    }

    @GetMapping("/{mobNum}")
    public ResponseEntity<User> getUserByMobNum(@PathVariable String mobNum) {
        return new ResponseEntity<>(userJpaService.getUserByMobNum(mobNum), HttpStatus.OK);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable int userId, @RequestBody UserDto userDto) throws UserNotFoundException, ManagerNotFoundOrActiveException {
        return new ResponseEntity<>(userJpaService.updateUser(userId, userDto), HttpStatus.OK);
    }

    @PostMapping("/manager/{managerId}/add")
    public ResponseEntity<User> createUserWithManager(@PathVariable int managerId, @RequestBody @Valid UserDto userDto) throws ManagerNotFoundOrActiveException {
        return new ResponseEntity<>(userJpaService.createUserWithManager(managerId, userDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/id/{userId}")
    public void deleteUser(@PathVariable int userId) {
        userJpaService.deleteUser(userId);
    }

    @DeleteMapping("/{mobNum}")
    public void deleteUserByMobNum(@PathVariable String mobNum) {
        userJpaService.deleteUserByMobNum(mobNum);
    }
}
