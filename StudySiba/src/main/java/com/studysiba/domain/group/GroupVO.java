package com.studysiba.domain.group;

import com.studysiba.domain.study.StudyVO;

public class GroupVO extends StudyVO {

	private long no;
	private long gNo;
	private String id;
	private String gDate;
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public long getgNo() {
		return gNo;
	}
	public void setgNo(long gNo) {
		this.gNo = gNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getgDate() {
		return gDate;
	}
	public void setgDate(String gDate) {
		this.gDate = gDate;
	}
	
	@Override
	public String toString() {
		return "GroupVO : [ no = " + no + " , gNo = " + gNo + " , id = " + id + " , gDate = " + gDate + " ] ";
	}
	
}
