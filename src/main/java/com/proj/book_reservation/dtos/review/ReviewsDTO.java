package com.proj.book_reservation.dtos.review;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Builder
@Setter
@Getter
public class ReviewsDTO {
    private Long reviewId;
    private Long bookId;
    private String bookTitle;
    private Integer rating;
    private String reviewText;
    private LocalDateTime createdAt;
}
