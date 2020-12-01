package com.felipe.cliente_crud.exception;

/**
 * 
 * @author Felipe Lemos
 *
 */
@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException {
	     
    public ResourceNotFoundException(String message) {
		super(message);
	}
	
	public ResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}