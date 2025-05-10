package com.proj.book_reservation.exception.book;

public class BookIsFoundedException extends RuntimeException{
    public BookIsFoundedException(String msg){
        super(msg);
    }
}
