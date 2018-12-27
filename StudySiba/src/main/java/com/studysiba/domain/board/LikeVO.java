package com.studysiba.domain.board;

public class LikeVO extends FreeBoardVO {

	private long no;
	private long fNo;
	private String type;
	private String id;
	private String lDate;

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public long getfNo() {
		return fNo;
	}

	public void setfNo(long fNo) {
		this.fNo = fNo;
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

	public String getlDate() {
		return lDate;
	}

	public void setlDate(String lDate) {
		this.lDate = lDate;
	}

	@Override
	public String toString() {
		return "LikeVO : [ no = " + no + " , fNo = " + fNo + " , type = " + type + " , id = " + id + " , lDate = "
				+ lDate + " ] ";
	}

}
