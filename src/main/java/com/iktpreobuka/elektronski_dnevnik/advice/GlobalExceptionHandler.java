package com.iktpreobuka.elektronski_dnevnik.advice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	public List<String> handleValidationExceptions(MethodArgumentNotValidException e){
		List<String> errorMessages = new ArrayList<String>();
		e.getBindingResult().getAllErrors().forEach((error) -> {
			errorMessages.add(error.getDefaultMessage());
		});
		return errorMessages;
	}

}
