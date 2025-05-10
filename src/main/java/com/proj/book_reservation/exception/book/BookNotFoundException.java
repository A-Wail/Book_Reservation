package com.proj.book_reservation.exception.book;


public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(String msg){
        super(msg);
    }
}
