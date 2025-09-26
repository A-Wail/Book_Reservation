package com.proj.book_reservation.exception.user;

public class UserFoundedException extends RuntimeException{
    public UserFoundedException(String msg){
        super(msg);
    }
}
