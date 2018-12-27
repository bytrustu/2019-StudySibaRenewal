package com.studysiba.domain.common;

public class TotalVO {

	private String date;
	private String vCount;
	private String mCount;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getvCount() {
		return vCount;
	}
	public void setvCount(String vCount) {
		this.vCount = vCount;
	}
	public String getmCount() {
		return mCount;
	}
	public void setmCount(String mCount) {
		this.mCount = mCount;
	}
	
	@Override
	public String toString() {
		return "TotalVO : [ date = " + date + " , vCount = " + vCount + " , mCount = " + mCount + " ] ";
	}
	
}
