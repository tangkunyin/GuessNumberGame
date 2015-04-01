package com.vision.game.utils;

import java.util.List;

import net.sf.json.JSONArray;
import org.apache.ibatis.session.SqlSession;

import com.vision.game.bean.SongHallInfo;

/**
 * 通过Mybatis模糊查询，提供songhallinfo表中的songhallname值
 * @author tangkunyin
 * @serial 2013-06-08
 */
public class SelectIdAndNameFromSHI {
	//直接使用Mybatis，不用Spring
	public static String getSongHallNames(String SongHallName){
		SqlSession session=DataSourceUtils.getSession();
		List<SongHallInfo> songhalls=session.selectList("selectNameByLikeName",SongHallName);
		if(songhalls.size()==0){
			//构建一个json格式的数据返回 {'错误':'[不能这么搞]'}
			return "{'错误':'[查无此项,请输入正确关键字]'}";
		}
		//将结果拼成串
		String strs="";
		for(SongHallInfo ars:songhalls){
			strs+=ars.getSongHallName()+",";
		}
		//将最后一个','去掉
		String name=strs.substring(0, strs.length()-1);
		String names[]=name.split(",");
		//将此串，变成json格式返回
		JSONArray jsonNames=JSONArray.fromObject(names);
		session.close();
		System.out.println("客户端检索："+jsonNames);
		return jsonNames.toString();
	}
	//获取id
	public static int getSongHallId(String name){
		SqlSession session=DataSourceUtils.getSession();
		int id=session.selectOne("selectIdByName", name);
		session.close();
		return id;
	}
	
	//测试
	/*public static void main(String[] args) {
		//System.out.println(SelectIdAndNameFromSHI.getSongHallId("金库KTV"));
		SelectIdAndNameFromSHI.getSongHallNames("麦乐迪");
	}*/
}
