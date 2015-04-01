package com.vision.game.dao;

import java.util.List;

import com.vision.game.bean.KactivityInfo;

/**
 * KactivityInfoDao接口
 * @author tangkunyin
 * @see 2013-05-30
 */
public interface KactivityInfoDao {
	public int countAll();
	
	public List<KactivityInfo> selectAll();
	
	public KactivityInfo findByGameAcId(int AcId);
	
	public int conutCurrentAcUsers(int AcId);
	
	public int addGame(KactivityInfo gameInfo);
	
	public int deleteGame(int AcId);
	
	public int updateGame(KactivityInfo gameInfo);
	
	//二维码相关
	public int updateQRCodeAddr(String path,String acName);
	public String selectQRCodeAddr(int id);

	public List<KactivityInfo> findGameByKtvId(int ktvId);
}
