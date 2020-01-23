package com.medochek.shopapp.dto;

import lombok.Data;

@Data
public class ErrorResponseBody {
    private int code;
    private String message;

    public ErrorResponseBody() {
    }

    public ErrorResponseBody(ApplicationErrorTypes message, String customMessage) {
        this.code = message.getCode();
        this.message = customMessage;
    }

    public ErrorResponseBody(ApplicationErrorTypes message) {
        this.code = message.getCode();
        this.message = message.getMessage();
    }
}
