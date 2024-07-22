package com.project.Library_Management_Spring_BackEnd.exception;

import com.project.Library_Management_Spring_BackEnd.dto.request.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<ApiResponse> handleAppException(AppException e) {
        ErrorCode errorCode = e.getErrorCode();
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ApiResponse> handleBindException (BindException e){

        ApiResponse apiResponse = new ApiResponse();
        String message = "Request không hợp lệ!";

        if(e.getBindingResult().hasErrors()){
            message = e.getBindingResult().getAllErrors().getFirst().getDefaultMessage();
        }

        apiResponse.setCode(ErrorCode.INVALID_INPUT.getCode());
        apiResponse.setMessage(message);

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception e){
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        apiResponse.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage() + ": " + e.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }
}
