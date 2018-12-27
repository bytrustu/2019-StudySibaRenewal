package com.studysiba.common;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;

public class FileUpload {

	public static String upload(String uploadPath, String ext, byte[] fileData) throws IOException {
		UUID uuid = UUID.randomUUID();
		String saveName = uuid.toString()+ext;
		File target = new File(uploadPath, saveName);
		FileCopyUtils.copy(fileData, target);
		Runtime.getRuntime().exec("chmod 644 "+uploadPath + saveName);
		return saveName;
	}
}
