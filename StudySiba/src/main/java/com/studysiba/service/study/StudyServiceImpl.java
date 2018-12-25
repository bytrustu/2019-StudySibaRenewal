package com.studysiba.service.study;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studysiba.dao.study.StudyDAO;
import com.studysiba.dao.upload.UploadDAO;
import com.studysiba.domain.page.PageDTO;
import com.studysiba.domain.study.StudyVO;
import com.studysiba.domain.upload.UploadVO;

@Service
public class StudyServiceImpl implements StudyService {

	@Autowired
	private StudyDAO studyDAO;
	@Autowired
	private UploadDAO uploadDAO;
	
	@Override
	public int write(StudyVO studyVO, UploadVO uploadVO) {
		int result = studyDAO.write(studyVO);
		int no = studyDAO.getStudyNo(studyVO);
		if ( result == 1 ) {
			studyVO.setgNo(no);
			result = studyDAO.makeGroup(studyVO);
			if ( result == 1 ) {
				result = studyDAO.joinGroup(studyVO);
			}
		} else {
			return result;
		}
		
		uploadVO.setuNo(no);
		uploadDAO.uploadFile(uploadVO);
		return result;
	}

	@Override
	public int getStudyCount() {
		return studyDAO.getStudyCount();
	}

	@Override
	public List<StudyVO> getStudyList(PageDTO page) {
		int startRow = (page.getPageNum()-1)*page.getPageSize()+1;
		page.setStartRow(startRow-1);
		List<StudyVO> list = studyDAO.getStudyList(page);
		return list;
	}

	
	
}
