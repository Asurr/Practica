package com.hector.practica.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PedidoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1344140204179224925L;

	public PedidoNotFoundException() {
		super();
	}

	public PedidoNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PedidoNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public PedidoNotFoundException(String message) {
		super(message);
	}

	public PedidoNotFoundException(Throwable cause) {
		super(cause);
	}

}
