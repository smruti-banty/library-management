package com.lms.backend.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lms.backend.constants.UserRole;
import com.lms.backend.constants.UserStatus;
import com.lms.backend.model.User;
import com.lms.backend.repository.UserRepository;
import com.lms.backend.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    @Override
    public User createUser(User user) {
        var encodePassword = passwordEncoder.encode(user.getPassword());
        var userId = UUID.randomUUID().toString();
        var userRole = UserRole.USER;
        var userStatus = UserStatus.PENDING_APPROVAL;
        var currentDate = LocalDateTime.now();
        
        user.setPassword(encodePassword);
        user.setUserId(userId);
        user.setUserRole(userRole);
        user.setUserStatus(userStatus);
        user.setCreatedDate(currentDate);
        user.setUpdatedDate(currentDate);

        userRepository.save(user);
        return user;
    }

    @Override
    public User createAdmin(User user) {
        var encodePassword = passwordEncoder.encode(user.getPassword());
        var userId = UUID.randomUUID().toString();
        var userRole = UserRole.ADMIN;
        var userStatus = UserStatus.ACTIVE;
        var currentDate = LocalDateTime.now();

        user.setPassword(encodePassword);
        user.setUserId(userId);
        user.setUserRole(userRole);
        user.setUserStatus(userStatus);
        user.setCreatedDate(currentDate);
        user.setUpdatedDate(currentDate);

        userRepository.save(user);
        return user;
    }

    @Override
    public User updateUser(User user, String userId) {
        var oldUser = userRepository.findById(userId).orElseThrow();

        oldUser.setFirstName(user.getFirstName());
        oldUser.setFirstName(user.getLastName());
        oldUser.setEmail(user.getEmail());
        oldUser.setReferenceNumber(user.getReferenceNumber());
        oldUser.setUserStatus(user.getUserStatus());
        oldUser.setUpdatedDate(LocalDateTime.now());

        userRepository.save(oldUser);
        return oldUser;
    }

    @Override
    public User deleteUser(String userId) {
        var user = userRepository.findById(userId).orElseThrow();
        user.setUserStatus(UserStatus.INACTIVE);
        user.setUpdatedDate(LocalDateTime.now());
        return user;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAllByUserRole(UserRole.USER);
    }

    @Override
    public List<User> getAdmins() {
        return userRepository.findAllByUserRole(UserRole.ADMIN);
    }

    @Override
    public User getUserById(String userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    @Override
    public User getUserByReferenceNumber(String referenceNumber) {
        return userRepository.findByReferenceNumber(referenceNumber).orElseThrow();
    }

    @Override
    public User approveUser(String userId) {
        var user = userRepository.findById(userId).orElseThrow();
        user.setUserStatus(UserStatus.ACTIVE);
        user.setUpdatedDate(LocalDateTime.now());
        return user;
    }
}
