package com.proj.book_reservation.dtos.loan;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@Builder
@Setter
@Getter
public class LoanDTO {
    private Long loanId;
    private Long bookId;
    private String bookTitle;
    private LocalDateTime borrowedDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private String  status;
}
