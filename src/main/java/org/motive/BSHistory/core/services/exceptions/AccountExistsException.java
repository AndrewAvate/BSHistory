package org.motive.BSHistory.core.services.exceptions;

public class AccountExistsException extends RuntimeException {
	private static final long serialVersionUID = -6281229025478641538L;

	public AccountExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountExistsException(String message) {
        super(message);
    }

    public AccountExistsException() {
        super();
    }
}
