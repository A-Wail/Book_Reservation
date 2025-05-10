package com.proj.book_reservation.dtos.user;

import com.proj.book_reservation.entities.User;
import lombok.Data;

@Data
public class RegisterUserDTO {
    private String username;
    private String passwordHash;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private User.Role role;

}
