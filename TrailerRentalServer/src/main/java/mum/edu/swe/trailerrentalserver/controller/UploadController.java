package mum.edu.swe.trailerrentalserver.controller;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mum.edu.swe.trailerrentalserver.service.FileService;

@RestController
//@RequestMapping("")
public class UploadController {
	
	private final FileService fileService;
	 
	@Autowired
	public UploadController(FileService fileService) {
		this.fileService = fileService;
	}
	 @CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = ("/upload"), headers = "content-type=multipart/form-data", method = RequestMethod.POST)
	public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file,
			@RequestParam("dataType") String dataType) throws IOException {
		System.out.println(file.getOriginalFilename());
		System.out.println(dataType);
		fileService.storeFile(file);
		return new ResponseEntity<>("File uploaded successfully.", HttpStatus.OK);
	}
}
