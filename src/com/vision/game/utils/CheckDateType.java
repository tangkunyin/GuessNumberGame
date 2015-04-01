package com.vision.game.utils;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.vision.game.service.KactivityTemplaiteService;

/**
 * 验证用户选择的日期是否正常。如果日期在一天，则要判断当天具体时间
 * 在新建模版时，采用Ajax
 * @author tangkunyin
 * @since 2013-06-04
 */
public class CheckDateType extends MultiActionController{
	//时间比较
	public static int compareTimes(String...str) throws ParseException{
		if(str.length==3){
			SimpleDateFormat dateType = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
			Date acStartTime=dateType.parse(str[0]);
			Date acEndTime=dateType.parse(str[1]);
			Date acEndTime2=dateType.parse(str[2]);
			
			int t1=acStartTime.compareTo(acEndTime);
			int t2=acEndTime.compareTo(acEndTime2);
			if(t1<0 && t2<0){
				return 1;
			}else{
				return -1;
			}
		}else if(str.length==1){
			SimpleDateFormat dateType = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss", Locale.CHINA);
			String arr[]=str[0].split("#");
			
			Date acStartTime=dateType.parse(arr[0]);
			Date acEndTime=dateType.parse(arr[1]);
			Date acCloseTime=dateType.parse(arr[2]);
			
			int t4=acStartTime.compareTo(acEndTime);
			int t5=acEndTime.compareTo(acCloseTime);
			if(t4<0 && t5<0){
				return 1;
			}else{
				return -1;
			}
		}else{
			System.err.println("数据个数不正确，日期校验失败...");
			return 0;
		}
	}
	//构造日期格式:yyyy-MM-dd,HH:mm:ss
	public static String getSpecificTime(String...args){
		String acStartTime=args[0]+","+args[1];
		String acEndTime=args[2]+","+args[3];
		String acCloseTime=args[4]+","+args[5];
		return acStartTime+"#"+acEndTime+"#"+acCloseTime;
	}
	//传入具体时间：yyyy-MM-dd,HH:mm:ss，返回星期:星期五,21:22:22
	public static String getWeek(String time) throws ParseException{
		SimpleDateFormat dateType = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss", Locale.CHINA);
		SimpleDateFormat weekType = new SimpleDateFormat("E",Locale.CHINA);
		Date weekTime=dateType.parse(time);
		String week=weekType.format(weekTime);
		if(week.equalsIgnoreCase("星期一")){
			return "周一";
		}else if(week.equalsIgnoreCase("星期二")){
			return "周二";
		}else if(week.equalsIgnoreCase("星期三")){
			return "周三";
		}else if(week.equalsIgnoreCase("星期四")){
			return "周四";
		}else if(week.equalsIgnoreCase("星期五")){
			return "周五";
		}else if(week.equalsIgnoreCase("星期六")){
			return "周六";
		}else{
			return "周日";
		}
	}
	
	public static Date getGameoverTime(String date){
		SimpleDateFormat dateType = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss", Locale.CHINA);
		try {
			Date gameoverDate=dateType.parse(date);
			return gameoverDate;
		} catch (ParseException e) {
			System.out.println("gameoverDate parse error...");
			e.printStackTrace();
			return null;
		}
	}
	
	//检查活动日期合法性
	public ModelAndView CheckAcDate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//取到ajax发送过来的数据
		String acStartDate=request.getParameter("StartDate");
		String acStartTime=request.getParameter("StartTime");
		String acEndTime=request.getParameter("EndTime");
		String acCloseTime=request.getParameter("CloseTime");
		
		String text=CheckDateType.getSpecificTime(acStartDate,acStartTime,acStartDate,acEndTime,acStartDate,acCloseTime);
		int n=CheckDateType.compareTimes(text);
		if(n==1){
			out.write("");
		}else{
			out.write("时间格式不合法，请注意时间先后");
		}
		out.close();
		return null;
	}
	
	//检查模版名称有效性--唯一性
	public ModelAndView CheckTempNames(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String acname=request.getParameter("name");
		String KeyWord=new String(acname.getBytes("ISO-8859-1"),"UTF-8");
		KactivityTemplaiteService KacTemplaiteService = (KactivityTemplaiteService) SpringUtil.getBean("KacTemplaiteService");
		int n=KacTemplaiteService.countTempNames(KeyWord);
		if(n==0){
			out.write("yes");
		}else{
			out.write("no");
		}
		out.close();
		return null;
	}
	//检查模版时间设置的合理性
//	public ModelAndView CheckTempTimes(HttpServletRequest request,
//			HttpServletResponse response) throws Exception{
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;charset=UTF-8");
//		PrintWriter out = response.getWriter();
//		//设置开始具体时间
//		SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss",Locale.CHINA);
//		String acStartTime=sdf.format(new Date());
//		String acEndTime=request.getParameter("EndTime");
//		String acEndTime2=request.getParameter("EndTime2");
//		
//		int n=CheckDateType.compareTimes(acStartTime,acEndTime,acEndTime2);
//		if(n<0){
//			out.write("errorTime");
//			out.close();
//		}else{
//			//将开始时间存入session
//			HttpSession session=request.getSession();
//			session.setAttribute("acTempStartTime", acStartTime);
//		}
//		return null;
//	}
}
