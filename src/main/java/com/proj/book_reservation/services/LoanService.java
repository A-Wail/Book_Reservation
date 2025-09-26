package com.proj.book_reservation.services;

import com.proj.book_reservation.dtos.loan.LoanResponse;
import com.proj.book_reservation.model.Book;
import com.proj.book_reservation.model.Loan;
import com.proj.book_reservation.model.User;
import com.proj.book_reservation.exception.book.BookNotFoundException;
import com.proj.book_reservation.exception.loans.NotAvailableCopiesExceptions;
import com.proj.book_reservation.exception.review.ReviewNotFoundException;
import com.proj.book_reservation.exception.user.UserNotFoundException;
import com.proj.book_reservation.repositories.BookRepository;
import com.proj.book_reservation.repositories.LoansRepository;
import com.proj.book_reservation.repositories.UserRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Data
public class LoanService {

    private final LoansRepository loanRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public LoanResponse loanBook(Long userId, Long bookId){
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
        return convertToLoanResponse(loan);
    }


    public LoanResponse returning(Long loanId) {
        //check if Loan exist
        Loan existLoan = loanRepository.findById(loanId).
                orElseThrow(() -> new ReviewNotFoundException("Not find Loan with the referred ID !!"));
        //Set returned data of book
        existLoan.setReturnDate(LocalDate.now());
        existLoan.getBook().setAvailableCopies(existLoan.getBook().getAvailableCopies()+1);
        //Check if returned time is after or before due date
        if (existLoan.getReturnDate().isAfter(existLoan.getDueDate())) {
            existLoan.setStatus(Loan.Status.OVERDUE);
        }else {
            existLoan.setStatus(Loan.Status.RETURNED);
        }
        loanRepository.save(existLoan);

        return convertToLoanResponse(existLoan);
    }

    public List<LoanResponse> listUserLoans(Long userId){
        //Check if User is found or not exist
        if (userRepository.findById(userId).isEmpty())
                throw new UserNotFoundException("User Not Found !!");

        return loanRepository.findLoanResponsesByUserId(userId).stream()
                .map(loan -> LoanResponse.builder()
                        .loanId(loan.getLoanId())
                        .bookId(loan.getBookId())
                        .bookTitle(loan.getBookTitle())
                        .borrowedDate(loan.getBorrowedDate())
                        .dueDate(loan.getDueDate())
                        .returnDate(loan.getReturnDate())
                        .status(loan.getStatus())
                        .build()).toList();
    }

    private LoanResponse convertToLoanResponse(Loan loan) {
        return LoanResponse.builder()
                .loanId(loan.getLoanId())
                .bookId(loan.getBook().getBookId())
                .bookTitle(loan.getBook().getTitle())
                .borrowedDate(loan.getBorrowedDate())
                .dueDate(loan.getDueDate())
                .returnDate(loan.getReturnDate())
                .status(loan.getStatus().name())
                .build();
    }


}
