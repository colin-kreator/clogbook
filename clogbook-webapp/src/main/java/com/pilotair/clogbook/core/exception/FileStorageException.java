package com.pilotair.clogbook.core.exception;

public class FileStorageException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8769524563882646585L;

	public FileStorageException( String message ) {
		super( message );
	}

	public FileStorageException( String message, Throwable cause ) {
		super( message, cause );
	}
}