package com.vision.game.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 1、用IO的方式记录第几次游戏；
 * 2、在进行下一次活动之前，将摇奖结果更新到数据库中。方便日后查询之用。
 * 默认是1
 * @author tangkunyin
 */
public class GameOrderUtil {
	//路径
	private static String path=GameOrderUtil.class.getClassLoader().getResource("game-order.txt").getPath();
	
	//改
	public static void updateGameOrder(int n){
		try {
			BufferedWriter bw=new BufferedWriter(new FileWriter(new File(path)));
			bw.write(NumberUtil.num_Int2String(n));
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
	
	
	//恢复默认文件
	public static void setGameOrderDefault(){
		try {
			BufferedWriter bw=new BufferedWriter(new FileWriter(new File(path)));
			bw.write("一");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//查
	public static String getGameOrder(){
		try {
			BufferedReader br=new BufferedReader(new FileReader(new File(path)));
			String order=br.readLine();
			if(order==null){
				return "";
			}else{
				return order;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	//=========================================================================//
	/**
	 *摇中的号码何时写入活动信息表 
	 * @param th     	：第几次活动
	 * @param answer	：正确答案
	 * @param acId		：活动id
	 * @return boolean    更新成功与否
	 */
	public static boolean updateGameAnswers(int th,String answer,int acId){
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		String sql="update kactivityinfo set rightAnswer"+th+"="+answer+" WHERE activityId="+acId;
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
	
	//测试
//	public static void main(String[] args) {
//		GameOrderUtil.updateGameOrder(2);
//		System.out.println(GameOrderUtil.getGameOrder());
//	}
}
