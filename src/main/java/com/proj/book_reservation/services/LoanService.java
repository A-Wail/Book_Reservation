package com.proj.book_reservation.services;

import com.proj.book_reservation.dtos.loan.LoanDTO;
import com.proj.book_reservation.dtos.review.InputReviewDTO;
import com.proj.book_reservation.entities.Book;
import com.proj.book_reservation.entities.BookReviews;
import com.proj.book_reservation.entities.Loan;
import com.proj.book_reservation.entities.User;
import com.proj.book_reservation.exception.book.BookNotFoundException;
import com.proj.book_reservation.exception.loans.NotAvailableCopiesExceptions;
import com.proj.book_reservation.exception.review.ReviewNotFoundException;
import com.proj.book_reservation.exception.user.UserNotFoundException;
import com.proj.book_reservation.repositories.BookRepository;
import com.proj.book_reservation.repositories.LoansRepository;
import com.proj.book_reservation.repositories.ReviewsRepository;
import com.proj.book_reservation.repositories.UserRepository;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class LoanService {

    private final LoansRepository loanRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public Loan selectBook(Long userId, Long bookId){
        //Check if User is found or not exist
        User userBorrowedBook = userRepository.findById(userId).
                orElseThrow(()->new UserNotFoundException("User Not Found !!"));
        //Check if book is found or not exist
        Book bookBorrowed = bookRepository.findById(bookId).
                orElseThrow(()->new BookNotFoundException("Book Not Found !!"));
        //Check if exist an available copies of Book user want to loan
        if (bookBorrowed.getAvailableCopies() < 1)
            throw new NotAvailableCopiesExceptions("Not Available Copies of this Book");
        //Generate loan with user and book
        Loan loan =Loan.builder()
                    .user(userBorrowedBook)
                    .book(bookBorrowed)
                    .status(Loan.Status.ACTIVE)
                    .dueDate(LocalDate.now().plusDays(14))
                    .build();
        //Update AvailableCopies After book returned
        bookBorrowed.setAvailableCopies(bookBorrowed.getAvailableCopies() - 1);
        loanRepository.save(loan);
        return loan;
    }


    public Loan  returning(Long loanId) {
        //check if Loan exist
        Loan loan = loanRepository.findById(loanId).
                orElseThrow(() -> new ReviewNotFoundException("Not find Loan with the referred ID !!"));
        //Set returned data of book
        loan.setReturnDate(LocalDate.now());
        loan.getBook().setAvailableCopies(loan.getBook().getAvailableCopies()+1);
        //Check if returned time is after or before due date
        if (loan.getReturnDate().isAfter(loan.getDueDate())) {
            loan.setStatus(Loan.Status.OVERDUE);
        }else {
            loan.setStatus(Loan.Status.RETURNED);
        }
        loanRepository.save(loan);
        return loan;
    }

    public List<LoanDTO> listUserLoans(Long userId){
        //Check if User is found or not exist
        User user1 = userRepository.findById(userId).
                orElseThrow(()->new UserNotFoundException("User Not Found !!"));
        List<LoanDTO> userLoans = loanRepository.findByUser_UserId(userId).stream()
                                .map(loan -> {
                                    LoanDTO l =LoanDTO.builder()
                                    .loanId(loan.getLoanId())
                                    .bookId(loan.getBook().getBookId())
                                    .bookTitle(loan.getBook().getTitle())
                                    .borrowedDate(loan.getBorrowedDate())
                                    .dueDate(loan.getDueDate())
                                    .returnDate(loan.getReturnDate())
                                    .status(String.valueOf(loan.getStatus()))
                                    .build();
                                    return l;
                                }).collect(Collectors.toList());
        return userLoans;
    }

}
