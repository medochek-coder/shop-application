package com.medochek.shopapp.dto;

public enum ApplicationErrorTypes {
    PRODUCT_ID_NOT_FOUND(1, "Product with this id has not found"),
    BASKET_ID_NOT_FOUND(2, "Basket with this id has not found");

    private String message;
    private int code;

    ApplicationErrorTypes(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
