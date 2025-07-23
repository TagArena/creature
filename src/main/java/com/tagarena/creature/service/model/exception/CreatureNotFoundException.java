package com.tagarena.creature.service.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CreatureNotFoundException extends RuntimeException {

	public CreatureNotFoundException(String message) {
		super(message);
	}
}
