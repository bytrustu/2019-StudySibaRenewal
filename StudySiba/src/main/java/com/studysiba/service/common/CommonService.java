package com.studysiba.service.common;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface CommonService {

	String upload(String type, String id, MultipartFile file) throws IOException;


}
