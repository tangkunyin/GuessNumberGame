package com.vision.game.service;

import java.util.List;

import com.vision.game.bean.KactivityUser;
import com.vision.game.dao.KactivityUserDao;

public class KactivityUserService {
	private KactivityUserDao kacUserDao;
	
	public KactivityUserDao getKacUserDao() {
		return kacUserDao;
	}

	public void setKacUserDao(KactivityUserDao kacUserDao) {
		this.kacUserDao = kacUserDao;
	}

	public List<KactivityUser> selectAllAcUser() {
		return kacUserDao.selectAllAcUser();
	}

	public KactivityUser findAcUserById(int userId) {
		return kacUserDao.findAcUserById(userId);
	}

	public int addAcUser(KactivityUser kacUser) {
		return kacUserDao.addAcUser(kacUser);
	}

	public int deleteAcUser(int userId) {
		return kacUserDao.deleteAcUser(userId);
	}

	public int updateWinAcUser(String answer) {
		return kacUserDao.updateWinAcUser(answer);
	}
	
	public int updateFailureAcUser() {
		return kacUserDao.updateFailureAcUser();
	}
	
	public int updateAcWinUserTh(int th,String answer){
		return kacUserDao.updateAcWinUserTh(th, answer);
	}
	
	//与开奖有关
	public KactivityUser findAcUserByRightAnswer(String rightAnswer) {
		return kacUserDao.findAcUserByRightAnswer(rightAnswer);
	}
	
	public List<KactivityUser> findAcWinerByTH(int th) {
		return kacUserDao.findAcWinerByTH(th);
	}
	
	public List<KactivityUser> selectWinner() {
		return kacUserDao.selectWinner();
	}
}