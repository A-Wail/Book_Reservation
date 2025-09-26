package com.proj.book_reservation.dtos.user;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class AuthenticationResp {

    private String token;
}
