package com.decasecure.decsecure.exceptions;

import com.decasecure.decsecure.dto.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {CustomException.class})
    public ResponseEntity<ApiResponse<?>> handleCustomException(CustomException exception) {



        return ResponseEntity.status(exception.getHttpStatus()).body(ApiResponse.builder()
                .message(exception.getMessage())
                .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ApiResponse<?>> handleGlobalExceptions(MethodArgumentNotValidException ex) {
//        String[] errors = ex.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toArray(String[]::new);
//
//
//        ApiResponse<Object> response = ApiResponse.builder()
//                .isSuccessful(false)
//                .statusMessage("An error occurred, check message below")
//                .data(errorDetails)
//                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
