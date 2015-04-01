package com.vision.game.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.vision.game.bean.AdminUser;
import com.vision.game.dao.AdminUserDao;

/**
 * AdminUserDao的实现类
 * @author tangkunyin
 * @since 2013-05-30
 */
public class AdminUserDaoImpl implements AdminUserDao {
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
		int n=session.selectOne("countAllAdminUser");
		session.close();
		return n;
	}

	public List<AdminUser> selectAll() {
		SqlSession session=sqlSessionFactory.openSession();
		List<AdminUser> list = session.selectList("selectAllAdminUser");
		session.close();
		return list;
	}

	public AdminUser findByUserLogin(String userName, String passWord) {
		SqlSession session=sqlSessionFactory.openSession();
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("username", userName);
		params.put("repassword", passWord);
		AdminUser user=session.selectOne("findByUserLogin", params);
		session.close();
		return user;
	}

	public int addAdUser(AdminUser adminUser) {
		SqlSession session=sqlSessionFactory.openSession();
		int n=session.insert("insertAdminUser", adminUser);
		session.commit();
		session.close();
		return n;
	}

	public int deleteAdUser(int id) {
		SqlSession session=sqlSessionFactory.openSession();
		int n=session.delete("deleteAdminUser", id);
		session.commit();
		session.close();
		return n;
	}

	public int updateAdUser(int id) {
		SqlSession session=sqlSessionFactory.openSession();
		int n=session.update("updateAdminUser", id);
		session.commit();
		session.close();
		return n;
	}
}