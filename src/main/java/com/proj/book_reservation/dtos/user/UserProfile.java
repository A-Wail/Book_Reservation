package com.proj.book_reservation.dtos.user;

import com.proj.book_reservation.dtos.loan.LoanResponse;
import com.proj.book_reservation.dtos.review.ReviewsResponse;
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
public class UserProfile {
    private Long userId;
    private String username;
    private String firstName;
    private String lastName;
    private String phone;
    private LocalDateTime createdAt;
    private String role;
    private List<LoanResponse> loans;
    private  List<ReviewsResponse> reviews;

}
