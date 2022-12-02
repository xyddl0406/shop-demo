package com.crewmate.shopapicommon.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.crewmate.shopapicommon.model.ResponseMessage;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class RestApiControllerAdvice {
    
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseMessage> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        ResponseMessage rm = new ResponseMessage();
        rm.setResultStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        rm.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        
        
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors()
                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
        
        rm.setData(errors);
        log.error("methodArgumentNotValidExceptionHandler : {}", errors);
        
        return new ResponseEntity<ResponseMessage>(rm, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseMessage> commonExceptionHandler(Exception e) {
        ResponseMessage rm = new ResponseMessage();
        rm.setResultStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        rm.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        rm.setData(e.getMessage());
        
        log.error("commonExceptionHandler : {}", e);
        
        return new ResponseEntity<ResponseMessage>(rm, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
