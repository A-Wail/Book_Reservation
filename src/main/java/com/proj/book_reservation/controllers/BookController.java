package com.proj.book_reservation.controllers;

import com.proj.book_reservation.dtos.book.AddBookDTO;
import com.proj.book_reservation.services.BookService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<?> insertBook(@PathVariable(required = true) Long userId,@RequestBody AddBookDTO book){
        return ResponseEntity.ok(bookService.insertBook(userId,book));
    }

    @PutMapping("/{userId}/{bookId}")
    public ResponseEntity<?> update(@PathVariable(required = true)Long userId,
                                    @PathVariable Long bookId,@RequestBody AddBookDTO newBook) {
       return ResponseEntity.ok(bookService.update(userId,bookId,newBook));
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<?> bookDetails(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.bookDetails(bookId));
    }

    @GetMapping("")
    public ResponseEntity<?> viewAllBooks(){
        return ResponseEntity.ok(bookService.viewAllBooks());
    }

    @DeleteMapping("/{bookId}/{userId}")
    public String delete( @PathVariable Long bookId,
                          @PathVariable Long userId
                        ) {
        bookService.delete(userId,bookId);
        return "The Book is deleted with id "+bookId;
    }

    }
