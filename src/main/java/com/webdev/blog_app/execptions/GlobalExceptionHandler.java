package com.webdev.blog_app.execptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.webdev.blog_app.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public  ResponseEntity<ApiResponse> resourceNotFoundExceptionHandle(ResourceNotFoundException ex){
		String message=ex.getMessage();
		boolean status=false;
		ApiResponse apiRes=new ApiResponse(message,status);
		return new ResponseEntity<ApiResponse>(apiRes,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> methodArgumentNotValidExceptionHandle(MethodArgumentNotValidException ex) {
		
		Map<String, String> errors = new HashMap<>();
		ex.getFieldErrors().forEach((error) -> {
			String fieldName = error.getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		return new ResponseEntity<Map<String,String>>(errors,HttpStatus.BAD_REQUEST);
		
	}
}
