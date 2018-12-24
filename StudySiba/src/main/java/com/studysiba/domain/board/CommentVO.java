package com.studysiba.domain.board;

import com.studysiba.domain.member.MemberVO;

public class CommentVO extends MemberVO {

	private long no;
	private long fNo;
	private String type;
	private String id;
	private String preId;
	private String content;
	private int gNo;
	private int step;
	private int indent;
	private int available;
	private String cDate;

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

	public String getPreId() {
		return preId;
	}

	public void setPreId(String preId) {
		this.preId = preId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getgNo() {
		return gNo;
	}

	public void setgNo(int gNo) {
		this.gNo = gNo;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getIndent() {
		return indent;
	}

	public void setIndent(int indent) {
		this.indent = indent;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public String getcDate() {
		return cDate;
	}

	public void setcDate(String cDate) {
		this.cDate = cDate;
	}

	@Override
	public String toString() {
		return "CommentVO : [ no = " + no + " , fNo = " + fNo + " , type = " + type + " , id = " + id + " , preId = "
				+ preId + " , content = " + content + " , gNo = " + gNo + " , step = " + step + " , indent = " + indent
				+ " ,available = " + available + " , cDate = " + cDate + " ] ";
	}
}
