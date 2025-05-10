package com.proj.book_reservation.services;

import com.proj.book_reservation.dtos.loan.LoanDTO;
import com.proj.book_reservation.dtos.review.ReviewsDTO;
import com.proj.book_reservation.dtos.user.RegisterUserDTO;
import com.proj.book_reservation.dtos.user.UpdateUserDTO;
import com.proj.book_reservation.dtos.user.UserProfileDTo;
import com.proj.book_reservation.entities.User;
import com.proj.book_reservation.exception.user.UserFoundedException;
import com.proj.book_reservation.exception.user.UserNotFoundException;
import com.proj.book_reservation.mapper.UserMapper;
import com.proj.book_reservation.repositories.LoansRepository;
import com.proj.book_reservation.repositories.ReviewsRepository;
import com.proj.book_reservation.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;
    private LoansRepository loansRepository;
    private ReviewsRepository reviewsRepository;

    public UserService(UserRepository userRepository, LoansRepository loansRepository, ReviewsRepository reviewsRepository) {
        this.userRepository = userRepository;
        this.loansRepository = loansRepository;
        this.reviewsRepository = reviewsRepository;
    }


    public User insert(RegisterUserDTO userDTO){
        User founder= userRepository.findByUsername(userDTO.getUsername());
        if (founder != null)  throw new UserFoundedException(" User is Register before with id !!"+founder.getUserId());
        User user =UserMapper.toUser(userDTO);
        userRepository.save(user);
        return user;
    }

    public UserProfileDTo viewProfile(Long id){
        User user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(" User not found !!"));
        UserProfileDTo dto =UserProfileDTo.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .phone(user.getPhone())
                .role(user.getRole().name())
                .createdAt(user.getCreatedAt())
                .build();
     /* new UserProfileDTo();
        dto.setUserId(user.getUserId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setUsername(user.getUsername());
        dto.setPhone(user.getPhone());
        dto.setRole(user.getRole().name());
        dto.setCreatedAt(user.getCreatedAt());   */

        List<LoanDTO> loanDTOs = loansRepository.findByUser_UserId(id).stream()
                .map(loan -> {
                    LoanDTO l =LoanDTO.builder()
                                .loanId(loan.getLoanId())
                                .bookId(loan.getBook().getBookId())
                                .bookTitle(loan.getBook().getTitle())
                                .borrowedDate(loan.getBorrowedDate())
                                .dueDate(loan.getDueDate())
                                .returnDate(loan.getReturnDate())
                                .status(loan.getStatus().name())
                                .build();
                                return l;
                }).collect(Collectors.toList());
        dto.setLoans(loanDTOs);

        List<ReviewsDTO> reviewDTOs = reviewsRepository.findByUser_UserId(id).stream()
                .map(review -> {
                    ReviewsDTO r = ReviewsDTO.builder()
                                   .reviewId(review.getReviewId())
                                   .bookId(review.getBook().getBookId())
                                   .bookTitle(review.getBook().getTitle())
                                   .rating(review.getRating())
                                   .reviewText(review.getReviewText())
                                   .createdAt(review.getCreatedAt())
                                   .build();
                    return r;
                }).collect(Collectors.toList());
        dto.setReviews(reviewDTOs);

        return dto;
    }


    public User update(Long id, UpdateUserDTO userDTO) {

        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User not found!!"));
        if (userDTO.getFirstName() != null) user.setFirstName(userDTO.getFirstName());
        if (userDTO.getLastName() != null) user.setLastName(userDTO.getLastName());
        if (userDTO.getEmail() != null) user.setEmail(userDTO.getEmail());
        if (userDTO.getPhone() != null) user.setPhone(userDTO.getPhone());

        userRepository.save(user);
        return user;
    }





}
