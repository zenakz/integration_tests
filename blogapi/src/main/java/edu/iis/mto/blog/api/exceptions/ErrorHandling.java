package edu.iis.mto.blog.api.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import edu.iis.mto.blog.domain.errors.DomainError;

@ControllerAdvice
public class ErrorHandling {

    private final static Logger logger = LoggerFactory.getLogger(ErrorHandling.class);

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(code = HttpStatus.CONFLICT, reason = "data conflict")
    public void dataIntegrityException(DataIntegrityViolationException exc) {
        logger.error(exc.getMessage());
    }

    @ExceptionHandler(DomainError.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "domain error encountered")
    public void domainError(DomainError exc) {
        logger.error(exc.getMessage());
    }
}
