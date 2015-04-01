package com.vision.game.service;

import java.util.List;

import com.vision.game.bean.KactivityInfo;
import com.vision.game.dao.KactivityInfoDao;

public class KactivityInfoService {
	//注入Dao
	private KactivityInfoDao gameInfoDao;

	public KactivityInfoDao getGameInfoDao() {
		return gameInfoDao;
	}

	public void setGameInfoDao(KactivityInfoDao gameInfoDao) {
		this.gameInfoDao = gameInfoDao;
	}
	
	public int countAll(){
		return gameInfoDao.countAll();
	}
	
	public int conutCurrentAcUsers(int AcId) {
		return gameInfoDao.conutCurrentAcUsers(AcId);
	}
	
	public List<KactivityInfo> selectAll(){
		return gameInfoDao.selectAll();
	}
	
	public KactivityInfo findByGameAcId(int AcId){
		return gameInfoDao.findByGameAcId(AcId);
	}
	
	public List<KactivityInfo> findGameByKtvId(int ktvId){
		return gameInfoDao.findGameByKtvId(ktvId);
	}
	
	public int addGame(KactivityInfo gameInfo){
		return gameInfoDao.addGame(gameInfo);
	}
	
	public int deleteGame(int AcId){
		return gameInfoDao.deleteGame(AcId);
	}
	
	public int updateGame(KactivityInfo gameInfo){
		return gameInfoDao.updateGame(gameInfo);
	}
	//二维码
	public int updateQRCodeAddr(String path,String acName){
		return gameInfoDao.updateQRCodeAddr(path,acName);
	}
	public String selectQRCodeAddr(int id) {
		return gameInfoDao.selectQRCodeAddr(id);
	}
}