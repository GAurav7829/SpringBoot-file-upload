package com.demo.exceptions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import com.demo.payload.FileResponse;

@RestControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(MultipartException.class)
	public ResponseEntity<Map<String, String>> handleMultipartException(MultipartException ex){
		Map<String, String> map = new HashMap<String, String>();
		map.put("Error", ex.getMessage());
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(FileNotFoundException.class)
	public ResponseEntity<Map<String, String>> handleFileNotFoundException(FileNotFoundException ex){
		Map<String, String> map = new HashMap<String, String>();
		map.put("Error", ex.getMessage());
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(IOException.class)
	public ResponseEntity<FileResponse> handleIOException(IOException ex){
		return new ResponseEntity<FileResponse>(new FileResponse(null, "Error in reading file"), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
