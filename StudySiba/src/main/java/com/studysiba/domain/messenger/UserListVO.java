package com.studysiba.domain.messenger;

public class UserListVO {

	private String preId;
	private String preNick;
	private String preProfile;
	private String lastId;
	private String lastNick;
	private String lastProfile;
	private int unRead;

	public String getPreId() {
		return preId;
	}

	public void setPreId(String preId) {
		this.preId = preId;
	}

	public String getPreNick() {
		return preNick;
	}

	public void setPreNick(String preNick) {
		this.preNick = preNick;
	}

	public String getPreProfile() {
		return preProfile;
	}

	public void setPreProfile(String preProfile) {
		this.preProfile = preProfile;
	}

	public String getLastId() {
		return lastId;
	}

	public void setLastId(String lastId) {
		this.lastId = lastId;
	}

	public String getLastNick() {
		return lastNick;
	}

	public void setLastNick(String lastNick) {
		this.lastNick = lastNick;
	}

	public String getLastProfile() {
		return lastProfile;
	}

	public void setLastProfile(String lastProfile) {
		this.lastProfile = lastProfile;
	}

	public int getUnRead() {
		return unRead;
	}

	public void setUnRead(int unRead) {
		this.unRead = unRead;
	}

}
