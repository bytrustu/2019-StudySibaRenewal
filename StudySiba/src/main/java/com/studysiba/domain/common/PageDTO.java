package com.studysiba.domain.common;

public class PageDTO extends SearchVO{

	private int startRow;
	private int pageSize;
	private int count;
	private int pageNum;
	private int pageCount;
	private int pageBlock;
	private int startPage;
	private int endPage;
	private String id;
	private String type;
	private long uNo;

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
		pageInit();
	}

	public void pageInit() {
		setPageCount(count / pageSize + (count % pageSize == 0 ? 0 : 1));
		setPageBlock(3);
		setStartPage(((pageNum - 1) / pageBlock) * pageBlock + 1);
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		setEndPage(endPage);
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageBlock() {
		return pageBlock;
	}

	public void setPageBlock(int pageBlock) {
		this.pageBlock = pageBlock;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getuNo() {
		return uNo;
	}

	public void setuNo(long uNo) {
		this.uNo = uNo;
	}

	@Override
	public String toString() {
		return "PageDTO : [ startRow = " + startRow + " , pageSize = " + pageSize + " , count = " + count + " , pageNum = " + pageNum + " , pageCount = " + pageCount 
				+ " , pageBlock = " + pageBlock + " , startPage = " + startPage + " , endPage = " + endPage + " ] ";
	}
	
	
	

}
