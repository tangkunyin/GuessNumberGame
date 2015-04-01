package com.vision.game.utils;

import org.apache.ibatis.session.SqlSession;

import com.vision.game.bean.Customerinfo;

public class SelectCustomerById {
	public static Customerinfo getCustById(int userId){
		try {
			SqlSession session=DataSourceUtils.getSession();
			Customerinfo user=session.selectOne("selectCustById", userId);
			session.close();
			return user;
		} catch (NumberFormatException e) {
			System.err.println("==========传入用户id不是整数，系统无法解析。==========");
			return null;
		}
	}
	
	/*public static void main(String[] args) {
		System.out.println(getCustById(50104).toString());
	}*/
}
