package com.studysiba.domain.study;

import com.studysiba.domain.member.MemberVO;

public class StudyGroup extends MemberVO {

	private long no;
	private long gNo;
	private String id;
	private String gName;
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
	public String getgName() {
		return gName;
	}
	public void setgName(String gName) {
		this.gName = gName;
	}
	public String getgDate() {
		return gDate;
	}
	public void setgDate(String gDate) {
		this.gDate = gDate;
	}
	@Override
	public String toString() {
		return "StudyGroup : [ no = " + no + " , gNo = " + gNo + " , id = " + id + " , gName = " + gName + " , gDate = " + gDate + " ] ";
	}
	
	
	
}
