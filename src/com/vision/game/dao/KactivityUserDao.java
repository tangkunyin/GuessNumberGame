package com.vision.game.dao;

import java.util.List;

import com.vision.game.bean.KactivityUser;

/**
 * KactivityUser接口
 * @author tangkunyin
 * @see 2013-05-30
 */
public interface KactivityUserDao {
	public List<KactivityUser> selectAllAcUser();
	
	public KactivityUser findAcUserById(int userId);
	
	public int addAcUser(KactivityUser kacUser);
	
	public int deleteAcUser(int userId);
	
	public int updateWinAcUser(String answer);
	
	public int updateFailureAcUser();
	
	public int updateAcWinUserTh(int th,String answer);
	
	//与开奖时有关的
	//开奖时，通过正确答案去找当前用户。
	public KactivityUser findAcUserByRightAnswer(String  rightAnswer);

	//查询第n次摇奖产生的用户
	public List<KactivityUser> findAcWinerByTH(int th);
	
	//摇奖期间，根据isWinner字段查询用户中奖信息
	public List<KactivityUser> selectWinner();
}
