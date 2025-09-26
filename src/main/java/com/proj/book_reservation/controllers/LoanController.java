package com.proj.book_reservation.controllers;

import com.proj.book_reservation.dtos.loan.LoanResponse;
import com.proj.book_reservation.model.User;
import com.proj.book_reservation.services.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/loans")
public class LoanController {
    private final LoanService loanService;

    @PostMapping("/{bookId}")
    public ResponseEntity<?> borrow(@AuthenticationPrincipal UserDetails userDetails,
                                    @PathVariable Long bookId){
        User user =(User)userDetails;

        return ResponseEntity.ok(loanService.loanBook(user.getUserId(),bookId));
    }

    @PutMapping("/return/{loanId}")
    public ResponseEntity<?> returnedBook(@PathVariable Long loanId) {
        return ResponseEntity.ok(loanService.returning(loanId));

            }

    @GetMapping("/user")
    public ResponseEntity<List<LoanResponse>> listUserLoans(@AuthenticationPrincipal UserDetails userDetails) {
        User user =(User)userDetails;
        return ResponseEntity.ok(loanService.listUserLoans(user.getUserId()));
    }

    }
