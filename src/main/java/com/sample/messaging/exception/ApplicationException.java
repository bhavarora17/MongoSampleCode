/**
 * Custom exception class
 */
package com.sample.messaging.exception;

public class ApplicationException extends Exception {

	/**
	 * default serial version Id
	 */
	private static final long serialVersionUID = 1L;

	public ApplicationException() {
	}

	public ApplicationException(String message) {
		super(message);

	}
}
