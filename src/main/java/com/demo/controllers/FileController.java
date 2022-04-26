package com.demo.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.payload.FileResponse;
import com.demo.services.FileService;

@RestController
@RequestMapping("/file")
public class FileController {
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	@PostMapping("/upload")
	public ResponseEntity<FileResponse> fileUpload(
			@RequestParam("image") MultipartFile image
			){
		String fileName;
		try {
			fileName = this.fileService.uploadImage(path, image);
			//repository call
			//save filename to db
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<FileResponse>(new FileResponse(null, "Image is not uploaded due to error on server."), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<FileResponse>(new FileResponse(fileName, "Image Uploaded successfully."), HttpStatus.OK);
	}
}
