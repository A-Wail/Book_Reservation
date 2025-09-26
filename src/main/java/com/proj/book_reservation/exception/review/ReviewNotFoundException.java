package com.proj.book_reservation.exception.review;

public class ReviewNotFoundException extends RuntimeException {
    public ReviewNotFoundException(String msg) {
        super(msg);
    }
}
