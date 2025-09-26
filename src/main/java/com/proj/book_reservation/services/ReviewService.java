package com.proj.book_reservation.services;

import com.proj.book_reservation.dtos.review.InputReview;
import com.proj.book_reservation.model.Book;
import com.proj.book_reservation.model.BookReviews;
import com.proj.book_reservation.model.User;
import com.proj.book_reservation.exception.review.ReviewCanNotModifyException;
import com.proj.book_reservation.exception.review.ReviewNotFoundException;
import com.proj.book_reservation.exception.book.BookNotFoundException;
import com.proj.book_reservation.exception.user.UserNotFoundException;
import com.proj.book_reservation.repositories.BookRepository;
import com.proj.book_reservation.repositories.ReviewsRepository;
import com.proj.book_reservation.repositories.UserRepository;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Data
public class ReviewService {

    private final ReviewsRepository reviewRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public ResponseEntity<?> inputReview(Long bookId, Long userId, InputReview review){
        //Check if User is found or not exist
        User userThatInsertReview =userRepository.findById(userId).
                orElseThrow(()->new UserNotFoundException("User Not Found !!"));
        //Check if book is found or not exist
        Book bookReviewed=bookRepository.findById(bookId).
                orElseThrow(()->new BookNotFoundException("Book Not Found !!"));
        //Inter the review in database with id and user that write that review and the book reviewed
        BookReviews bookReview = BookReviews.builder()
                                .user(userThatInsertReview)
                                .book(bookReviewed)
                                .rating(review.getRating())
                                .reviewText(review.getReviewText())
                                .build();

        return  ResponseEntity.ok(reviewRepository.save(bookReview));
    }

    public ResponseEntity<?> updateReview(Long reviewId,Long userId, InputReview review) {
        //Check if Review is found or not exist
        BookReviews oldReview =reviewRepository.findById(reviewId).
                orElseThrow(()->new ReviewNotFoundException("Review Not Found !!"));

        //Check if this user write that review or not
        if (oldReview.getUser().getUserId() != userId)
            throw new ReviewCanNotModifyException("You can change your review only");

        //check Values not null before update
        if (review.getRating() != null) oldReview.setRating(review.getRating());
        if (review.getReviewText() != null) oldReview.setReviewText(review.getReviewText());
        return  ResponseEntity.ok(reviewRepository.save(oldReview));
    }


    public void  deleteReview(Long reviewId,Long userId) {
        //check if review exist or not before delete
        BookReviews review = reviewRepository.findById(reviewId).
                orElseThrow(() -> new ReviewNotFoundException("Review Not Found !!"));
        if (review.getUser().getUserId() != userId)
            throw new ReviewCanNotModifyException("You can delete your review only");
        reviewRepository.deleteById(reviewId);
    }





}
