package com.iktpreobuka.elektronski_dnevnik.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class LogServiceImpl implements LogService{
	
	final static String PATH_TO_LOGS = "/src/logs/";
	final static String LOG_FILE_NAME = "e-dnevnik";
	
	public void zipMultipleFiles(ArrayList<String> dates) throws IOException {
		String firstFile = dates.get(0);
		String lastFile = dates.get(dates.size()-1);
		String outputFileName = "Logs for " + firstFile + " to " + lastFile + ".zip";
		FileOutputStream fos = new FileOutputStream(PATH_TO_LOGS + outputFileName);
		ZipOutputStream zipOut = new ZipOutputStream(fos);
		for(String d : dates) {
			File fileToZip = new File(PATH_TO_LOGS + LOG_FILE_NAME + "_" + d +".log");
			FileInputStream fis = new FileInputStream(fileToZip);
			ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
			zipOut.putNextEntry(zipEntry);
			
			byte[] bytes = new byte[1024];
			int length;
			while((length = fis.read(bytes)) >= 0) {
				zipOut.write(bytes, 0, length);
			}
			fis.close();
		}
		zipOut.close();
		fos.close();
	}

}
