package com.felipe.cliente_crud.exception;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
 * @author Felipe Lemos
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL) 
@AllArgsConstructor	
@Data
public class ApiException {

	private String message;
	private HttpStatus httpStatus;
	private ZonedDateTime timestamp;
	private String description;
	private List<String> errors;
	
	
	public ApiException(HttpStatus httpStatus, ZonedDateTime timestamp, String description, List<String> errors) {
		this.httpStatus = httpStatus;
		this.timestamp = timestamp;
		this.description = description;
		this.errors = errors;
	}

	public ApiException(String message, HttpStatus httpStatus, ZonedDateTime timestamp, String description) {
		this.message = message;
		this.httpStatus = httpStatus;
		this.timestamp = timestamp;
		this.description = description;
	}
}
