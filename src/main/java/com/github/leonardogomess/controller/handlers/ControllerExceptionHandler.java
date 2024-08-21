package com.github.leonardogomess.controller.handlers;


import com.github.leonardogomess.dto.CustomErrorDTO;
import com.github.leonardogomess.dto.ValidationErrorDTO;
import com.github.leonardogomess.service.ResourceNotFoundException;
import com.github.leonardogomess.service.exception.DataBaseException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.time.Instant;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomErrorDTO> resourceNotFound(ResourceNotFoundException exception , HttpServletRequest request){

        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomErrorDTO errorDTO = new CustomErrorDTO(Instant.now().toString(),
                status.value() , exception.getMessage(),request.getRequestURI());
        return  ResponseEntity.status(status).body(errorDTO);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorDTO> methodArgumentNotValidation(MethodArgumentNotValidException e , HttpServletRequest request){

        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO(Instant.now().toString(),
                status.value() , "Dados Invalidos",request.getRequestURI());
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()){
            validationErrorDTO.addError(fieldError.getField(),fieldError.getDefaultMessage());
        }
        return  ResponseEntity.status(status).body(validationErrorDTO);
    }

    @ExceptionHandler(DataBaseException.class)
    public  ResponseEntity<CustomErrorDTO> databaseException(DataBaseException e , HttpServletRequest request){

        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomErrorDTO err = new CustomErrorDTO(Instant.now().toString(),
                status.value(),e.getMessage() , request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

}
