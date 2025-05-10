package com.proj.book_reservation.services;

import com.proj.book_reservation.dtos.book.AddBookDTO;
import com.proj.book_reservation.dtos.book.BookDetailsDTO;
import com.proj.book_reservation.dtos.review.ReviewsDTO;
import com.proj.book_reservation.entities.Book;
import com.proj.book_reservation.entities.Loan;
import com.proj.book_reservation.entities.User;
import com.proj.book_reservation.exception.book.BookIsFoundedException;
import com.proj.book_reservation.exception.book.BookNotFoundException;
import com.proj.book_reservation.exception.loans.LoanActiveException;
import com.proj.book_reservation.exception.user.UserNotAdminException;
import com.proj.book_reservation.exception.user.UserNotFoundException;
import com.proj.book_reservation.mapper.BookMapper;
import com.proj.book_reservation.repositories.BookRepository;
import com.proj.book_reservation.repositories.LoansRepository;
import com.proj.book_reservation.repositories.ReviewsRepository;
import com.proj.book_reservation.repositories.UserRepository;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final LoansRepository loansRepository;
    private final ReviewsRepository reviewsRepository;


    public Book insertBook(Long id, AddBookDTO book){
        User admin =userRepository.findById(id).orElse(null);

        if (admin == null) {
            throw  new UserNotFoundException("User Not Found !!");
        }
        else if (admin.getRole() == User.Role.MEMBER) {
            throw new UserNotAdminException("Sorry can't change,you aren't an Admin!!");
        }else if (bookRepository.findByIsbn(book.getIsbn()) != null) {
            Book existBook=bookRepository.findByIsbn(book.getIsbn());
            throw new BookIsFoundedException("Book definitely Exists with id " +existBook.getBookId());
        }
        Book newBook=BookMapper.toBook(book);
        bookRepository.save(newBook);
        return newBook;
    }


    public Book update(Long userId,Long bookId, AddBookDTO newBook){

        Book oldBook=bookRepository.findById(bookId).orElseThrow(()-> new BookNotFoundException("Book not found!!"));

        User admin =userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User Not Founded  !!"));

        if (admin.getRole() == User.Role.MEMBER) throw new UserNotAdminException("Modify Not Allowed!!");

        if (newBook.getIsbn() != null){oldBook.setIsbn(newBook.getIsbn());}
        if (newBook.getAuthor() != null){oldBook.setAuthor(newBook.getAuthor());}
        if (newBook.getGenre() != null){oldBook.setGenre(newBook.getGenre());}
        if (newBook.getDescription() != null){oldBook.setDescription(newBook.getDescription());}
        if (newBook.getAvailableCopies() != null){oldBook.setAvailableCopies(newBook.getAvailableCopies());}
        if (newBook.getPublisher() != null){oldBook.setPublisher(newBook.getPublisher());}
        if (newBook.getTitle() != null){oldBook.setTitle(newBook.getTitle());}
        if (newBook.getPublicationYear() != null){oldBook.setPublicationYear(newBook.getPublicationYear());}
        if (newBook.getTotalCopies() != null){oldBook.setTotalCopies(newBook.getTotalCopies());}

        bookRepository.save(oldBook);
        return oldBook;
    }

    public ResponseEntity<?> bookDetails(Long bookId){
        Book book=bookRepository.findById(bookId).orElseThrow(()->
                                                    new BookNotFoundException("Book not found!!"));

        BookDetailsDTO bookDetails =BookDetailsDTO.builder()
                                    .bookId(book.getBookId())
                                    .isbn(book.getIsbn())
                                    .publisher(book.getPublisher())
                                    .title(book.getTitle())
                                    .author(book.getAuthor())
                                    .publicationYear(book.getPublicationYear())
                                    .genre(book.getGenre())
                                    .description(book.getDescription())
                                    .totalCopies(book.getTotalCopies())
                                    .availableCopies(book.getAvailableCopies())
                                    .createdAt(book.getCreatedAt())
                                    .build();
        //List Reviews that book has
        List<ReviewsDTO> reviews =reviewsRepository.findByBook_BookId(bookId).stream().
                map(review->{
                    ReviewsDTO r = ReviewsDTO.builder()
                                    .bookId(review.getBook().getBookId())
                                    .reviewId(review.getReviewId())
                                    .bookTitle(review.getBook().getTitle())
                                    .reviewText(review.getReviewText())
                                    .createdAt(review.getCreatedAt())
                                    .build();
                    return r;
                }).collect(Collectors.toList());
        bookDetails.setReviews(reviews);

        return ResponseEntity.ok(bookDetails);
    }

    public ResponseEntity<List<Book>> viewAllBooks(){
        return ResponseEntity.ok(bookRepository.findAll());
    }

    public void delete(Long bookId, Long userId){
        Book book=bookRepository.findById(bookId).orElseThrow(()->
                                                  new BookNotFoundException("Book not found!!"));
        User admin =userRepository.findById(userId).orElseThrow(()->
                new UserNotFoundException("User Not Founded  !!"));

        if (admin.getRole() == User.Role.MEMBER) throw new UserNotAdminException("Modify Not Allowed!!");

        if (loansRepository.existsByBook_BookIdAndStatus(bookId,Loan.Status.ACTIVE) )
            throw new LoanActiveException("This book has loan copies Can't delete !");

        loansRepository.deleteByBookId(bookId);

        bookRepository.delete(book);
    }








}
