package com.vision.game.bean;

/**
 * 活动信息
 * @author tangkunyin
 */
public class KactivityInfo {
	//表单需要此参数，数据库中没有这个字段
	private String ktvName;
	
	private int ktvId;
	private int activityId;
	private String acName;
	private String acRule;
	
	//0:已过期   1:正在进行  2:未开始
	private short acValid;
	
	//----------------------------
	private String acStartDate;
	
	private String acStartTime;
	private String acEndTime;
	private String acCloseTime;
	
	private String week;
	
	//摇奖结果，是多个答案
	private String rightAnswer1;
	private String rightAnswer2;
	private String rightAnswer3;
	private String rightAnswer4;
	private String rightAnswer5;
	private String rightAnswer6;
	private String rightAnswer7;
	private String rightAnswer8;
	private String rightAnswer9;
	private String rightAnswer10;
	
	//奖品
	private String prize1;
	private String prize2;
	private String prize3;
	private String prize4;
	private String prize5;
	private String prize6;
	private String prize7;
	private String prize8;
	private String prize9;
	private String prize10;
	
	private int winNum;
	
	public int getWinNum() {
		return winNum;
	}
	public void setWinNum(int winNum) {
		this.winNum = winNum;
	}
	//二维码图片地址
	private String qrcodeImg;
	private int acCrrentPeople;
	private String acPicAddress;
	
	public String getQrcodeImg() {
		return qrcodeImg;
	}
	public void setQrcodeImg(String qrcodeImg) {
		this.qrcodeImg = qrcodeImg;
	}
	public String getKtvName() {
		return ktvName;
	}
	public void setKtvName(String ktvName) {
		this.ktvName = ktvName;
	}
	public int getKtvId() {
		return ktvId;
	}
	public void setKtvId(int ktvId) {
		this.ktvId = ktvId;
	}
	public String getAcRule() {
		return acRule;
	}
	public void setAcRule(String acRule) {
		this.acRule = acRule;
	}
	public short getAcValid() {
		return acValid;
	}
	public void setAcValid(short acValid) {
		this.acValid = acValid;
	}
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	public String getAcStartDate() {
		return acStartDate;
	}
	public void setAcStartDate(String acStartDate) {
		this.acStartDate = acStartDate;
	}
	public String getAcStartTime() {
		return acStartTime;
	}
	public void setAcStartTime(String acStartTime) {
		this.acStartTime = acStartTime;
	}
	public String getAcEndTime() {
		return acEndTime;
	}
	public void setAcEndTime(String acEndTime) {
		this.acEndTime = acEndTime;
	}
	public String getAcName() {
		return acName;
	}
	public void setAcName(String acName) {
		this.acName = acName;
	}
	public String getAcCloseTime() {
		return acCloseTime;
	}
	public void setAcCloseTime(String acCloseTime) {
		this.acCloseTime = acCloseTime;
	}
	public String getAcPicAddress() {
		return acPicAddress;
	}
	public void setAcPicAddress(String acPicAddress) {
		this.acPicAddress = acPicAddress;
	}
	public int getAcCrrentPeople() {
		return acCrrentPeople;
	}
	public void setAcCrrentPeople(int acCrrentPeople) {
		this.acCrrentPeople = acCrrentPeople;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getRightAnswer1() {
		return rightAnswer1;
	}
	public void setRightAnswer1(String rightAnswer1) {
		this.rightAnswer1 = rightAnswer1;
	}
	public String getRightAnswer2() {
		return rightAnswer2;
	}
	public void setRightAnswer2(String rightAnswer2) {
		this.rightAnswer2 = rightAnswer2;
	}
	public String getRightAnswer3() {
		return rightAnswer3;
	}
	public void setRightAnswer3(String rightAnswer3) {
		this.rightAnswer3 = rightAnswer3;
	}
	public String getRightAnswer4() {
		return rightAnswer4;
	}
	public void setRightAnswer4(String rightAnswer4) {
		this.rightAnswer4 = rightAnswer4;
	}
	public String getRightAnswer5() {
		return rightAnswer5;
	}
	public void setRightAnswer5(String rightAnswer5) {
		this.rightAnswer5 = rightAnswer5;
	}
	public String getRightAnswer6() {
		return rightAnswer6;
	}
	public void setRightAnswer6(String rightAnswer6) {
		this.rightAnswer6 = rightAnswer6;
	}
	public String getRightAnswer7() {
		return rightAnswer7;
	}
	public void setRightAnswer7(String rightAnswer7) {
		this.rightAnswer7 = rightAnswer7;
	}
	public String getRightAnswer8() {
		return rightAnswer8;
	}
	public void setRightAnswer8(String rightAnswer8) {
		this.rightAnswer8 = rightAnswer8;
	}
	public String getRightAnswer9() {
		return rightAnswer9;
	}
	public void setRightAnswer9(String rightAnswer9) {
		this.rightAnswer9 = rightAnswer9;
	}
	public String getRightAnswer10() {
		return rightAnswer10;
	}
	public void setRightAnswer10(String rightAnswer10) {
		this.rightAnswer10 = rightAnswer10;
	}
	public String getPrize1() {
		return prize1;
	}
	public void setPrize1(String prize1) {
		this.prize1 = prize1;
	}
	public String getPrize2() {
		return prize2;
	}
	public void setPrize2(String prize2) {
		this.prize2 = prize2;
	}
	public String getPrize3() {
		return prize3;
	}
	public void setPrize3(String prize3) {
		this.prize3 = prize3;
	}
	public String getPrize4() {
		return prize4;
	}
	public void setPrize4(String prize4) {
		this.prize4 = prize4;
	}
	public String getPrize5() {
		return prize5;
	}
	public void setPrize5(String prize5) {
		this.prize5 = prize5;
	}
	public String getPrize6() {
		return prize6;
	}
	public void setPrize6(String prize6) {
		this.prize6 = prize6;
	}
	public String getPrize7() {
		return prize7;
	}
	public void setPrize7(String prize7) {
		this.prize7 = prize7;
	}
	public String getPrize8() {
		return prize8;
	}
	public void setPrize8(String prize8) {
		this.prize8 = prize8;
	}
	public String getPrize9() {
		return prize9;
	}
	public void setPrize9(String prize9) {
		this.prize9 = prize9;
	}
	public String getPrize10() {
		return prize10;
	}
	public void setPrize10(String prize10) {
		this.prize10 = prize10;
	}
}