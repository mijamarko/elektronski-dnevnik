package com.iktpreobuka.elektronski_dnevnik.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/logs")
public class LogController {
	
	final static String BASE_PATH = "C:\\Users\\Marko\\Documents\\sts-workspace\\elektronski_dnevnik\\src\\logs\\";
	final static String LOG_FILE_NAME = "e-dnevnik";
	
	//GET all logs
	//GET logs for date
	//GET logs between dates
	//DELETE logs for date
	//DELETE logs between dates
	
	@GetMapping(path = "/{datum:.+}")
	public ResponseEntity dobaviLogZaDatum(@PathVariable String datum) {
		Path path = Paths.get(BASE_PATH + LOG_FILE_NAME + "_" + datum + ".log");
		Resource resource = null;
		try {
			resource = new UrlResource(path.toUri());
		} catch(MalformedURLException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	
	@GetMapping(path = "/")
	public void zipDownload(@RequestParam List<String> datum, HttpServletResponse response) throws IOException{
		String zipFileName = "logs for " + datum.get(0) + " to " + datum.get(datum.size() - 1) + ".zip";
		ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream());
		for(String d : datum) {
			FileSystemResource resource = new FileSystemResource(BASE_PATH + LOG_FILE_NAME + "_" + d + ".log");
			ZipEntry zipEntry = new ZipEntry(resource.getFilename());
			zipEntry.setSize(resource.contentLength());
			zipOut.putNextEntry(zipEntry);
			StreamUtils.copy(resource.getInputStream(), zipOut);
			zipOut.closeEntry();
		}
		zipOut.finish();
		zipOut.close();
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("application/zip");
		response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + zipFileName + "\"");
	}

}
