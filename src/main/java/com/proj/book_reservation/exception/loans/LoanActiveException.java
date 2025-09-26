package com.proj.book_reservation.exception.loans;

public class LoanActiveException extends RuntimeException {
    public LoanActiveException(String msg) {
        super(msg);
    }
}
