package com.example.duckdemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// Indicates @ExceptionHandler, @InitBinder, or @ModelAttribute have been used on the methods
// inside this class. We are focused on @ExceptionHandler
//
// Applies to all registered controller beans
// - This is a global exception handler of sorts
@ControllerAdvice
public class ControllerAdviceExceptionHandlers {

	// Specifies the following method is for handling the specified exception
	@ExceptionHandler(value = DuckNotFoundException.class)
	public ResponseEntity<String> duckNotFoundExceptionHandler(DuckNotFoundException dnfe) {
		// Spring automatically passes the exception to our method parameter
		return new ResponseEntity<String>(dnfe.getMessage(), HttpStatus.NOT_FOUND);
	}
}

