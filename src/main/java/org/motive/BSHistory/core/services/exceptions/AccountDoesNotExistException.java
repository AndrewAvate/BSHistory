package org.motive.BSHistory.core.services.exceptions;

public class AccountDoesNotExistException extends RuntimeException {

	private static final long serialVersionUID = -3087814359343485677L;

	public AccountDoesNotExistException(Throwable cause) {
        super(cause);
    }

    public AccountDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountDoesNotExistException(String message) {
        super(message);
    }

    public AccountDoesNotExistException() {
    }
}
