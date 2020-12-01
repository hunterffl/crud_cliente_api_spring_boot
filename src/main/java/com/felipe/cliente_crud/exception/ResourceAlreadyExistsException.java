package com.felipe.cliente_crud.exception;

/**
 * 
 * @author Felipe Lemos
 *
 */
@SuppressWarnings("serial")
public class ResourceAlreadyExistsException extends RuntimeException {
    
    public ResourceAlreadyExistsException(String message) {
		super(message);
	}
	
	public ResourceAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}
}
