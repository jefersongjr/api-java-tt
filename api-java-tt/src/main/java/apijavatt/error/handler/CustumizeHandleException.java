package apijavatt.error.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import apijavatt.error.ExceptionResponse;
import apijavatt.error.InvalidFieldsException;

@ControllerAdvice
@RestController
public class CustumizeHandleException extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), null);

		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(InvalidFieldsException.class)
	public final ResponseEntity<ExceptionResponse> handleBadQuestExceptions(Exception ex) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), null);

		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}
