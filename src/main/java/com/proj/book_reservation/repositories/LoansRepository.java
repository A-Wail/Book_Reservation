package com.proj.book_reservation.repositories;

import com.proj.book_reservation.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LoansRepository extends JpaRepository<Loan,Long> {
     List<Loan> findByUser_UserId(Long userId);
    boolean existsByBook_BookIdAndStatus(Long bookId, Loan.Status status);
    @Modifying
    @Transactional
    @Query("DELETE FROM Loan l WHERE l.book.bookId = :bookId")
    void deleteByBookId(Long bookId);

}
