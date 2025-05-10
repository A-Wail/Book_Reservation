package com.proj.book_reservation.controlleradvice;

import com.proj.book_reservation.exception.ErrorResponse;
import com.proj.book_reservation.exception.book.BookIsFoundedException;
import com.proj.book_reservation.exception.book.BookNotFoundException;
import com.proj.book_reservation.exception.loans.LoanActiveException;
import com.proj.book_reservation.exception.loans.NotAvailableCopiesExceptions;
import com.proj.book_reservation.exception.review.IllegalRatingException;
import com.proj.book_reservation.exception.review.ReviewNotFoundException;
import com.proj.book_reservation.exception.user.UserFoundedException;
import com.proj.book_reservation.exception.user.UserNotAdminException;
import com.proj.book_reservation.exception.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandle {


    @ExceptionHandler(UserFoundedException.class)
    public ResponseEntity<?> handleUserFoundException(UserFoundedException ex){
        ErrorResponse error=new ErrorResponse(HttpStatus.FOUND.value(),ex.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException ex){
        ErrorResponse error=new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UserNotAdminException.class)
    public ResponseEntity<?> handleUserNotAdminException(UserNotAdminException ex){
        ErrorResponse error=new ErrorResponse(HttpStatus.BAD_REQUEST.value(),ex.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BookIsFoundedException.class)
    public ResponseEntity<?> handleBookIsFoundedException(BookIsFoundedException ex){
        ErrorResponse error=new ErrorResponse(HttpStatus.BAD_REQUEST.value(),ex.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<?> handleBookNotFoundException(BookNotFoundException ex){
        ErrorResponse error=new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(IllegalRatingException.class)
    public ResponseEntity<?> handleIllegalRatingException(IllegalRatingException ex){
        ErrorResponse error=new ErrorResponse(HttpStatus.BAD_REQUEST.value(),ex.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<?> handleReviewNotFoundException(ReviewNotFoundException ex){
        ErrorResponse error=new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NotAvailableCopiesExceptions.class)
    public ResponseEntity<?> handleNotAvailableCopiesExceptions(NotAvailableCopiesExceptions ex){
        ErrorResponse error=new ErrorResponse(HttpStatus.BAD_REQUEST.value(),ex.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(LoanActiveException.class)
    public ResponseEntity<?> handleLoanActiveException(LoanActiveException ex){
        ErrorResponse error=new ErrorResponse(HttpStatus.NOT_ACCEPTABLE.value(),ex.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);
    }


}
