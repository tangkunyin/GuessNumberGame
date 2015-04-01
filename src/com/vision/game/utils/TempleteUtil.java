package com.vision.game.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vision.game.service.KactivityTemplaiteService;

/**
 * 模版工具
 * @author tangkunyin
 * @since 2013-08-26
 */
public class TempleteUtil {
	KactivityTemplaiteService KacTemplaiteService=(KactivityTemplaiteService) SpringUtil.getBean("KacTemplaiteService");
	/**
	 *  更新模版使用次数：增加一次
	 */
	public void addTempUsedTimes(){
		//使用JDBC取得对应模版的基数
		try {
			Connection conn=JDBCUtil.getConnection();
			String sql="SELECT tempUsedTimes FROM kactivitytemplaite";
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			int n=Integer.parseInt(rs.getString("tempUsedTimes"));
			KacTemplaiteService.updateTempUsedTimes(n+1);
			JDBCUtil.closeDBResource(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 减少一次
	 */
	public void reduceTempUsedTimes(){
		//使用JDBC取得对应模版的基数
		try {
			Connection conn=JDBCUtil.getConnection();
			String sql="SELECT tempUsedTimes FROM kactivitytemplaite";
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			int n=Integer.parseInt(rs.getString("tempUsedTimes"));
			KacTemplaiteService.updateTempUsedTimes(n-1);
			JDBCUtil.closeDBResource(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}