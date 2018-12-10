package com.hector.practica.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundEntityException extends Exception {

	private static final long serialVersionUID = -6659757722767550462L;

	public NotFoundEntityException(String message) {
		super(message);
	}

}