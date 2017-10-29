package exception;

/**
 * Exception thrown by the service classes.
 * 
 * @author debdipta.h
 *
 */

@SuppressWarnings("serial")
public class ValidationException extends Exception {
	public ValidationException(Exception e) {
		super(e);
	}

	public ValidationException(String msg) {
		super(msg);
	}

	public ValidationException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
