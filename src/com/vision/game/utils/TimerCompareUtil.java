package com.vision.game.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import com.vision.game.bean.GameLastTime;
/**
 * 计算时差
 * @author tangkunyin
 * 活动间隔不超过12小时。
 */
public class TimerCompareUtil {
	/**
	 * @author tangkunyin
	 * @param ar1:开始时间：xxxx年xx月xx日,xx时xx分xx秒
	 * @param ar2:结束时间：xxxx年xx月xx日,xx时xx分xx秒
	 * @param ar3:开奖结束时间：xxxx年xx月xx日,xx时xx分xx秒
	 * @return Map
	 * @throws ParseException
	 */
	public static Map<String,Object> compareDate(String str) throws ParseException{
		SimpleDateFormat dateType = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss", Locale.CHINA);
		
		String arr[]=str.split("#");
		
		Date acStartTime=dateType.parse(arr[0]);
		Date acEndTime=dateType.parse(arr[1]);
		Date acCloseTime=dateType.parse(arr[2]);
		
		//活动持续时间，初始单位：秒
		Long acLastSec=(acEndTime.getTime()-acStartTime.getTime())/1000;
		Long acLastMin=acLastSec/60;  //活动持续分钟数
		Long acLastHr=acLastMin/60;   //活动持续小时数
		int acLastDays=(int) (acLastHr/24);  //活动持续天数

		//开奖持续时间，初始单位：秒
		Long openAcLastSec=(acCloseTime.getTime()-acEndTime.getTime())/1000;
		Long openAcLastMin=openAcLastSec/60;  //开奖持续分钟数
		Long openAcLastHr=openAcLastMin/60;   //开奖持续小时数
		int openAcLastDays=(int) (openAcLastHr/24);  //开奖持续天数
		
		Map<String,Object> map=new HashMap<String,Object>();
		if(acLastDays>0){
			map.put("acLastDays", acLastDays);
		}else{
			if(acLastHr>0){
				map.put("acLastHr", acLastHr);
			}else{
				if(acLastMin>0){
					map.put("acLastMin", acLastMin);
				}else{
					map.put("acLastSec", acLastSec);
				}
			}
		}
		if(openAcLastDays>0){
			map.put("openAcLastDays", openAcLastDays);
		}else{
			if(openAcLastHr>0){
				map.put("openAcLastHr", openAcLastHr);
			}else{
				if(openAcLastMin>0){
					map.put("openAcLastMin", openAcLastMin);
				}else{
					map.put("openAcLastSec", openAcLastSec);
				}
			}
		}
		return map;
	}

	/**
	 * 使用可变参数，注意：参数顺序不能乱，否则就会出错
	 * @param args
	 * @return
	 */
	public static String getSpecificTime(String...args){
		String acStartTime=args[0]+","+args[1];
		String acEndTime=args[2]+","+args[3];
		String acCloseTime=args[4]+","+args[5];
		return acStartTime+"#"+acEndTime+"#"+acCloseTime;
	}
	
	//返回时差
	public static GameLastTime getTimeDifference(String...args) throws ParseException{
		GameLastTime gameLast=new GameLastTime();
		String specificTime=TimerCompareUtil.getSpecificTime(args);
		Map<String,Object> timeMap=TimerCompareUtil.compareDate(specificTime);
		if(timeMap.isEmpty()){
			return null;
		}else{
			//迭代
			Set<?> timeSet=timeMap.keySet();
			for(Object s:timeSet){
				String mykey=(String) s;
				Object value=(Object) timeMap.get(mykey);
				if(mykey.equalsIgnoreCase("acLastSec")){
					gameLast.setAcLastSec(value.toString());
				}else if(mykey.equalsIgnoreCase("acLastMin")){
					gameLast.setAcLastMin(value.toString());
				}else if(mykey.equalsIgnoreCase("acLastHr")){
					gameLast.setAcLastHr(value.toString());
				}else if(mykey.equalsIgnoreCase("openAcLastSec")){
					gameLast.setOpenAcLastSec(value.toString());
				}else if(mykey.equalsIgnoreCase("openAcLastMin")){
					gameLast.setOpenAcLastMin(value.toString());
				}else if(mykey.equalsIgnoreCase("openAcLastHr")){
					gameLast.setOpenAcLastHr(value.toString());
				}
			}
			return gameLast;
		}
	}
	
	//测试
//	public static void main(String[] args) throws ParseException {
//		String ar1="2013-06-18";
//		String ar2="16:33:15";
//
//		String ar3="2013-06-19";
//		String ar4="21:00:00";
//		
//		String ar5="2013-06-19";
//		String ar6="21:30:00";
//		
//		GameLastTime gameLast=TimerCompareUtil.getTimeDifference(ar1,ar2,ar3,ar4,ar5,ar6);
//		System.out.println(gameLast.toString());
//	}
}
