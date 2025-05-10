package com.proj.book_reservation.controllers;

import com.proj.book_reservation.dtos.review.InputReviewDTO;
import com.proj.book_reservation.services.ReviewService;
import com.proj.book_reservation.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @PostMapping("/{bookId}/{userId}")
    public ResponseEntity<?> inputReview(@PathVariable Long bookId, @PathVariable Long userId,
                                         @RequestBody InputReviewDTO review) {
        return ResponseEntity.ok(reviewService.inputReview(bookId, userId, review));
    }

    @PutMapping("/{reviewId}/{userId}")
    public ResponseEntity<?> updateReview(@PathVariable Long reviewId,@PathVariable Long UserId,
                                          @RequestBody InputReviewDTO review) {
        return ResponseEntity.ok(reviewService.updateReview(reviewId,UserId, review));
    }

    @DeleteMapping("/{reviewId}/{userId}")
    public String  deleteReview(@PathVariable Long reviewId,@PathVariable Long userId) {
        reviewService.deleteReview(reviewId,userId);
        return "Review is deleted with id :"+reviewId;
    }

    }
