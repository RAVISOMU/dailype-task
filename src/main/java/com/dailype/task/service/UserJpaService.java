package com.dailype.task.service;

import com.dailype.task.dto.UserDto;
import com.dailype.task.exception.ManagerNotFoundOrActiveException;
import com.dailype.task.exception.UserNotFoundException;
import com.dailype.task.model.Manager;
import com.dailype.task.model.User;
import com.dailype.task.repository.ManagerJpaRepository;
import com.dailype.task.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
public class UserJpaService {
    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private ManagerJpaRepository managerJpaRepository;

    public User createUser(UserDto userDto) {
        User newUser = User.build(0, userDto.getUserName(), userDto.getMobNum(), userDto.getPanNum(), LocalDateTime.now(), LocalDateTime.now(), null);
        return userJpaRepository.save(newUser);
    }

    public User getUserById(int userId) throws UserNotFoundException {
        User user = userJpaRepository.findByUserId(userId);
        if (user != null) {
            return user;
        } else {
            throw new UserNotFoundException("User not found with id: " + userId);
        }
    }

    public User getUserByMobNum(String mobNum) {
        User user = userJpaRepository.findByMobNum(mobNum);
        if (user != null) {
            return user;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public User updateUser(int userId, UserDto userDto) throws UserNotFoundException, ManagerNotFoundOrActiveException {
        User user = userJpaRepository.findByUserId(userId);
        if (user != null) {
            if (userDto.getUserName() != null) {
                user.setUserName(userDto.getUserName());
            }
            if (userDto.getMobNum() != null) {
                user.setMobNum(userDto.getMobNum());
            }
            if (userDto.getPanNum() != null) {
                user.setPanNum(userDto.getPanNum());
            }
            user.setUpdatedAt(LocalDateTime.now());

            return userJpaRepository.save(user);
        } else {
            throw new UserNotFoundException("User not found with id: " + userId);
        }
    }

    public User createUserWithManager(int managerId, UserDto userDto) throws ManagerNotFoundOrActiveException {
        Manager newManager = managerJpaRepository.findByManagerId(managerId);
        if (newManager != null && newManager.isActive() == true) {
            User newUser = User.build(0, userDto.getUserName(), userDto.getMobNum(), userDto.getPanNum(), LocalDateTime.now(), LocalDateTime.now(), newManager);

            return userJpaRepository.save(newUser);
        } else {
            throw new ManagerNotFoundOrActiveException("Manager Not Found or Not Active");
        }
    }

    public void deleteUser(int userId) {
        User user = userJpaRepository.findByUserId(userId);
        if (user != null) {
            userJpaRepository.deleteById(userId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    public void deleteUserByMobNum(String mobNum) {
        User user = userJpaRepository.findByMobNum(mobNum);
        if (user != null) {
            int userId = user.getUserId();
            userJpaRepository.deleteById(userId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }
}
