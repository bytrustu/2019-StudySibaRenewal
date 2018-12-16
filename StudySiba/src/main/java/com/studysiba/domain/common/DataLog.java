package com.studysiba.domain.common;

public class DataLog {
	private long no;
	private long dNo;
	private String type;
	private String id;
	private String title;
	private String dDate;

	public DataLog() {
	}

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public long getdNo() {
		return dNo;
	}

	public void setdNo(long dNo) {
		this.dNo = dNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getdDate() {
		return dDate;
	}

	public void setdDate(String dDate) {
		this.dDate = dDate;
	}

	@Override
	public String toString() {
		return "DataLog : [ no = " + no + " , dNo = " + dNo + " , type = " + type + " , id = " + id + " , title = " + title + " , dDate = " + dDate + " ] ";
	}

}
