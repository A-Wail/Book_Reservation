package com.proj.book_reservation.dtos.user;

import com.proj.book_reservation.model.User;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class RegisterRequest {
    private String username;
    private String passwordHash;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
//    @NotNull(message = "Role is required")
    private User.Role role;

}
