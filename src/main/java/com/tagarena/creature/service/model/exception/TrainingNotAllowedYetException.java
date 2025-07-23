package com.tagarena.creature.service.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
public class TrainingNotAllowedYetException extends RuntimeException {

    public TrainingNotAllowedYetException(String message) {
        super(message);
    }
}
