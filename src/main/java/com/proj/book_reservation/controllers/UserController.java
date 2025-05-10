package com.proj.book_reservation.controllers;

import com.proj.book_reservation.dtos.user.RegisterUserDTO;
import com.proj.book_reservation.dtos.user.UpdateUserDTO;
import com.proj.book_reservation.dtos.user.UserProfileDTo;
import com.proj.book_reservation.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registration(@RequestBody RegisterUserDTO userDTO){
        return  ResponseEntity.ok(userService.insert(userDTO));
    }

    @GetMapping("/{userId}")
    public UserProfileDTo viewProfile(@PathVariable Long userId){
        return userService.viewProfile(userId);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> update( @PathVariable Long userId, @RequestBody UpdateUserDTO userDTO){
        return ResponseEntity.ok(userService.update(userId,userDTO));
    }

}
