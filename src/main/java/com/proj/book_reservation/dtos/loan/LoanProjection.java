package com.proj.book_reservation.dtos.loan;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface LoanProjection {
    Long getLoanId();
    Long getBookId();
    String getBookTitle();
    LocalDateTime getBorrowedDate();
    LocalDate getDueDate();
    LocalDate getReturnDate();
    String getStatus();
}
