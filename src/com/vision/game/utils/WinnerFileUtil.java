package com.vision.game.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.vision.game.bean.KactivityUser;

/**
 * 获奖用户文件更改器
 * 此时不能在读一行删一行，否则用户一旦刷新浏览器，就没数据了
 * @author tangkunyin
 */
public class WinnerFileUtil {
	public synchronized static  List<String> readOrder(){
		try {
			String path=WinnerFileUtil.class.getClassLoader().getResource("winner.txt").getPath();
			File file=new File(path);
			BufferedReader fr = new BufferedReader(new FileReader(file));
			List<String> list=new ArrayList<String>();
			String content=null;
			while((content=fr.readLine())!=null){
				list.add(content);
			}
			if(list.size()!=0){
				return list;
			}else{
				list.add("nobody");
				list.add("nobody");
				return list;
			}
		} catch (IOException e) {
			System.err.println("winner.txt read error....");
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 设置文件只读
	 */
	public static void setFileOnlyRead(){
		String path=WinnerFileUtil.class.getClassLoader().getResource("winner.txt").getPath();
		File file=new File(path);
		file.setReadOnly();
	}
	/**
	 * 设置文件读写状态
	 */
	public static void setFileWRable(){
		String path=WinnerFileUtil.class.getClassLoader().getResource("winner.txt").getPath();
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
	public synchronized static void writeOrder(KactivityUser user){
		try {
			String path=WinnerFileUtil.class.getClassLoader().getResource("winner.txt").getPath();
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
	public synchronized static  void setOrderEmpty(){
		try {
			String path=WinnerFileUtil.class.getClassLoader().getResource("winner.txt").getPath();
			File file=new File(path);
			file.setWritable(true);
			BufferedWriter bw=new BufferedWriter(new FileWriter(file));
			bw.write("");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
