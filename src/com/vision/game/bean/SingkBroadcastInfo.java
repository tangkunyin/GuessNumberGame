package com.vision.game.bean;

public class SingkBroadcastInfo {
	private int id;
	private String userHeadPic;
	private String nickName;
	private String fileUrl;
	private String textContent;
	private int type;

	//预留字段
//	private int userId;
//	private int gender;
//	private int ktvInnerId;
//	private String ktvName;
//	private int cityId;
//	private String sendTime;
//	private int scope;
//	private int topCount;
//	private int commentCount;
//	private String birthdate;
//	private int validate;
//	private String ktvFileName;
	
	public String getUserHeadPic() {
		return userHeadPic;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setUserHeadPic(String userHeadPic) {
		this.userHeadPic = userHeadPic;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public String getTextContent() {
		return textContent;
	}
	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}