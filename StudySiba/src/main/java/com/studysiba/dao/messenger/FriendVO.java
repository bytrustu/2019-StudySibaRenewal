package com.studysiba.dao.messenger;

public class FriendVO {

	private long no;
	private String id;
	private String fId;
	private String request;

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

	public String getfId() {
		return fId;
	}

	public void setfId(String fId) {
		this.fId = fId;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	@Override
	public String toString() {
		return "FriendVO : [ no = " + no + " , id = " + id + " , fId = " + fId + " , request = " + request + " ]";
	}

}
