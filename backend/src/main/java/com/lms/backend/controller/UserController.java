package com.lms.backend.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.lms.backend.dto.UserRequestDto;
import com.lms.backend.model.User;
import com.lms.backend.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody UserRequestDto userRequestDto) {
        var user = new User();
        BeanUtils.copyProperties(userRequestDto, user);
        return userService.createUser(user);
    }

    @PostMapping("/createAdmin")
    @ResponseStatus(HttpStatus.CREATED)
    public User createAdmin(@RequestBody UserRequestDto userRequestDto) {
        var user = new User();
        BeanUtils.copyProperties(userRequestDto, user);
        return userService.createAdmin(user);
    }

    @PutMapping("/update/{userId}")
    public User updateUser(@RequestBody UserRequestDto userRequestDto, @PathVariable String userId) {
        var user = new User();
        BeanUtils.copyProperties(userRequestDto, user);
        return userService.updateUser(user, userId);
    }

    @DeleteMapping("/delete/{userId}")
    public User deleteUser(@PathVariable String userId) {
        return userService.deleteUser(userId);
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/admins")

    public List<User> getAdmins() {
        return userService.getAdmins();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable String userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/byReferenceNumber/{referenceNumber}")
    public User getUserByReferenceNumber(@PathVariable String referenceNumber) {
        return userService.getUserByReferenceNumber(referenceNumber);
    }

    @PutMapping("/approve/{userId}")
    public User approveUser(@PathVariable String userId) {
        return userService.approveUser(userId);
    }
}
