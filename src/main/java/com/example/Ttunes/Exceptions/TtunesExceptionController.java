package com.example.Ttunes.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.time.ZonedDateTime;

@ControllerAdvice
public class TtunesExceptionController {
    @ExceptionHandler(value = HttpClientErrorException.class)
    public ResponseEntity<Object> httpClientException(HttpClientErrorException e) {
        ExceptionDTO exceptionDTO = new ExceptionDTO("Rest Template URI my not be reachable: "+e.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = NullJukeListException.class)
    public ResponseEntity<Object> jukesListException(NullJukeListException e) {
        ExceptionDTO exceptionDTO = new ExceptionDTO("External Juke service retuned null: "+e.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = NullSettingsListException.class)
    public ResponseEntity<Object> settingsListException(NullSettingsListException e) {
        ExceptionDTO exceptionDTO = new ExceptionDTO("External settings service retuned null: "+e.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }
}
