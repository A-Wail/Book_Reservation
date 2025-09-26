package com.proj.book_reservation.services;

import com.proj.book_reservation.dtos.loan.LoanProjection;
import com.proj.book_reservation.dtos.loan.LoanResponse;
import com.proj.book_reservation.dtos.review.ReviewProjection;
import com.proj.book_reservation.dtos.review.ReviewsResponse;
import com.proj.book_reservation.dtos.user.*;
import com.proj.book_reservation.model.User;
import com.proj.book_reservation.exception.user.UserFoundedException;
import com.proj.book_reservation.exception.user.UserNotFoundException;
import com.proj.book_reservation.jwt.JwtService;
import com.proj.book_reservation.repositories.LoansRepository;
import com.proj.book_reservation.repositories.ReviewsRepository;
import com.proj.book_reservation.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private  final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final LoansRepository loanRepository;
    private final ReviewsRepository reviewRepository;


    public AuthenticationResp register(RegisterRequest request){
        if (userRepository.findByEmail(request.getEmail()).isEmpty())
                throw new UserFoundedException("User is Register before ");

        User user =User.builder()
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .email(request.getEmail())
                        .username(request.getUsername())
                        .phone(request.getPhone())
                        .passwordHash(passwordEncoder.encode(request.getPasswordHash()))
                        .role(request.getRole() != null ? request.getRole() : User.Role.MEMBER)
                        .build();

        userRepository.save(user);

        var token= jwtService.generateToken(user);

        logger.info("User {} registered successfully", user.getUsername());

        return AuthenticationResp.builder()
                                .token(token)
                                .build();
    }

    public AuthenticationResp authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow( ()-> new UserNotFoundException("User not found !!"));

        logger.info("User Founded");

        var token= jwtService.generateToken(user);

        return AuthenticationResp.builder()
                                .token(token)
                                .build();
    }

    public UserProfile userProfile(Long id){
        //check if user exist or not

        UserBasicProjection userProj = userRepository.getUserBasic(id);
        if (userProj == null) {
            throw new UserNotFoundException("User not found with id " + id);
        }

        List<LoanProjection> loanProjs = loanRepository.findLoanResponsesByUserId(id);
        List<ReviewProjection> reviewProjs = reviewRepository.getReviewsByUser(id);

        return UserProfile.builder()
                .userId(userProj.getUserId())
                .firstName(userProj.getFirstName())
                .lastName(userProj.getLastName())
                .username(userProj.getUsername())
                .phone(userProj.getPhone())
                .role(userProj.getRole())
                .createdAt(userProj.getCreatedAt())
                .loans(loanProjs.stream().map(lp -> LoanResponse.builder()
                        .loanId(lp.getLoanId())
                        .bookId(lp.getBookId())
                        .bookTitle(lp.getBookTitle())
                        .borrowedDate(lp.getBorrowedDate())
                        .dueDate(lp.getDueDate())
                        .returnDate(lp.getReturnDate())
                        .status(lp.getStatus())
                        .build()).toList())
                .reviews(reviewProjs.stream().map(rp -> ReviewsResponse.builder()
                        .reviewId(rp.getReviewId())
                        .bookId(rp.getBookId())
                        .bookTitle(rp.getBookTitle())
                        .rating(rp.getRating())
                        .reviewText(rp.getReviewText())
                        .createdAt(rp.getCreatedAt())
                        .build()).toList())
                .build();
    }


    public User update(Long id, UpdateUser userDTO) {

        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User not found!!"));
        if (userDTO.getFirstName() != null) user.setFirstName(userDTO.getFirstName());
        if (userDTO.getLastName() != null) user.setLastName(userDTO.getLastName());
        if (userDTO.getEmail() != null) user.setEmail(userDTO.getEmail());
        if (userDTO.getPhone() != null) user.setPhone(userDTO.getPhone());

        userRepository.save(user);
        return user;
    }



}
