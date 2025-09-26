package com.proj.book_reservation.dtos.review;

import java.time.LocalDateTime;

public interface ReviewProjection {
    Long getReviewId();
    Long getBookId();
    String getBookTitle();
    Integer getRating();
    String getReviewText();
    LocalDateTime getCreatedAt();
}
