package com.vision.game.bean;

/**
 * 参与活动的用户
 * @author tangkunyin
 */
public class KactivityUser {
	private int userId;
	private String nickname;
	private short sex;
	private String headpicpath;
	private int ktvId;
	private int activityId;
	private String joinTime;
	private String yourAnswer;
	//数据库中以char(1)体现，为null时，表示没开奖；n表示未中奖[no]；y表示中奖[yes]
	private String isWinner;
	
	//记录第几次被摇中，便于页面迭代。默认是0，表示从未被摇中过，即：不是中奖者
	private int th;   

	public int getTh() {
		return th;
	}
	public void setTh(int th) {
		this.th = th;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHeadpicpath() {
		return headpicpath;
	}
	public void setHeadpicpath(String headpicpath) {
		this.headpicpath = headpicpath;
	}
	public int getKtvId() {
		return ktvId;
	}
	public void setKtvId(int ktvId) {
		this.ktvId = ktvId;
	}
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	public short getSex() {
		return sex;
	}
	public void setSex(short sex) {
		this.sex = sex;
	}
	public String getJoinTime() {
		return joinTime;
	}
	public void setJoinTime(String joinTime) {
		this.joinTime = joinTime;
	}
	public String getYourAnswer() {
		return yourAnswer;
	}
	public void setYourAnswer(String yourAnswer) {
		this.yourAnswer = yourAnswer;
	}
	public String getIsWinner() {
		return isWinner;
	}
	public void setIsWinner(String isWinner) {
		this.isWinner = isWinner;
	}
}
