package com.example.demo.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/upload")
public class UploadFileController {

	@Value("${file-upload-dir}")
	String FILE_DIRECTORY;

	@Value("${image-file-upload-dir}")
	String IMAGE_FILE_DIRECTORY;

	@PostMapping(value = "/image/product")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> fileUpload(@RequestParam("File") MultipartFile[] multipartFile) throws IOException {

		List<File> files = new ArrayList<File>();
		for (int i = 0; i < multipartFile.length; i++) {
			File file = new File(FILE_DIRECTORY + new Date().getTime() + "_" + multipartFile[i].getOriginalFilename());
			files.add(file);
		}

		List<String> fileDownloadUrls = new ArrayList<String>();
		for (int i = 0; i < multipartFile.length; i++) {
			String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/product/")
					.path(files.get(i).getName()).toUriString();
			fileDownloadUrls.add(url);
		}

		FileOutputStream fos = null;

		for (int i = 0; i < multipartFile.length; i++) {
			files.get(i).createNewFile();
			fos = new FileOutputStream(files.get(i));
			fos.write(multipartFile[i].getBytes());
			fos.close();
		}
		fos.close();
//		Upload hình ảnh thành công!
		return ResponseEntity.ok(fileDownloadUrls);

	}

	@PostMapping("/image")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> imageSlidefileUpload(@RequestParam("File") MultipartFile multipartFile)
			throws IOException {

		File file = new File(IMAGE_FILE_DIRECTORY + new Date().getTime() + "_" + multipartFile.getOriginalFilename());
		FileOutputStream fos = null;
		file.createNewFile();
		fos = new FileOutputStream(file);
		fos.write(multipartFile.getBytes());
		fos.close();
		String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/other/").path(file.getName())
				.toUriString();
		return ResponseEntity.ok(url);

	}

}
