package com.vision.game.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.vision.game.utils.JDBCUtil;
import com.vision.game.utils.KAIdUtil;
/**
 * 首页转向控制器
 * @author tangkunyin
 */
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//通过session获取ktvId，然后根据当天的日期，共同查得某个具体的活动，并在TV上做显示
		IndexServlet index=new IndexServlet();
		String ktvId=null;
		String rid=request.getParameter("ktvId");                               //通过url获取ktvId
		int fid=KAIdUtil.readKtvId();											//通过文件获取id			
		if(rid!=null){
			ktvId=rid;
			KAIdUtil.writeId("ktvId="+ktvId);
		}else{
			ktvId=fid+"".trim();
		}
		Map<String,Object> map=index.getActivityStartTime(Integer.parseInt(ktvId));
		if(map!=null){
			int acStart=(Integer) map.get("acStart");
			int acEnd=(Integer) map.get("acEnd");
			int acClose=(Integer) map.get("acClose");
			
			String startTime=(String) map.get("startTime");
			String endTime=(String) map.get("endTime");
			String closeTime=(String) map.get("closeTime");
			
			String acId=(String) map.get("acId");
			//将活动id写入文件
			KAIdUtil.writeId("AcId="+acId);
			
			if(acStart==-1 && acEnd==-1 && acClose==-1){   //广播
				request.setAttribute("acStartTime", startTime);
				request.getRequestDispatcher("TVIndex").forward(request, response);
			}else if(acStart==1 && acEnd==-1 && acClose==-1){ //用户提交
				request.setAttribute("acEndTime", endTime);
				request.getRequestDispatcher("TVSubmit").forward(request, response);
			}else if(acStart==1 && acEnd==1 && acClose==-1){  //摇奖中
				request.setAttribute("acCloseTime",closeTime);
				request.getRequestDispatcher("KaiJiangServlet").forward(request, response);
			}else if(acStart==1 && acEnd==1 && acClose==1){  //开奖，显示中奖用户
				request.getRequestDispatcher("FinishedServlet").forward(request, response);
			}
		}else{
			//没有活动，或者查询失败
			request.setAttribute("indexError", "活动信息读取失败");
			request.getRequestDispatcher("/error/error.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
	//查询游戏时间是否到，如果没到，则显示广播，游戏开始时，显示用户提交的数字
	public Map<String,Object> getActivityStartTime(int ktvId){
		SimpleDateFormat dateType = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss", Locale.CHINA);
		String today=IndexServlet.getWeek();
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="SELECT activityId,acStartDate,acStartTime,acEndTime,acCloseTime FROM kactivityinfo WHERE WEEK='"+today+"' and ktvId="+ktvId;
		try {
			ps=conn.prepareStatement(sql);
			rs= ps.executeQuery();
			if(rs.next()){
				String acStartDate="";
				String acStartTime="";
				String acEndTime="";
				String acCloseTime="";
				String activityId="";
				rs.previous();
				while(rs.next()){
					acStartDate=rs.getString("acStartDate");
					acStartTime=rs.getString("acStartTime");
					acEndTime=rs.getString("acEndTime");
					acCloseTime=rs.getString("acCloseTime");
					activityId=rs.getString("activityId");
				}
				Date nowTime=new Date();  //现在时间
				Date acStart=dateType.parse(acStartDate+","+acStartTime);
				Date acEnd=dateType.parse(acStartDate+","+acEndTime);
				Date acClose=dateType.parse(acStartDate+","+acCloseTime);
				
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("acStart", nowTime.compareTo(acStart));
				map.put("acEnd", nowTime.compareTo(acEnd));
				map.put("acClose", nowTime.compareTo(acClose));
				
				String startDate=acStartDate.replace('-', '/');
				map.put("startTime",startDate+" "+acStartTime);
				map.put("endTime", startDate+" "+acEndTime);
				map.put("closeTime",startDate+" "+acCloseTime);
				
				map.put("acId", activityId);
				return map;
			}else{
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			JDBCUtil.closeDBResource(rs, ps, conn);
		}
	}
	//查询今天是周几
	public static String getWeek(){
		SimpleDateFormat weekType = new SimpleDateFormat("E",Locale.CHINA);
		String today;
		String day=weekType.format(new Date());
		if(day.equalsIgnoreCase("星期一")){
			today="周一";
		}else if(day.equalsIgnoreCase("星期二")){
			today="周二";
		}else if(day.equalsIgnoreCase("星期三")){
			today="周三";
		}else if(day.equalsIgnoreCase("星期四")){
			today="周四";
		}else if(day.equalsIgnoreCase("星期五")){
			today="周五";
		}else if(day.equalsIgnoreCase("星期六")){
			today="周六";
		}else{
			today="周日";
		}
		return today;
	}
}
