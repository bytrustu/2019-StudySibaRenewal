package com.studysiba.domain.study;

public class StudyVO {

	private long no;
	private String id;
	private String gName;
	private String divide;
	private String title;
	private String content;
	private String lat;
	private String lng;
	private String area;
	private String address;
	private String toPer;
	private String fromPer;
	private String toTime;
	private String fromTime;
	private int person;
	private String available;
	private String rDate;
	private String sDate;
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
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
	public String getDivide() {
		return divide;
	}
	public void setDivide(String divide) {
		this.divide = divide;
	}
	public String getTitle() {
		return title;
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
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getToPer() {
		return toPer;
	}
	public void setToPer(String toPer) {
		this.toPer = toPer;
	}
	public String getFromPer() {
		return fromPer;
	}
	public void setFromPer(String fromPer) {
		this.fromPer = fromPer;
	}
	public String getToTime() {
		return toTime;
	}
	public void setToTime(String toTime) {
		this.toTime = toTime;
	}
	public String getFromTime() {
		return fromTime;
	}
	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}
	public int getPerson() {
		return person;
	}
	public void setPerson(int person) {
		this.person = person;
	}
	public String getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
	}
	public String getrDate() {
		return rDate;
	}
	public void setrDate(String rDate) {
		this.rDate = rDate;
	}
	public String getsDate() {
		return sDate;
	}
	public void setsDate(String sDate) {
		this.sDate = sDate;
	}
	@Override
	public String toString() {
		return "StudyVO : [ no = " + no + " , id = " + id + " , gName = " + gName + " , divide = " + divide + " , title = " + title + " , content = " + content
				+ " , lat = " + lat + " , lng = " + lng + " , area = " + area + " , address = " + address + " , toPer = " + toPer + " , fromPer = " + fromPer  
				+ " , toTime = " + toTime + " , fromTime = " + fromTime + " , person = " + person + " , available = " + available + " , rDate = " + rDate
				+ " , sDate = " + sDate + " ] ";
	}
	
}
