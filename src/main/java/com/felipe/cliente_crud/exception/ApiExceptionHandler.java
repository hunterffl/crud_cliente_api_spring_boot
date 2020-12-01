package com.felipe.cliente_crud.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 
 * @author Felipe Lemos
 *
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final String ZONE_ID = "Z";
	
	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(
    	ResourceNotFoundException ex, WebRequest request) {
		
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		ApiException apiException = new ApiException(
				ex.getMessage(), 
				httpStatus, 
				ZonedDateTime.now(ZoneId.of(ZONE_ID)),
				request.getDescription(false));
        logger.error(ex.getMessage());
		return new ResponseEntity<>(apiException, httpStatus);
    }
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> globalExceptionHandler(Exception ex, WebRequest request) {
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		ApiException apiException = new ApiException(
				ex.getMessage(), 
				httpStatus, 
				ZonedDateTime.now(ZoneId.of(ZONE_ID)),
				request.getDescription(false));
        logger.error(ex.getMessage());
		return new ResponseEntity<>(apiException, httpStatus);
	}
	
	@ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<Object> handleResourceConflictException(
    	ResourceAlreadyExistsException ex, WebRequest request) {
		
		HttpStatus httpStatus = HttpStatus.CONFLICT;
		ApiException apiException = new ApiException(
				ex.getMessage(), 
				httpStatus, 
				ZonedDateTime.now(ZoneId.of(ZONE_ID)),
				request.getDescription(false));
        logger.error(ex.getMessage());
		return new ResponseEntity<>(apiException, httpStatus);
    }
	
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
    	MethodArgumentNotValidException ex, HttpHeaders headers, 
        HttpStatus status, WebRequest request) {
		
		List<String> errors = ex.getBindingResult()
	                .getFieldErrors()
	                .stream()
	                .map(FieldError::getDefaultMessage)
	                .collect(Collectors.toList());
		
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ApiException apiException = new ApiException(
				httpStatus, 
				ZonedDateTime.now(ZoneId.of(ZONE_ID)),
				request.getDescription(false),
				errors);
        logger.error(errors);
		return new ResponseEntity<>(apiException, httpStatus);
    }
}
