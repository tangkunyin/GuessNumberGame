package com.vision.game.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vision.game.bean.KactivityInfo;
import com.vision.game.service.KactivityInfoService;

/**
 * 传入一个对象，返回该对象的信息集合
 * @author tangkunyin
 */
public class GameInfoUtil {
	public List<String> getAnswers(KactivityInfo gameInfo){
		List<String> list=new ArrayList<String>();
		if(gameInfo.getRightAnswer1()!=null){
			list.add(gameInfo.getRightAnswer1());
		}if(gameInfo.getRightAnswer2()!=null){
			list.add(gameInfo.getRightAnswer2());
		}if(gameInfo.getRightAnswer3()!=null){
			list.add(gameInfo.getRightAnswer3());
		}if(gameInfo.getRightAnswer4()!=null){
			list.add(gameInfo.getRightAnswer4());
		}if(gameInfo.getRightAnswer5()!=null){
			list.add(gameInfo.getRightAnswer5());
		}if(gameInfo.getRightAnswer6()!=null){
			list.add(gameInfo.getRightAnswer6());
		}if(gameInfo.getRightAnswer7()!=null){
			list.add(gameInfo.getRightAnswer7());
		}if(gameInfo.getRightAnswer8()!=null){
			list.add(gameInfo.getRightAnswer8());
		}if(gameInfo.getRightAnswer9()!=null){
			list.add(gameInfo.getRightAnswer9());
		}if(gameInfo.getRightAnswer10()!=null){
			list.add(gameInfo.getRightAnswer10());
		}
		return list;
	}
	
	public List<String> getPrizes(KactivityInfo gameInfo){
		List<String> list=new ArrayList<String>();
		if(gameInfo.getPrize1()!=null){
			list.add(gameInfo.getPrize1());
		}if(gameInfo.getPrize2()!=null){
			list.add(gameInfo.getPrize2());
		}if(gameInfo.getPrize3()!=null){
			list.add(gameInfo.getPrize3());
		}if(gameInfo.getPrize4()!=null){
			list.add(gameInfo.getPrize4());
		}if(gameInfo.getPrize5()!=null){
			list.add(gameInfo.getPrize5());
		}if(gameInfo.getPrize6()!=null){
			list.add(gameInfo.getPrize6());
		}if(gameInfo.getPrize7()!=null){
			list.add(gameInfo.getPrize7());
		}if(gameInfo.getPrize8()!=null){
			list.add(gameInfo.getPrize8());
		}if(gameInfo.getPrize9()!=null){
			list.add(gameInfo.getPrize9());
		}if(gameInfo.getPrize10()!=null){
			list.add(gameInfo.getPrize10());
		}
		return list;
	}
	
	/**
	 * addAcUserTimes
	 */
	public void addAcUserTimes(int AcId){
		KactivityInfoService kis=(KactivityInfoService) SpringUtil.getBean("GameInfoService");
		int n=kis.conutCurrentAcUsers(AcId)+1;
		//使用JDBC取得对应模版的基数
		try {
			Connection conn=JDBCUtil.getConnection();
			String sql="UPDATE kactivityinfo SET acCrrentPeople="+n+" WHERE activityId="+AcId;
			PreparedStatement ps=conn.prepareStatement(sql);
			int rs=ps.executeUpdate();
			if(rs!=0){
				System.out.println("有人参加活动，已完成提交...");
			}
			JDBCUtil.closeDBResource(null, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}