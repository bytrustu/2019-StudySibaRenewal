package com.studysiba.domain.messenger;

public class MessageVO {

	private long no;
	private long roomId;
	private String type;
	private String id;
	private String toId;
	private String content;
	private int mRead;
	private String mDate;
	
	private String myProfile;
	private String toProfile;

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public long getRoomId() {
		return roomId;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
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

	public String getToId() {
		return toId;
	}

	public void setToId(String toId) {
		this.toId = toId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getmRead() {
		return mRead;
	}

	public void setmRead(int mRead) {
		this.mRead = mRead;
	}

	public String getmDate() {
		return mDate;
	}

	public void setmDate(String mDate) {
		this.mDate = mDate;
	}
	
	public String getMyProfile() {
		return myProfile;
	}

	public void setMyProfile(String myProfile) {
		this.myProfile = myProfile;
	}

	public String getToProfile() {
		return toProfile;
	}

	public void setToProfile(String toProfile) {
		this.toProfile = toProfile;
	}

	@Override
	public String toString() {
		return "MessageVO : [ no = " + no + " , roomId = " + roomId + " , type = " + type + " , id = " + id
				+ " , toId = " + toId + " , content = " + content + " , mRead = " + mRead + " , mDate = " + mDate
				+ " , myProfile = " + myProfile + " , toProfile = " + toProfile + " ]";
	}

}
