package edu.iis.mto.blog.api.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandling {

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public void dataIntegrityException() {}
}
