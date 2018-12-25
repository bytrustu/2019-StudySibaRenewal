package com.studysiba.service.study;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studysiba.dao.member.MemberDAO;
import com.studysiba.dao.study.StudyDAO;
import com.studysiba.dao.upload.UploadDAO;
import com.studysiba.domain.board.FreeBoardVO;
import com.studysiba.domain.common.PageDTO;
import com.studysiba.domain.common.SearchVO;
import com.studysiba.domain.study.StudyVO;
import com.studysiba.domain.upload.UploadVO;

@Service
public class StudyServiceImpl implements StudyService {

	@Autowired
	private StudyDAO studyDAO;
	@Autowired
	private UploadDAO uploadDAO;
	@Autowired
	private MemberDAO memberDAO;
	
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

	@Override
	public int getSearchCount(SearchVO searchVO) {
		if( searchVO.getSearchType().equals("id") ) {
			searchVO.setSearchText(memberDAO.getUserId(searchVO.getSearchText()));
		}
		return studyDAO.getSearchCount(searchVO);
	}

	@Override
	public List<StudyVO> getSearchList(PageDTO page) {
		if ( page.getSearchType().equals("id")) {
			page.setSearchText(memberDAO.getUserId(page.getSearchText()));
		}
		int startRow = (page.getPageNum()-1)*page.getPageSize()+1;
		page.setStartRow(startRow-1);
		List<StudyVO> list = studyDAO.getSearchList(page);
		return list;
	}
	
	

	
	
}
