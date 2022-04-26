package com.demo.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

@RestControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(MultipartException.class)
	public ResponseEntity<Map<String, String>> handleMultipartException(MultipartException ex){
		Map<String, String> map = new HashMap<String, String>();
		map.put("Error", ex.getMessage());
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.BAD_REQUEST);
	}
}
