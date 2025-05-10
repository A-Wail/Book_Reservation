package com.proj.book_reservation.dtos.user;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.proj.book_reservation.dtos.loan.LoanDTO;
import com.proj.book_reservation.dtos.review.ReviewsDTO;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Setter
@Getter
@JsonPropertyOrder({ "userId","fname", "lname",  "username", "phone", "createdAt", "role", "loans", "reviews" })
public class UserProfileDTo {
    private Long userId;
    private String username;
    private String firstName;
    private String lastName;
    private String phone;
    private LocalDateTime createdAt;
    private String role;
    private List<LoanDTO> loans;
    private  List<ReviewsDTO> reviews;

}
