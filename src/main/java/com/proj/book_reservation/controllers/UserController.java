package com.proj.book_reservation.controllers;

import com.proj.book_reservation.dtos.user.UpdateUser;
import com.proj.book_reservation.dtos.user.UserProfile;
import com.proj.book_reservation.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }




    @GetMapping("/{userId}")
    public UserProfile viewProfile(@PathVariable Long userId){
        return userService.userProfile(userId);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> update( @PathVariable Long userId, @RequestBody UpdateUser userDTO){
        return ResponseEntity.ok(userService.update(userId,userDTO));
    }

}
