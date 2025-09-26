package com.proj.book_reservation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private String isbn;
    private String publisher;
    private String title;
    private String Author;
    @Column(name = "publication_year")
    private Long publicationYear;
    private String genre;
    private String description;
    @Column(name = "total_copies")
    private Long totalCopies;
    @Column(name = "available_copies")
    private Long availableCopies;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;


}
