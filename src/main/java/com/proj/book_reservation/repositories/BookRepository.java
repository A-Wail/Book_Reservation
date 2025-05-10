package com.proj.book_reservation.repositories;

import com.proj.book_reservation.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    public Book findByIsbn(String isbn);
}
