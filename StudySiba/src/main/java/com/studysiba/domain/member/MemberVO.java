package com.studysiba.domain.member;

public class MemberVO {

	private long no;
	private String type;
	private String auth;
	private String id;
	private String sId;
	private String pass;
	private String nick;
	private String eMail;
	private String proFile;
	private String cDate;
	private String mDate;
	
	public MemberVO() {
	}
	
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
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getsId() {
		return sId;
	}
	public void setsId(String sId) {
		this.sId = sId;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getProFile() {
		return proFile;
	}
	public void setProFile(String proFile) {
		this.proFile = proFile;
	}
	public String getcDate() {
		return cDate;
	}
	public void setcDate(String cDate) {
		this.cDate = cDate;
	}
	public String getmDate() {
		return mDate;
	}
	public void setmDate(String mDate) {
		this.mDate = mDate;
	}

	@Override
	public String toString() {
		return "MemberVO [ no=" + Integer.toString((int) no) + ", type=" + type + ", auth=" + auth + ", id=" + id + ", sId=" + sId + ", pass=" + pass 
				+ ", nick=" + nick + ", eMail=" + eMail + ", proFile=" + proFile + ", cDate=" + cDate + ", mDate=" + mDate + " ]";
	}
	
	
	
	
}
