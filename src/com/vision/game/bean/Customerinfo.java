package com.vision.game.bean;

import java.util.Date;

/**
 * 用户详情。需要携带登录时的信息
 * @author tangkunyin
 */
public class Customerinfo {
	private int id;
	private String userName;
	private String nickName;
	private short sex;
	private int areaId;
	private String areaName;
	private int cityId;
	private String city;
	private String introduce;
	private Date birthday;
	private String email;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public short getSex() {
		return sex;
	}
	public void setSex(short sex) {
		this.sex = sex;
	}
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Customerinfo [id=" + id + ", userName=" + userName
				+ ", nickName=" + nickName + ", sex=" + sex + ", areaId="
				+ areaId + ", areaName=" + areaName + ", cityId=" + cityId
				+ ", city=" + city + ", introduce=" + introduce + ", birthday="
				+ birthday + ", email=" + email + "]";
	}
	
}
