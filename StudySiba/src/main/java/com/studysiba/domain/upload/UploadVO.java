package com.studysiba.domain.upload;

public class UploadVO {

	private long no;
	private long uNo;
	private String id;
	private String type;
	private String content;
	private String uuid;
	private String uFile;
	private String uDate;
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public long getuNo() {
		return uNo;
	}
	public void setuNo(long uNo) {
		this.uNo = uNo;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getuFile() {
		return uFile;
	}
	public void setuFile(String uFile) {
		this.uFile = uFile;
	}
	public String getuDate() {
		return uDate;
	}
	public void setuDate(String uDate) {
		this.uDate = uDate;
	}
	
	@Override
	public String toString() {
		return "UploadVO : [ no = " + no + " , uNo = " + uNo + " , id = " + id + " , type = " + type + " , content = " + content + " , uuid = " + uuid + " , uFile = " + uFile + " , uDate = " + uDate + " ]";
	}
	
	
}
