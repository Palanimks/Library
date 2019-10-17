package com.hcl.lms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hcl.lms.utility.LibraryUtility;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Laxman
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(LimitExceededException.class)
	public ResponseEntity<ErrorResponse> limitExceededException(LimitExceededException ex) {
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), "Failed");

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoBookAvailableException.class)
	public ResponseEntity<ErrorResponse> globalExceptionHandler(NoBookAvailableException exception) {
		ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), LibraryUtility.ERROR_RESPONSE_FAIL);

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

	}
}
