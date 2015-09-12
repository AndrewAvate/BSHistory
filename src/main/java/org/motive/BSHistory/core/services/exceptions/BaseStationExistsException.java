package org.motive.BSHistory.core.services.exceptions;

public class BaseStationExistsException extends RuntimeException {
	private static final long serialVersionUID = -5829286441009853969L;

	public BaseStationExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseStationExistsException(String message) {
		super(message);
	}

	public BaseStationExistsException() {
		super();
	}

}
