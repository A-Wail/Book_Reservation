package com.proj.book_reservation.mapper;

import com.proj.book_reservation.dtos.book.AddBookDTO;
import com.proj.book_reservation.dtos.user.RegisterUserDTO;
import com.proj.book_reservation.entities.Book;
import com.proj.book_reservation.entities.User;

public class UserMapper {
    public static RegisterUserDTO userToDTO(User user){
        RegisterUserDTO userDTO =new RegisterUserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        userDTO.setPhone(user.getPhone());
        userDTO.setUsername(user.getUsername());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPasswordHash(user.getPasswordHash());
        return userDTO;
    }
    public static User toUser(RegisterUserDTO userDTO){
        User user=new User();
        if (userDTO.getEmail() != null)     user.setEmail(userDTO.getEmail());
        if (userDTO.getRole() != null)      user.setRole(userDTO.getRole());
        if (userDTO.getPhone() != null)     user.setPhone(userDTO.getPhone());
        if (userDTO.getUsername() != null)     user.setUsername(userDTO.getUsername());
        if (userDTO.getFirstName() != null)     user.setFirstName(userDTO.getFirstName());
        if (userDTO.getLastName() != null)     user.setLastName(userDTO.getLastName());
        if (userDTO.getPasswordHash() != null)     user.setPasswordHash(userDTO.getPasswordHash());
        return user;
    }
}
