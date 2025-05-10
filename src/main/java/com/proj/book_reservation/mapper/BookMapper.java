package com.proj.book_reservation.mapper;

import com.proj.book_reservation.dtos.book.AddBookDTO;
import com.proj.book_reservation.entities.Book;

public class BookMapper {
    public static AddBookDTO bookToDTO(Book book){
        AddBookDTO bookDTO =new AddBookDTO();
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setPublisher(book.getPublisher());
        bookDTO.setPublicationYear(book.getPublicationYear());
        bookDTO.setGenre(book.getGenre());
        bookDTO.setDescription(book.getDescription());
        bookDTO.setTotalCopies(book.getTotalCopies());
        bookDTO.setAvailableCopies(book.getAvailableCopies());
        return bookDTO;
    }
    public static Book toBook(AddBookDTO dtoBook){
        Book book =new Book();
        book.setIsbn(dtoBook.getIsbn());
        book.setTitle(dtoBook.getTitle());
        book.setAuthor(dtoBook.getAuthor());
        book.setPublisher(dtoBook.getPublisher());
        book.setPublicationYear(dtoBook.getPublicationYear());
        book.setGenre(dtoBook.getGenre());
        book.setDescription(dtoBook.getDescription());
        book.setTotalCopies(dtoBook.getTotalCopies());
        book.setAvailableCopies(dtoBook.getAvailableCopies());
        return book;
    }
}
