package com.tagarena.creature.service.model.exception;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CreatureNotFoundException extends Throwable {

	public CreatureNotFoundException(String message) {
		super(message);
	}
}
