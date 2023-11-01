package com.lms.backend.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lms.backend.dto.UserRequestDto;
import com.lms.backend.model.User;
import com.lms.backend.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@Tag(name = "User controller", description = "Manage users")
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create user", description = "create new user")
    public User createUser(@RequestBody UserRequestDto userRequestDto) {
        var user = new User();
        BeanUtils.copyProperties(userRequestDto, user);
        return userService.createUser(user);
    }

    @PostMapping("/createAdmin")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create admin", description = "create new admin")
    public User createAdmin(@RequestBody UserRequestDto userRequestDto) {
        var user = new User();
        BeanUtils.copyProperties(userRequestDto, user);
        return userService.createAdmin(user);
    }

    @PutMapping("/update/{userId}")
    @Operation(summary = "Update user", description = "Update user basic details")
    @ApiResponse(responseCode = "200", description = "On successful update")
    @ApiResponse(responseCode = "500", description = "User not found", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    public User updateUser(@RequestBody UserRequestDto userRequestDto, @PathVariable String userId) {
        var user = new User();
        BeanUtils.copyProperties(userRequestDto, user);
        return userService.updateUser(user, userId);
    }

    @DeleteMapping("/delete/{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Delete user", description = "Inactive a user by user id")
    @ApiResponse(responseCode = "200", description = "On successful delete")
    @ApiResponse(responseCode = "500", description = "User not found", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    public User deleteUser(@PathVariable String userId) {
        return userService.deleteUser(userId);
    }

    @GetMapping("/users")
    @Operation(summary = "Get users", description = "To retrive all users data")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/admins")
    @Operation(summary = "Get admins", description = "To retrive all admins data")
    public List<User> getAdmins() {
        return userService.getAdmins();
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get user by id", description = "To retrive a single user by user id")
    @ApiResponse(responseCode = "200", description = "On successful retrive")
    @ApiResponse(responseCode = "500", description = "User not found", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    public User getUserById(@PathVariable String userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/byreferencenumber/{referenceNumber}")
    @Operation(summary = "Get user by reference number", description = "To retrive a single user by reference number")
    @ApiResponse(responseCode = "200", description = "On successful retrive")
    @ApiResponse(responseCode = "500", description = "User not found", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    public User getUserByReferenceNumber(@PathVariable String referenceNumber) {
        return userService.getUserByReferenceNumber(referenceNumber);
    }

    @PutMapping("/approve/{userId}")
    @Operation(summary = "Approve user", description = "Approve user by id")
    @ApiResponse(responseCode = "200", description = "On successful approval")
    @ApiResponse(responseCode = "500", description = "User not found", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    public User approveUser(@PathVariable String userId) {
        return userService.approveUser(userId);
    }

    @GetMapping("/pending")
    @Operation(summary = "Get the list of all pending user", description = "To retrive all pending user")
    public List<User> getAllPendingUser() {
        return userService.findAllPendingUser();
    }
}
