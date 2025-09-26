package com.proj.book_reservation.repositories;

import com.proj.book_reservation.dtos.review.ReviewProjection;
import com.proj.book_reservation.model.BookReviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewsRepository extends JpaRepository<BookReviews,Long> {

    @Query(value = """
        SELECT r.review_id as reviewId, rb.book_id as bookId, rb.title as bookTitle,
               r.rating as rating, r.review_text as reviewText, r.created_at as createdAt
        FROM book_reviews r
        JOIN book rb ON r.book_id = rb.book_id
        WHERE r.user_id = :userId
    """, nativeQuery = true)
    List<ReviewProjection> getReviewsByUser(@Param("userId") Long userId);
    public List<BookReviews> findByBook_BookId(Long bookId);


}
