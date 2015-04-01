package com.vision.game.utils;
/**
 * 传入阿拉伯数字，返回中文的数字
 * @author tangkunyin
 */
public class NumberUtil {
	public static String num_Int2String(int n){
		String num;
		switch(n){
		case 1:num="一";break;
		case 2:num="二";break;
		case 3:num="三";break;
		case 4:num="四";break;
		case 5:num="五";break;
		case 6:num="六";break;
		case 7:num="七";break;
		case 8:num="八";break;
		case 9:num="九";break;
		case 10:num="十";break;
		default:num="";break;
		}
		return num;
	}
	
	public static int num_String2int(String num){
		int n;
		if(num.equalsIgnoreCase("一")){
			n=1;
		}else if(num.equalsIgnoreCase("二")){
			n=2;
		}else if(num.equalsIgnoreCase("三")){
			n=3;
		}else if(num.equalsIgnoreCase("四")){
			n=4;
		}else if(num.equalsIgnoreCase("五")){
			n=5;
		}else if(num.equalsIgnoreCase("六")){
			n=6;
		}else if(num.equalsIgnoreCase("七")){
			n=7;
		}else if(num.equalsIgnoreCase("八")){
			n=8;
		}else if(num.equalsIgnoreCase("九")){
			n=9;
		}else if(num.equalsIgnoreCase("十")){
			n=10;
		}else{
			System.out.println("开奖次数超过10次，系统异常，请检查...");
			n=0;
		}
		return n;
	}
}