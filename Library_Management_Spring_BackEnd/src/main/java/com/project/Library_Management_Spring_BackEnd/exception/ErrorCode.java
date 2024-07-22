package com.project.Library_Management_Spring_BackEnd.exception;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized Exception"),
    INVALID_INPUT(9000, "Invalid Input"),
    UNAUTHENTICATED(9001, "Unauthenticated"),
    USER_NOT_FOUND(1000, "User not found"),
    USER_EXISTED(1001, "User existed");

    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
