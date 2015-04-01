package com.vision.game.dao;

import java.util.List;

import com.vision.game.bean.KactivityTemplaite;


/**
 * KactivityTemplaite接口
 * @author tangkunyin
 * @see 2013-05-30
 */
public interface KactivityTemplaiteDao {
	public int countAll();
	
	//查看活动名称是否有效
	public int countTempNames(String name);
	
	public List<KactivityTemplaite> selectAll();
	
	public KactivityTemplaite findByActivityId(int id);
	
	public int addKaTemplaite(KactivityTemplaite kaTemplaite);
	
	public int deleteKaTemplaite(int id);
	
	public int updateKaTemplaite(KactivityTemplaite temp);
	
	public int updateTempUsedTimes(int id);
}
