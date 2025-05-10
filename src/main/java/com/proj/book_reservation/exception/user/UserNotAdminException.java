package com.proj.book_reservation.exception.user;

public class UserNotAdminException extends RuntimeException{
    public UserNotAdminException(String msg){
        super(msg);
    }
}
