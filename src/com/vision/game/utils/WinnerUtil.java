package com.vision.game.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.vision.game.bean.KactivityUser;

/**
 * 根据算法算出谁是中奖用户
 * 被选出的下次不能再被选出。即：要防止重复，可根据th字段不等于0判断
 * @author tangkunyin
 * @since 2013-07-18
 */
public class WinnerUtil {
	static Logger logger=Logger.getLogger(WinnerUtil.class);
	/**
	 * 通过jdbc查得用户的答案，并构造成数组
	 * @author tangkunyin
	 * @return int[userAnswer]
	 */
	public static int[] getWinnerNumbers(){
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		StringBuilder strb=new StringBuilder();
		String sql="SELECT yourAnswer FROM kactivityuser WHERE th=0 ORDER BY yourAnswer ASC";  //。按升序查所有用户并且排除已经被选中的用户
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				strb.append(rs.getString("yourAnswer")+" ");
			}
			String str[]=strb.toString().trim().split(" ");
			int answers[]=new int[str.length];
			for(int i=0;i<str.length;i++){
				answers[i]=Integer.parseInt(str[i]);
			}
			return answers;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.closeDBResource(rs, ps, conn);
		}
		return null;
	}
	/**
	 * 必须满足有10人参加活动，否则调用本方法将有数组下标越界错误
	 * @author tangkunyin
	 * @since 2013-07-19
	 * @return Map<userType,userAnswer>
	 */
	public static Map<String,Object> getWinnersAnswers(){
		try{
			//获取此刻标准答案
			String str[]=FileRWUtil.readFromFile();
			if(str[0]==null || str[1]==null || str[2]==null){
				logger.info("No data in numbers.properties file--------->please check the file");
				System.out.print("[numbers.properties]文件为空，没有录入答案...");
				return null;
			}else{
				int answer=Integer.parseInt(str[2]+str[1]+str[0]);
				int userAnswers[]=WinnerUtil.getWinnerNumbers();
				Map<String,Object> map=new TreeMap<String,Object>();
				//先查找标准答案
				for(int i=0;i<userAnswers.length;i++){
					if(userAnswers[i]==answer){
						map.put("specialUser", userAnswers[i]);
					}
				}
				int temp[]=new int[userAnswers.length];
				int numbers[]=new int[userAnswers.length];	
				for(int i=0;i<userAnswers.length;i++){
					//选最近的。用数组中的元素与标准答案相加减，然后排序，选最小的两位即可
					if((answer-userAnswers[i])>0){
						temp[i]=answer-userAnswers[i];
						numbers[i]=answer-userAnswers[i];
					}else{
						temp[i]=userAnswers[i]-answer;
						numbers[i]=userAnswers[i]-answer;
					}
				}
				Arrays.sort(temp);
				//将numbers存在list集合中，再用indexof函数，找到具体是哪一个
				List<Integer> list=new ArrayList<Integer>();
				for(int x=0;x<numbers.length;x++){
					list.add(numbers[x]);
				}
				
//===============================================================//
				int num[]=new int[temp.length];
				for(int j=0;j<temp.length;j++){
					num[j]=list.indexOf(temp[j]);
				}
				
				for(int y=0;y<temp.length-1;y++){
					if(temp[y]==temp[y+1]){
						num[y+1]=list.lastIndexOf(temp[y]);
					}
				}
				//-----------前后相等性判断完毕
				for(int z=0;z<temp.length;z++){
					if(userAnswers[num[z]]!=answer){
						map.put("normal"+z, userAnswers[num[z]]);
					}
				}
				return map;
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 根据中奖答案反查用户
	 * @author tangkunyin
	 * @return Map<String,KactivityUser>
	 * @throws SQLException 
	 */
	public static Map<String,KactivityUser> getWinners() throws SQLException{
		Map<String,Object> userMap=WinnerUtil.getWinnersAnswers();
		Set<String> set=userMap.keySet();
		Map<String,KactivityUser> finalUserMap=new TreeMap<String,KactivityUser>();
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		ps=conn.prepareStatement("SELECT * FROM kactivityuser where yourAnswer=?");
		for(Object key:set){
			String mykey=(String) key;
			int ua= (Integer) userMap.get(mykey);
			ps.setInt(1, ua);
			rs=ps.executeQuery();
			KactivityUser user=new KactivityUser();
			if(rs.next()){
				user.setUserId(Integer.parseInt(rs.getString("userId")));
				user.setNickname(rs.getString("nickname"));
				user.setSex(Short.parseShort(rs.getString("sex")));
				user.setHeadpicpath(rs.getString("headpicpath"));
				user.setKtvId(Integer.parseInt(rs.getString("ktvId")));
				user.setActivityId(Integer.parseInt(rs.getString("activityId")));
				user.setJoinTime(rs.getString("joinTime"));
				user.setYourAnswer(rs.getString("yourAnswer"));
			}
			finalUserMap.put(mykey, user);
		}
		//关闭资源
		JDBCUtil.closeDBResource(rs, ps, conn);
		return finalUserMap;
	}
	
	//测试
//	public static void main(String[] args) throws SQLException {
//		Map<String,KactivityUser> map=WinnerUtil.getWinners();
//		Set<String> set=map.keySet();
//		for(Object key:set){
//			String mykey=(String) key;
//			KactivityUser user=(KactivityUser) map.get(mykey);
//			System.out.println(mykey+"="+user.getYourAnswer());
//		}
//	}
}
