package com.vision.game.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.vision.game.bean.KactivityUser;
import com.vision.game.dao.KactivityUserDao;
/**
 * KactivityUserDao的实现类
 * @author tangkunyin
 * @since 2013-05-30
 */
public class KactivityUserDaoImpl implements KactivityUserDao {
	//引入Mybatis的sqlSessionFactory
	private SqlSessionFactory sqlSessionFactory;
	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	public List<KactivityUser> selectAllAcUser() {
		SqlSession session=sqlSessionFactory.openSession();
		List<KactivityUser> kacUsers=session.selectList("selectAllAcUser");
		session.close();
		return kacUsers;
	}

	public KactivityUser findAcUserById(int userId) {
		SqlSession session=sqlSessionFactory.openSession();
		KactivityUser kacUser=session.selectOne("findAcUserById", userId);
		session.close();
		return kacUser;
	}

	public int addAcUser(KactivityUser kacUser) {
		SqlSession session=sqlSessionFactory.openSession();
		int n=session.insert("addAcUser", kacUser);
		session.commit();
		session.close();
		return n;
	}

	public int deleteAcUser(int userId) {
		SqlSession session=sqlSessionFactory.openSession();
		int n=session.delete("deleteAcUser", userId);
		session.commit();
		session.close();
		return n;
	}

	public int updateWinAcUser(String answer) {
		SqlSession session=sqlSessionFactory.openSession();
		int n=session.update("updateAcWinUser", answer);
		session.commit();
		session.close();
		return n;
	}
	
	public int updateFailureAcUser() {
		SqlSession session=sqlSessionFactory.openSession();
		int n=session.update("updateAcFailureUser");
		session.commit();
		session.close();
		return n;
	}
	
	public KactivityUser findAcUserByRightAnswer(String rightAnswer) {
		SqlSession session=sqlSessionFactory.openSession();
		KactivityUser currentUser=session.selectOne("findAcUserByRightAnswer", rightAnswer);
		session.close();
		return currentUser;
	}
	public List<KactivityUser> selectWinner() {
		SqlSession session=sqlSessionFactory.openSession();
		List<KactivityUser> winnerList=session.selectList("selectGuessNumWinner");
		session.close();
		return winnerList;
	}
	
	public int updateAcWinUserTh(int th, String answer) {
		SqlSession session=sqlSessionFactory.openSession();
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("th", th);
		params.put("yourAnswer", answer);
		session.update("updateAcWinUserTh", params);
		session.commit();
		session.close();
		return 0;
	}
	public List<KactivityUser> findAcWinerByTH(int th) {
		SqlSession session=sqlSessionFactory.openSession();
		List<KactivityUser> users=session.selectList("findAcWinerByTH", th);
		session.close();
		return users;
	}
}