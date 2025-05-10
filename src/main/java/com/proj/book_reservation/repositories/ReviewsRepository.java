package com.proj.book_reservation.repositories;

import com.proj.book_reservation.entities.Book;
import com.proj.book_reservation.entities.BookReviews;
import com.proj.book_reservation.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewsRepository extends JpaRepository<BookReviews,Long> {
    public List<BookReviews> findByUser_UserId(Long userId);
    public List<BookReviews> findByBook_BookId(Long bookId);


}
