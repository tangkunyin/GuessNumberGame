package com.vision.game.service;

import java.util.List;

import com.vision.game.bean.KactivityTemplaite;
import com.vision.game.dao.KactivityTemplaiteDao;

public class KactivityTemplaiteService {
	private KactivityTemplaiteDao KacTemplaiteDao;

	public KactivityTemplaiteDao getKacTemplaiteDao() {
		return KacTemplaiteDao;
	}

	public void setKacTemplaiteDao(KactivityTemplaiteDao kacTemplaiteDao) {
		KacTemplaiteDao = kacTemplaiteDao;
	}
	
	
	public int countAll(){
		return KacTemplaiteDao.countAll();
	}
	
	public List<KactivityTemplaite> selectAll(){
		return KacTemplaiteDao.selectAll();
	}
	
	public KactivityTemplaite findByActivityId(int id){
		return KacTemplaiteDao.findByActivityId(id);
	}
	
	public int addKaTemplaite(KactivityTemplaite kaTemplaite){
		return KacTemplaiteDao.addKaTemplaite(kaTemplaite);
	}
	
	public int deleteKaTemplaite(int id){
		return KacTemplaiteDao.deleteKaTemplaite(id);
	}
	
	public int updateKaTemplaite(KactivityTemplaite temp){
		return KacTemplaiteDao.updateKaTemplaite(temp);
	}
	
	public int updateTempUsedTimes(int id){
		return KacTemplaiteDao.updateTempUsedTimes(id);
	}
	public int countTempNames(String name) {
		return KacTemplaiteDao.countTempNames(name);
	}
}