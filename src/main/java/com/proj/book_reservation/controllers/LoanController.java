package com.proj.book_reservation.controllers;

import com.proj.book_reservation.dtos.book.AddBookDTO;
import com.proj.book_reservation.dtos.loan.LoanDTO;
import com.proj.book_reservation.entities.Loan;
import com.proj.book_reservation.services.BookService;
import com.proj.book_reservation.services.LoanService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class LoanController {
    private final LoanService loanService;

    @PostMapping("/loans")
    public ResponseEntity<?> borrow(@RequestParam(name = "userId") Long userId,
                                    @RequestParam(name = "bookId") Long bookId){
        return ResponseEntity.ok(loanService.selectBook(userId,bookId));
    }

    @PutMapping("loans/{loanId}/return")
    public ResponseEntity<?> returnedBook(@PathVariable Long loanId) {
        return ResponseEntity.ok(loanService.returning(loanId));

            }

    @GetMapping("/loans/user/{userId}")
    public ResponseEntity<List<LoanDTO>> listUserLoans(@PathVariable Long userId) {
    return ResponseEntity.ok(loanService.listUserLoans(userId));
    }

    }
