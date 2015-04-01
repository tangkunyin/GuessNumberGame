package com.vision.game.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.vision.game.utils.SelectIdAndNameFromSHI;

/**
 * 通过表单输入的ktvName从SongHallInfo中匹配信息
 * 返回具体Ktv名字
 * @author tangkunyin
 * @see 2013-06-03
 */
public class QueryShiFromKtvName extends AbstractController{

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//取到ajax发送过来的数据
		String params=request.getParameter("searchContent");
		String KeyWord=new String(params.getBytes("ISO-8859-1"),"UTF-8");
		String names=SelectIdAndNameFromSHI.getSongHallNames(KeyWord);
		PrintWriter out=response.getWriter();
		if(names.equalsIgnoreCase("{'错误':'[查无此项,请输入正确关键字]'}")){
			out.write(JSONObject.fromObject(names).toString());
		}else{
			out.write(names);
		}
		out.close();
		return null;
	}
}
