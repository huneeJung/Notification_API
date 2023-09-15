package kr.co.mz.notification.common.exception.controller;

import kr.co.mz.notification.common.exception.custom.NotExistException;
import kr.co.mz.notification.common.exception.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleException(Exception e) {
        var errorDto = new ErrorDto(e.getMessage(), 500);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDto);
    }

    @ExceptionHandler(NotExistException.class)
    public ResponseEntity<ErrorDto> handleNotExistException(NotExistException e) {
        ErrorDto errorDto = new ErrorDto(e.getMessage(), 404);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException e) {
        ErrorDto errorDto = new ErrorDto("Validation Failed", 400);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }


}
