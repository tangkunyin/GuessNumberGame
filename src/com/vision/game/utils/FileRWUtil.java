package com.vision.game.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.vision.game.bean.KactivityUser;

/**
 * 文件读写工具
 * 用于页面遥控
 * @author tangkunyin
 * @see www.hadooper.org
 * @since 2013-07-17
 */
public class FileRWUtil {
	static Logger logger=Logger.getLogger(FileRWUtil.class);
	/**
	 * 写入数据：以追加的方式写。此处要保证单线程
	 * @param text
	 */
	public synchronized static void writeToFile(String text){
		try {
			BufferedWriter writer=new BufferedWriter(new FileWriter(FileRWUtil.getFilePath(),true));
			if(FileRWUtil.modifyNumFile(text)){
				writer.write(text);
				writer.newLine();  //写入换行
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("write to file error...");
		}
	}
	
	//修改文件
	public synchronized static boolean modifyNumFile(String text){
		//先检查，再做判断，如果有记录，则修改。没有返回true;
		try {
			File file=new File(FileRWUtil.getFilePath());
			BufferedReader reader=new BufferedReader(new FileReader(file));
			List<String> list=new ArrayList<String>();
			String content=null;
			while((content=reader.readLine())!=null){
				list.add(content);
			}
			if(list.size()!=0){
				if(text.startsWith("g", 0)){
					list.set(0, text);
				}else if(text.startsWith("s",0)){
					list.set(1, text);
				}else if(text.startsWith("b", 0)){
					list.set(2, text);
				}
				//改完之后再把集合写到文件中
				BufferedWriter writer=new BufferedWriter(new FileWriter(file));
				for(int i=0;i<list.size();i++){
					writer.write(list.get(i));
					writer.newLine();
					writer.flush();
				}
				writer.close();
			}else{
				return true;
			}
		} catch (Exception e) {
			if(e.getClass().equals(IndexOutOfBoundsException.class)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 读取数据
	 * @return
	 */
	public static String[] readFromFile(){
		Properties pro=new Properties();
		try {
			pro.load(new FileReader(new File(FileRWUtil.getFilePath())));
			String arr[]=new String[3];
			arr[0]=pro.getProperty("gw");
			arr[1]=pro.getProperty("sw");
			arr[2]=pro.getProperty("bw");
			return arr;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("numbers.properties file does not exists or error ocured when reading it");
		} 
		return null;
	}
	//检查文件中的内容，如果有“bw”，则清空文件中的内容
	public static void checkNumFile(){
		try {
			BufferedReader reader=new BufferedReader(new FileReader(new File(FileRWUtil.getFilePath())));
			String text;
			while(true){
				text=reader.readLine();
				if(text==null){
					break;
				}else{
					if(text.startsWith("bw")){
						FileWriter writer=new FileWriter(new File(FileRWUtil.getFilePath()));
						writer.write("");
						writer.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//清空输入接收文件
	public static void setNumFileEmpty(){
		try {
			FileRWUtil.setFileWRable("numbers.properties");
			FileWriter writer=new FileWriter(new File(FileRWUtil.getFilePath()));
			writer.write("");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取文件地址，如果没有则新建文件
	 * @return filePath
	 */
	public static String getFilePath(){
		String path;
		try {
			path = FileRWUtil.class.getClassLoader().getResource("numbers.properties").getPath();
			return path;
		} catch (Exception e) {
			logger.info("The numbers.properties file under classes can not be found---->System error");
			return null;
		}
	}
	
	
	//测试  数据格式：gw=3   sw=4  bw=5
//	public static void main(String[] args) {
//		String arr[]=new String[3];
//		arr[0]="gw=6";
//		arr[1]="sw=7";
//		arr[2]="bw=8";
//		for(int i=0;i<3;i++){
//			FileRWUtil.writeToFile(arr[i]);
//		}
//		
//		String arr1[]=FileRWUtil.readFromFile();
//		for(int i=0;i<3;i++){
//			System.out.println(arr1[i]);
//		}
//		
//	}
	
	//===============================================================================================//
	//点击更换用户的处理如下
	
	/**
	 * 读取文件内容。每次读取第一个，读完就删除该行
	 * @author tangkunyin
	 * @return 第一条数据
	 */
	public synchronized static  String readWinnersOrder(){
		try {
			String path=FileRWUtil.class.getClassLoader().getResource("currentUsers.txt").getPath();
			File file=new File(path);
			BufferedReader fr = new BufferedReader(new FileReader(file));
			List<String> list=new ArrayList<String>();
			String content=null;
			while((content=fr.readLine())!=null){
				list.add(content);
			}
			if(list.size()!=0){
				String TheFirst=list.get(0);
				list.remove(0);
				//再把list写进去
				file.setWritable(true);
				BufferedWriter bw=new BufferedWriter(new FileWriter(file));
				for(int i=0;i<list.size();i++){
					bw.write(list.get(i));
					bw.newLine();
					bw.flush();
				}
				bw.close();
				file.setReadOnly();
				return TheFirst;
			}else{
				return "nobody";
			}
		} catch (IOException e) {
			System.err.println("currentUsers.txt read error....");
			e.printStackTrace();
			return null;
		}
	}
	
	public static void delSpecialUser() throws SQLException{
		Map<String,KactivityUser> map=WinnerUtil.getWinners();
		Set<String> set=map.keySet();
		for(Object key:set){
			String mykey=(String) key;
			if(mykey.equalsIgnoreCase("specialUser")){
				map.remove("specialUser");
				continue;
			}else{
				FileRWUtil.setFileWRable("currentUsers.txt");
				KactivityUser user=map.get(mykey);
				FileRWUtil.writeWinnerOrder(user);       //将候奖者名单写入文件，以备后用
			}
		}
		FileRWUtil.setFileOnlyRead("currentUsers.txt");
	}
	
	/**
	 * 设置文件只读
	 */
	public static void setFileOnlyRead(String fileName){
		String path=FileRWUtil.class.getClassLoader().getResource(fileName).getPath();
		File file=new File(path);
		file.setReadOnly();
	}
	/**
	 * 设置文件读写状态
	 */
	public static void setFileWRable(String fileName){
		String path=FileRWUtil.class.getClassLoader().getResource(fileName).getPath();
		File file=new File(path);
		if(file.setWritable(true)){
			System.err.println(file.getName()+",can write and read");
		}
	}
	
	
	/**
	 * 向文件中写数据，在写的时候判断文件中是否有数据，如果有，就不能再写
	 * 格式：{"user":[{"headpicpath":"xx","nickName":"yy","sex":"zz","yourAnswer":"vv"}]}
	 * @param text
	 */
	public synchronized static void writeWinnerOrder(KactivityUser user){
		try {
			String path=FileRWUtil.class.getClassLoader().getResource("currentUsers.txt").getPath();
			File file=new File(path);
			if(file.canWrite()){
				BufferedWriter bw=new BufferedWriter(new FileWriter(file,true));
				StringBuffer arr=new StringBuffer();
				arr.append("{\"user\":");
				arr.append("[{\"headpicpath\":\""+user.getHeadpicpath()+"\",");
				arr.append("\"nickName\":\""+user.getNickname()+"\",");
				if(user.getSex()==0){
					arr.append("\"sex\":\"男\",");
				}else{
					arr.append("\"sex\":\"女\",");
				}
				arr.append("\"yourAnswer\":\""+user.getYourAnswer()+"\"}]}");
				JSONObject json=JSONObject.fromObject(arr.toString());
				bw.write(json.toString());
				bw.newLine();
				bw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//清空，当用户点击显示到TV时，就清空文件所有内容
	public synchronized static  void setWinnerOrderEmpty(){
		try {
			String path=FileRWUtil.class.getClassLoader().getResource("currentUsers.txt").getPath();
			File file=new File(path);
			file.setWritable(true);
			BufferedWriter bw=new BufferedWriter(new FileWriter(file));
			bw.write("");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//----------测试
//	public static void main(String[] args) {
//		KactivityUser user=new KactivityUser();
//		user.setHeadpicpath("http://www.vison.com.cn");
//		user.setNickname("你猜");
//		user.setSex((short) 23);
//		user.setYourAnswer("125");
//		FileRWUtil.writeWinnerOrder("user0",user);
//	}
}