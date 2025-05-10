package com.proj.book_reservation.dtos.book;

import com.proj.book_reservation.dtos.review.ReviewsDTO;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class BookDetailsDTO {
    private Long bookId;
    private String isbn;
    private String publisher;
    private String title;
    private String author;
    private Long publicationYear;
    private String genre;
    private String description;
    private Long totalCopies;
    private Long availableCopies;
    private LocalDateTime createdAt;
    private List<ReviewsDTO> reviews;
}
