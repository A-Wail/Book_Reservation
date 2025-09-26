package com.proj.book_reservation.controllers;

import com.proj.book_reservation.dtos.review.InputReview;
import com.proj.book_reservation.model.User;
import com.proj.book_reservation.services.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @PostMapping("/{bookId}")
    public ResponseEntity<?> inputReview(@PathVariable Long bookId,
                                         @AuthenticationPrincipal UserDetails userDetails,
                                         @RequestBody InputReview review) {
        User user =(User)userDetails;
        return ResponseEntity.ok(reviewService.inputReview(bookId, user.getUserId(), review));
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<?> updateReview(@PathVariable Long reviewId,
                                          @AuthenticationPrincipal UserDetails userDetails,
                                          @RequestBody InputReview review) {
        User user =(User)userDetails;
        return ResponseEntity.ok(reviewService.updateReview(reviewId,user.getUserId(), review));
    }

    @DeleteMapping("/{reviewId}")
    public String  deleteReview(@PathVariable Long reviewId,
                                @AuthenticationPrincipal UserDetails userDetails) {
        User user =(User) userDetails;
        reviewService.deleteReview(reviewId,user.getUserId());
        return "Review is deleted with id :"+reviewId;
    }

    }
