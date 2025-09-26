package com.proj.book_reservation.repositories;

import com.proj.book_reservation.dtos.loan.LoanProjection;
import com.proj.book_reservation.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LoansRepository extends JpaRepository<Loan,Long> {
    @Query("select l.loanId as loanId, b.bookId as bookId, b.title as bookTitle, l.borrowedDate as borrowedDate,  "+
    "l.dueDate as dueDate, l.returnDate as returnDate,l.status as status "+
    "from Loan l Join l.book b where l.user.userId =:userId")
    List<LoanProjection> findLoanResponsesByUserId(Long userId);
    boolean existsByBook_BookIdAndStatus(Long bookId, Loan.Status status);
    @Modifying
    @Transactional
    @Query("DELETE FROM Loan l WHERE l.book.bookId = :bookId")
    void deleteByBookId(Long bookId);

}
