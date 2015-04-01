package com.vision.game.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.vision.game.bean.KactivityInfo;
import com.vision.game.dao.KactivityInfoDao;
/**
 * KactivityInfoDao实现类
 * @author tangkunyin
 * @since 2013-05-30
 * 通过Mybatis进行CURD
 */
public class KactivityInfoDaoImpl implements KactivityInfoDao{
	private SqlSessionFactory sqlSessionFactory;
	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	public int countAll() {
		SqlSession session=sqlSessionFactory.openSession();
		int n=session.selectOne("countAllGame");
		session.close();
		return n;
	}

	public List<KactivityInfo> selectAll() {
		SqlSession session=sqlSessionFactory.openSession();
		List<KactivityInfo> list =session.selectList("selectAllGame");
		session.close();
		return list;
	}

	public KactivityInfo findByGameAcId(int AcId) {
		SqlSession session=sqlSessionFactory.openSession();
		KactivityInfo gameInfo=session.selectOne("findByGameAcId", AcId);
		session.close();
		return gameInfo;
	}

	public List<KactivityInfo> findGameByKtvId(int ktvId) {
		SqlSession session=sqlSessionFactory.openSession();
		List<KactivityInfo> list =session.selectList("findGameByKtvId",ktvId);
		session.close();
		return list;
	}
	
	public int addGame(KactivityInfo gameInfo) {
		SqlSession session=sqlSessionFactory.openSession();
		int n=session.insert("insertGame", gameInfo);
		session.commit();
		session.close();
		return n;
	}

	public int deleteGame(int AcId) {
		SqlSession session=sqlSessionFactory.openSession();
		int n=session.insert("deleteGame", AcId);
		session.commit();
		session.close();
		return n;
	}

	//二维码相关操作
	public int updateQRCodeAddr(String path,String acName) {
		SqlSession session=sqlSessionFactory.openSession();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("qrcodeImg", path);
		map.put("acName", acName);
		int n=session.update("updateQRCodeImg", map);
		session.commit();
		session.close();
		return n;
	}
	public String selectQRCodeAddr(int id) {
		SqlSession session=sqlSessionFactory.openSession();
		String qrPath=session.selectOne("selectQRCodeImg", id);
		session.close();
		return qrPath;
	}
	public int conutCurrentAcUsers(int AcId) {
		SqlSession session=sqlSessionFactory.openSession();
		int totalUses=session.selectOne("conutCurrentAcUsers", AcId);
		session.close();
		return totalUses;
	}
	public int updateGame(KactivityInfo gameInfo) {
		SqlSession session=sqlSessionFactory.openSession();
		int n=session.update("updateGame", gameInfo);
		session.commit();
		session.close();
		return n;
	}
}
