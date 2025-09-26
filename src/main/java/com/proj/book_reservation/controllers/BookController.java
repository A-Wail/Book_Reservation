package com.proj.book_reservation.controllers;

import com.proj.book_reservation.dtos.book.AddBook;
import com.proj.book_reservation.model.User;
import com.proj.book_reservation.exception.user.UserNotAdminException;
import com.proj.book_reservation.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/admin/addBook")
    public ResponseEntity<?> insertBook(@AuthenticationPrincipal UserDetails userDetails,
                                        @RequestBody AddBook book){
        User user =(User)userDetails;

        if (!user.getRole().equals(User.Role.ADMIN)) {
            throw new UserNotAdminException("You're not authorized to add books.");
        }

        return ResponseEntity.ok(bookService.insertBook(user.getUserId(),book));
    }


    @PutMapping("/admin/updateBook/{bookId}")
    public ResponseEntity<?> update(@AuthenticationPrincipal UserDetails userDetails,
                                    @PathVariable Long bookId,@RequestBody AddBook newBook) {
        User user =(User)userDetails;

        if (!user.getRole().equals(User.Role.ADMIN)) {
            throw new UserNotAdminException("You're not authorized to add books.");
        }
       return ResponseEntity.ok(bookService.update(user.getUserId(),bookId,newBook));
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<?> bookDetails(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.bookDetails(bookId));
    }

    @GetMapping("")
    public ResponseEntity<?> viewAllBooks(){
        return ResponseEntity.ok(bookService.viewAllBooks());
    }

    @DeleteMapping("/admin/deleteBook/{bookId}")
    public String delete( @PathVariable Long bookId,
                          @AuthenticationPrincipal UserDetails userDetails
                        ) {
        User user =(User)userDetails;

        if (!user.getRole().equals(User.Role.ADMIN)) {
            throw new UserNotAdminException("You're not authorized to add books.");
        }
        bookService.delete(user.getUserId(),bookId);
        return "The Book is deleted with id "+bookId;
    }

    }
