package com.eazybytes.acounts.exception;

import com.eazybytes.acounts.DTO.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException exception, WebRequest webRequest) {
        {
            ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                    webRequest.getDescription(false),
                    HttpStatus.BAD_REQUEST,
                    exception.getMessage(),
                    LocalDateTime.now()
            );
            return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);

        }
    }
    @ExceptionHandler(ResponseNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResponseNotFoundException(ResponseNotFoundException exception, WebRequest webRequest) {
        {
            ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                    webRequest.getDescription(false),
                    HttpStatus.NOT_FOUND,
                    exception.getMessage(),
                    LocalDateTime.now()
            );
            return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);

        }
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGlobalException(Exception exception, WebRequest webRequest) {
        {
            ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                    webRequest.getDescription(false),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    exception.getMessage(),
                    LocalDateTime.now()
            );
            return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}