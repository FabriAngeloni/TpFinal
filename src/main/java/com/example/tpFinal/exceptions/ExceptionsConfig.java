package com.example.tpFinal.exceptions;

import com.example.tpFinal.dto.response.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsConfig {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validacionErronea(MethodArgumentNotValidException ex){
        ErrorDto errorDto = new ErrorDto(400,ex.getFieldError().getDefaultMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }
}
