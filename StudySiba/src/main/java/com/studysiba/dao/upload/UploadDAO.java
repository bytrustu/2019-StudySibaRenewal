package com.studysiba.dao.upload;

import java.util.List;

import com.studysiba.domain.common.PageDTO;
import com.studysiba.domain.upload.UploadVO;

public interface UploadDAO {

	// 파일 업로드
	void uploadFile(UploadVO uploadVO);
	// 번호 업로드 카운트
	int getUploadCount(UploadVO uploadVO);
	// 번호 업로드 리스트
	List<UploadVO> getUploadList(PageDTO page);

}
