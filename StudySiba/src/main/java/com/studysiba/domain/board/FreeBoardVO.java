package com.studysiba.domain.board;

import com.studysiba.domain.member.MemberVO;

public class FreeBoardVO extends MemberVO {

	private long no;
	private String type;
	private String id;
	private String title;
	private String content;
	private int gNo;
	private int step;
	private int indent;
	private int count;
	private int available;
	private String bDate;

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public String getbDate() {
		return bDate;
	}

	public void setbDate(String bDate) {
		this.bDate = bDate;
	}

	@Override
	public String toString() {
		return "FreeBoardVO : [ no = " + no + " , type = " + type + " , id = " + id + " , title = " + title
				+ " , content = " + content + " , gNo = " + gNo + " , step = " + step + " , indent = " + indent
				+ " , count = " + count + " , available = " + available + " , bDate = " + bDate + " ] ";
	}

}
