package com.proj.book_reservation.dtos.user;

import java.time.LocalDateTime;

public interface UserBasicProjection {
    Long getUserId();
    String getFirstName();
    String getLastName();
    String getUsername();
    String getPhone();
    String getRole();
    LocalDateTime getCreatedAt();

}
