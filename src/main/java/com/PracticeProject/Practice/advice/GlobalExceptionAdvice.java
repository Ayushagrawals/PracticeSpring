package com.PracticeProject.Practice.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.PracticeProject.Practice.exception.UserInvalidDetails;
import com.PracticeProject.Practice.exception.AlreadyExistException;
import com.PracticeProject.Practice.exception.CustomException;
import com.PracticeProject.Practice.exception.CustomTechnicalException;
import com.PracticeProject.Practice.exception.ExceptionResponse;
import com.PracticeProject.Practice.exception.UserNotExists;

@ControllerAdvice
public class GlobalExceptionAdvice extends ResponseEntityExceptionHandler{
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<?> handleEmptyException(CustomException e,WebRequest webRequest)
	{
		ExceptionResponse ce=new ExceptionResponse("601","User not found in DB");
		
		return new ResponseEntity<>(ce,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(AlreadyExistException.class)
	public ResponseEntity<?> handleAlreadyExistException(AlreadyExistException e,WebRequest webRequest)
	{
		ExceptionResponse ce=new ExceptionResponse("602","User already Exists");
		
		return new ResponseEntity<>(ce,HttpStatus.NOT_ACCEPTABLE);
	}
	@ExceptionHandler(UserNotExists.class)
	public ResponseEntity<?> userNotExists(UserNotExists e,WebRequest webRequest)
	{
		ExceptionResponse ce=new ExceptionResponse("603","User does not Exists");
		
		return new ResponseEntity<>(ce,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserInvalidDetails.class)
	public ResponseEntity<?> UserInvalidDetails(UserInvalidDetails e,WebRequest webRequest)
	{
		ExceptionResponse ce=new ExceptionResponse("604","Payload is not proper");
		
		return new ResponseEntity<>(ce,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(CustomTechnicalException.class)
	public ResponseEntity<?> customTechnicalException(CustomTechnicalException e,WebRequest webRequest)
	{
		ExceptionResponse ce=new ExceptionResponse("604","Technical Error occoured");
		
		return new ResponseEntity<>(ce,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	
}
