package com.vision.game.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 处理后台登录的协作
 * @author tangkunyin
 * @since 2013-07-12
 */
public class AdminUserUtil extends TimerTask{
	//根据ktvId查询KTV名字
	public static String getKtvNameById(int id){
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="SELECT SongHallName FROM songhallinfo where id="+id;
		try {
			ps=conn.prepareStatement(sql);
			rs= ps.executeQuery();
			String ktvName = null;
			if(rs.next()){
				ktvName=rs.getString("SongHallName");
			}
			return ktvName;
		} catch (SQLException e) {
			e.printStackTrace();
			return "未知KTV";
		}finally{
			JDBCUtil.closeDBResource(rs, ps, conn);
		}
	}
	//查询用户状态，根据islogin查询。 状态为noo则没有登录。yes表示已经登录
	public static String getAdminUserLoginState(int id){
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="SELECT islogin FROM AdminUser WHERE id="+id;
		try {
			ps=conn.prepareStatement(sql);
			rs= ps.executeQuery();
			String isLogin = null;
			if(rs.next()){
				isLogin=rs.getString("islogin");
			}
			return isLogin;
		} catch (SQLException e) {
			e.printStackTrace();
			return "noo";
		}finally{
			JDBCUtil.closeDBResource(rs, ps, conn);
		}
	}
	//更改用户状态:上线
	public static boolean setAULoginStateOn(int id){
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		String sql="update AdminUser set islogin='yes' WHERE id="+id;
		try {
			ps=conn.prepareStatement(sql);
			int n= ps.executeUpdate();
			if(n>0){
				return true;
			}else{
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally{
			JDBCUtil.closeDBResource(null, ps, conn);
		}
	}
	//更改用户状态：下线
	public static boolean setAULoginStateOff(int id){
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		String sql="update AdminUser set islogin='noo' WHERE id="+id;
		try {
			ps=conn.prepareStatement(sql);
			int n= ps.executeUpdate();
			if(n>0){
				return true;
			}else{
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally{
			JDBCUtil.closeDBResource(null, ps, conn);
		}
	}
	//更改所有用户状态：下线
	public static boolean setAllAULoginStateOff(){
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		String sql="UPDATE AdminUser SET islogin='noo'";
		try {
			ps=conn.prepareStatement(sql);
			int n= ps.executeUpdate();
			if(n>0){
				return true;
			}else{
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally{
			JDBCUtil.closeDBResource(null, ps, conn);
		}
	}
	private static int id;
	private static Timer timer;
	private int i=0;
	//在没人操作18分钟后，将用户状态改为下线
	public static void setAUexpire(int userId){
		id=userId;
		timer=new Timer();
		timer.schedule(new AdminUserUtil(), 0, 1000*60*30);
	}
	@Override
	public void run() {
		if(i==0){
			i++;
		}else{
			if(AdminUserUtil.setAULoginStateOff(id)){
				System.out.println(id+"号用户自动下线");
				GameOrderUtil.setGameOrderDefault();
				KAIdUtil.setKtvIdEmpty();
				timer.cancel();
			}
		}
	}
}