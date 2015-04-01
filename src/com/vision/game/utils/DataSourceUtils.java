package com.vision.game.utils;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
/**
 *	get mybatis sql session util 
 * @author tangkunyin
 */
public class DataSourceUtils {
	//get session
	public static SqlSession getSession(){
		final String resource="mybatis-config.xml";
		try {
			SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(resource));
			return factory.openSession();
		} catch (IOException e) {
			System.out.println("Mybatis配置文件未找到或打开文件异常...");
			e.printStackTrace();
		}
		return null;
	}
}
