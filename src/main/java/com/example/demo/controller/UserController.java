package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.model.WrapperResponse;
import com.example.demo.request.UserRequest;
import com.example.demo.response.UnifiedAccountResponse;
import com.example.demo.response.UserResponse;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public WrapperResponse<UserResponse> addUser(@RequestBody UserRequest userRequest) {
        return userService.addUser(userRequest);
    }

    @GetMapping("/getByEmail")
    public User getUserByEmail(@RequestParam String userEmail) {
        return userService.getUserByEmail(userEmail);
    }

    @GetMapping("/getAll")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/getAll")
    public WrapperResponse<UserResponse> allocateAccount(@RequestParam UUID userId, @RequestParam UUID accountId) {
        return userService.allocateAccount(userId, accountId);
    }

    @GetMapping("/unifiedAccounts")
    public  WrapperResponse<UnifiedAccountResponse> getUnifiedAccounts(@RequestParam UUID userId) {
        return userService.getUnifiedAccounts(userId);
    }
}
