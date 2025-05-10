package com.proj.book_reservation.exception.review;

public class IllegalRatingException extends RuntimeException{
    public IllegalRatingException(String msg){
        super(msg);
    }
}
