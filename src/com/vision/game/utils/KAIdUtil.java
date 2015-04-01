package com.vision.game.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * 记录ktvId和活动id到文件的工具
 * @author tangkunyin
 */
public class KAIdUtil {
	//path
	static String ktvIdFilePath=KAIdUtil.class.getClassLoader().getResource("ktvid.properties").getPath();
	
	//读ktvId
	public static int readKtvId(){
		try {
			Properties pro=new Properties();
			pro.load(new FileReader(new File(ktvIdFilePath)));
			String in=pro.getProperty("ktvId");
			if(in!=null){
				int ktvId=Integer.parseInt(in);
				return ktvId;
			}else{
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	//读acId
	public static int readAcId(){
		try {
			Properties pro=new Properties();
			pro.load(new FileReader(new File(ktvIdFilePath)));
			String in=pro.getProperty("AcId");
			if(in!=null){
				int AcId=Integer.parseInt(in);
				return AcId;
			}else{
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	//写
	public static void writeId(String Id){
		try {
			String arr[]=Id.split("=");
			if(KAIdUtil.checkKtvIdFileStatus(arr[0])){
				BufferedWriter bw=new BufferedWriter(new FileWriter(new File(ktvIdFilePath),true));
				bw.write(Id);
				bw.newLine();
				bw.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean checkKtvIdFileStatus(String key){
		try {
			Properties pro=new Properties();
			pro.load(new FileReader(new File(ktvIdFilePath)));
			String flag=pro.getProperty(key);
			if(flag==null){
				//没有对应的键
				return true;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	//清空
	public static void setKtvIdEmpty(){
		try {
			BufferedWriter bw=new BufferedWriter(new FileWriter(new File(ktvIdFilePath)));
			bw.write("");
			bw.close();
			System.out.println("ktvId and activityId have been set empty");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
