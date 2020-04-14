package hr.rezek.flightschool.model;

import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value  = { DataIntegrityViolationException.class})
    protected ResponseEntity<Object> handleDataIntegrity( DataIntegrityViolationException ex, WebRequest request) {
        return handleExceptionInternal(ex, "Error occurred while saving data.", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value  = { Exception.class})
    protected ResponseEntity<Object> handleAllExceptions( Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Something went wrong.", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
