package com.vision.game.bean;

/**
 * 活动模版  模版，是一个笼统的概念
 * @author tangkunyin
 */
public class KactivityTemplaite {
	
	//模版ID 根据模版生成具体的活动id。他不再页面上体现，在数据库中自增体现。
	private int acTempId;

	private String tempName;           //活动的名字应该是唯一的，否则会出现歧义
	private String acRule;			//游戏规则
	private short tempValid;		//模版状态：0，禁用模版，1：启动模版
	private String acPicAddress;	//活动图展示，大小：320*480
	private int tempUsedTimes;     //有多少商家使用这个模版
	
	public int getAcTempId() {
		return acTempId;
	}
	public void setAcTempId(int acTempId) {
		this.acTempId = acTempId;
	}
	public String getTempName() {
		return tempName;
	}
	public void setTempName(String tempName) {
		this.tempName = tempName;
	}
	public String getAcRule() {
		return acRule;
	}
	public void setAcRule(String acRule) {
		this.acRule = acRule;
	}
	public short getTempValid() {
		return tempValid;
	}
	public void setTempValid(short tempValid) {
		this.tempValid = tempValid;
	}
	public String getAcPicAddress() {
		return acPicAddress;
	}
	public void setAcPicAddress(String acPicAddress) {
		this.acPicAddress = acPicAddress;
	}
	public int getTempUsedTimes() {
		return tempUsedTimes;
	}
	public void setTempUsedTimes(int tempUsedTimes) {
		this.tempUsedTimes = tempUsedTimes;
	}
}