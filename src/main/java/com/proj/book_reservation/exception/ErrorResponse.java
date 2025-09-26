package com.proj.book_reservation.exception;

import lombok.*;


@Setter
@Getter

public class ErrorResponse {
    private int status;
    private String msg;
    private Long timeStamp;
    public ErrorResponse() {}

    public ErrorResponse(int status, String msg, Long timeStamp) {
        this.status = status;
        this.msg = msg;
        this.timeStamp = timeStamp;
    }
}
