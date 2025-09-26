package com.proj.book_reservation.dtos.user;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateUser {
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
}
