package com.proj.book_reservation.model;

import com.proj.book_reservation.exception.review.IllegalRatingException;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookReviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long reviewId;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name ="book_id",nullable = false )
    private Book book;
    private Integer rating;
    private String reviewText;
    @CreationTimestamp
    private LocalDateTime createdAt;


    public void setRating(Integer rating){
        if (rating <1 || rating >5){
            throw new IllegalRatingException("Rating must be between 1 and 5");
        }
        this.rating=rating;
    }


}
