package com.app.siteminder.emailUtilDemo.util;

import java.util.ArrayList;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import com.app.siteminder.emailUtilDemo.dto.EmailResponse;

/**
 * @author ravi
 * Global Exception Handler
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<EmailResponse> handleMethodArgumentNotValidException( MethodArgumentNotValidException exception){
	  EmailResponse errResp = new EmailResponse();
	  errResp.setErrorMessage(exception.getFieldErrors().stream().map(a->a.getField()+" -> "+a.getDefaultMessage()).collect(Collectors.toList()));
	  errResp.setErrorMessage(errResp.getErrorMessage().stream().distinct().collect(Collectors.toList()));
	  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errResp);	  
  }


  @ExceptionHandler(CustomException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<EmailResponse> handleCustomException(CustomException exception){
	  
	  EmailResponse errResp = new EmailResponse();
	  ArrayList al = new ArrayList();

	  if(exception.getLocalizedMessage() != null && !"".equalsIgnoreCase(exception.getLocalizedMessage()))
	  {
		  al.add(exception.getErrorMessage());
	  }
	  if(exception.getErrorList() != null)
		  al.addAll(exception.getErrorList());
	  errResp.setErrorMessage(al);
	  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errResp);	  
  }


  
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<EmailResponse> handleException(
		  Exception exception, 
      WebRequest request
  ){
	  EmailResponse errResp = new EmailResponse();
	  ArrayList al = new ArrayList();
	  al.add(exception.getLocalizedMessage());
	  errResp.setErrorMessage(al);
	  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errResp);	  
  }

}

