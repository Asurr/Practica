package com.hector.practica.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ArticuloNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 537782578772766514L;

	public ArticuloNotFoundException() {
		super();
	}

	public ArticuloNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ArticuloNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ArticuloNotFoundException(String message) {
		super(message);
	}

	public ArticuloNotFoundException(Throwable cause) {
		super(cause);
	}

}
