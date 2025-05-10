package com.proj.book_reservation.dtos.review;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InputReviewDTO {
    private Integer rating;
    private String reviewText;
}
