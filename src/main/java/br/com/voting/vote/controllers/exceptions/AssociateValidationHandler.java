package br.com.voting.vote.controllers.exceptions;

import br.com.voting.vote.services.exceptions.ResourceValidationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class AssociateValidationHandler {
    @ExceptionHandler(ResourceValidationException.class)
    public ResponseEntity<StandardError> associateValidationException(ResourceValidationException e, HttpServletRequest request){
        String error = "Associate validation error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

}
