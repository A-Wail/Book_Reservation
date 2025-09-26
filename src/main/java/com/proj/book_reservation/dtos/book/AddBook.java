package com.proj.book_reservation.dtos.book;

import lombok.Data;

@Data
public class AddBook {
    private String isbn;
    private String publisher;
    private String title;
    private String Author;
    private Long publicationYear;
    private String genre;
    private String description;
    private Long totalCopies;
    private Long availableCopies;

}
