package com.proj.book_reservation.controllers;

import com.proj.book_reservation.dtos.user.AuthenticationRequest;
import com.proj.book_reservation.dtos.user.AuthenticationResp;
import com.proj.book_reservation.dtos.user.RegisterRequest;
import com.proj.book_reservation.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {

    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResp> registration(@RequestBody RegisterRequest request){
        return  ResponseEntity.ok(userService.register(request));
    }


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResp> authentication(@RequestBody AuthenticationRequest request){
        return  ResponseEntity.ok(userService.authenticate(request));
    }
}
