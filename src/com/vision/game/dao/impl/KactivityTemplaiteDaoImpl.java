package com.vision.game.dao.impl;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.vision.game.bean.KactivityTemplaite;
import com.vision.game.dao.KactivityTemplaiteDao;
/**
 * KactivityTemplaiteDao实现类
 * @author tangkunyin
 * @since 2013-05-30
 * 对KactivityTemplaiteDao进行Mybatis的CRUD
 */
public class KactivityTemplaiteDaoImpl implements KactivityTemplaiteDao{
	//引入Mybatis的sqlSessionFactory
	private SqlSessionFactory sqlSessionFactory;
	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	public int countAll() {
		SqlSession session=sqlSessionFactory.openSession();
		int n=session.selectOne("countAllKactivityTemplaite");
		session.close();
		return n;
	}

	public List<KactivityTemplaite> selectAll() {
		SqlSession session=sqlSessionFactory.openSession();
		List<KactivityTemplaite> temps=session.selectList("selectAllKactivityTemplaite");
		session.close();
		return temps;
	}

	public KactivityTemplaite findByActivityId(int id) {
		SqlSession session=sqlSessionFactory.openSession();
		KactivityTemplaite temp=session.selectOne("findByacTempId",id);
		session.close();
		return temp;
	}

	public int addKaTemplaite(KactivityTemplaite kaTemplaite) {
		SqlSession session=sqlSessionFactory.openSession();
		int n=session.insert("insertKactivityTemplaite", kaTemplaite);
		session.commit();
		session.close();
		return n;
	}

	public int deleteKaTemplaite(int id) {
		SqlSession session=sqlSessionFactory.openSession();
		int n=session.delete("deleteKactivityTemplaite", id);
		session.commit();
		session.close();
		return n;
	}

	public int updateKaTemplaite(KactivityTemplaite temp) {
		SqlSession session=sqlSessionFactory.openSession();
		int n=session.update("updateKactivityTemplaite", temp);
		session.commit();
		session.close();
		return n;
	}
	public int updateTempUsedTimes(int id) {
		SqlSession session=sqlSessionFactory.openSession();
		int n=session.update("updateTempUsedTimes", id);
		session.commit();
		session.close();
		return n;
	}
	public int countTempNames(String name) {
		SqlSession session=sqlSessionFactory.openSession();
		int n=session.selectOne("countTempNames", name);
		session.close();
		return n;
	}
}