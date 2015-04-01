package com.vision.game.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.vision.game.bean.KactivityInfo;
import com.vision.game.bean.KactivityUser;
import com.vision.game.service.KactivityInfoService;
import com.vision.game.service.KactivityUserService;
import com.vision.game.utils.FileRWUtil;
import com.vision.game.utils.GameOrderUtil;
import com.vision.game.utils.KAIdUtil;
import com.vision.game.utils.NumberUtil;
import com.vision.game.utils.SpringUtil;
import com.vision.game.utils.WinnerFileUtil;
import com.vision.game.utils.WinnerUtil;
/**
 * 通过Ajax异步返回给客户端需要的数据，在TV端做无刷新显示
 * 使用HttpClient将请求后的数据用ajax写到页面上
 * @author tangkunyin
 * @since 2013-07-15 
 */
public class TVShowController extends MultiActionController {
	/**
	 * 控制游戏输入的数字。摇奖次数要和奖品设置个数对应
	 * 将所有文件内容清空，保存获奖用户[根据ajax提交过来的用户答案]，进入下一轮
	 * @param request
	 * @param response
	 * @return null
	 * @throws Exception
	 */
	public ModelAndView InitiaInputNums(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter out=response.getWriter();
		int i=NumberUtil.num_String2int(GameOrderUtil.getGameOrder());
		KactivityInfoService gameInfoService=(KactivityInfoService) SpringUtil.getBean("GameInfoService");
		KactivityInfo gameInfo=gameInfoService.findByGameAcId(KAIdUtil.readAcId());
		if(gameInfo!=null){
			String uanswer=request.getParameter("uanswer");
			String luanswer=request.getParameter("luanswer");
			String ruanswer=request.getParameter("ruanswer");
			//更新活动摇奖的答案信息
			String str[]=FileRWUtil.readFromFile();
			if(GameOrderUtil.updateGameAnswers(i, (str[2]+str[1]+str[0]), gameInfo.getActivityId())){
				System.out.println(gameInfo.getActivityId()+"，号活动成功更新第："+i+"个答案");
			}
			//保存用户，更新获奖者信息
			KactivityUserService acUserService=(KactivityUserService) SpringUtil.getBean("kacUserService");
			if(uanswer!=null){
				acUserService.updateWinAcUser(uanswer);
				acUserService.updateAcWinUserTh(i, uanswer);
				System.out.println("答案为："+uanswer+"，的获奖者信息已更新到数据库！");
			}else if(luanswer!=null && ruanswer!=null){
				acUserService.updateWinAcUser(luanswer);
				acUserService.updateAcWinUserTh(i, luanswer);
				
				acUserService.updateWinAcUser(ruanswer);
				acUserService.updateAcWinUserTh(i, ruanswer);
				System.out.println("答案为："+luanswer+"和"+ruanswer+"，的获奖者信息已更新到数据库！");
			}else{  //Ajax丢参数时
				System.out.println("Ajax请求可能丢失参数，请核查...");
			}
			
			FileRWUtil.setFileWRable("currentUsers.txt");
			WinnerFileUtil.setFileWRable();
			
			FileRWUtil.checkNumFile();
			FileRWUtil.setWinnerOrderEmpty();
			WinnerFileUtil.setOrderEmpty();
			
			i++;
			if(gameInfo.getWinNum()>=i){
				GameOrderUtil.updateGameOrder(i);   //更新序数。更新之后在判断
				out.write("next");
				out.close();
			}else{
				GameOrderUtil.setGameOrderDefault();  //回复默认，以后有可能跳到获奖用户展示页面
				out.write("end");
				out.close();
			}
		}else{
			System.out.println("活动详情获取失败，请检查...");
			out.write("error");
			out.close();
		}
		return null;
	}
	
	//返回广播数据信息
	public ModelAndView ShowBroadCast(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String urlPrev=getServletContext().getInitParameter("url-prev");
		response.setContentType("text/html;charset=UTF-8");
		String url=urlPrev+request.getContextPath()+"/Index";
		String content=TVShowController.getTVHtmlByHttpClient(url);
		PrintWriter out=response.getWriter();
		if(content!=""){
			out.write(content);
			out.close();
		}
		return null;
	}
	//返回用户提交数据信息
	public ModelAndView ShowSubmits(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String urlPrev=getServletContext().getInitParameter("url-prev");
		response.setContentType("text/html;charset=UTF-8");
		String url=urlPrev+request.getContextPath()+"/TVSubmit";
		String content=TVShowController.getTVHtmlByHttpClient(url);
		PrintWriter out=response.getWriter();
		if(content!=""){
			out.write(content);
			out.close();
		}
		return null;
	}
	//返回个、十、百每位数字的显示
	public ModelAndView ShowNumbers(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		String gnum=request.getParameter("gwinput");
		String snum=request.getParameter("swinput");
		String bnum=request.getParameter("bwinput");
		
		if(gnum!=null){
			FileRWUtil.writeToFile("gw="+gnum);
			out.write("gwok");
			out.flush();
		}else if(snum!=null){
			FileRWUtil.writeToFile("sw="+snum);
			out.write("swok");
			out.flush();
		}else if(bnum!=null){
			FileRWUtil.writeToFile("bw="+bnum);
			out.write("bwok");
			out.flush();
		}
		return null;
	}
	//
	public ModelAndView ShowKJNumber(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		String num[]=FileRWUtil.readFromFile();
		if(num[0]==null){
			num[0]="null";
		}
		if(num[1]==null){
			num[1]="null";
		}
		if(num[2]==null){
			num[2]="null";
		}
		//将该数组构成json并返回
		JSONArray json=JSONArray.fromObject(num);
		out.write(json.toString());
		out.close();
		return null;
	}
	
	//根据id查找指定活动的参加人数
	public ModelAndView ShowTotalUsersNum(HttpServletRequest request,
			HttpServletResponse response) {
		KactivityInfoService kis=(KactivityInfoService) SpringUtil.getBean("GameInfoService");
		//查询参与这次活动的总人数
		try {
			int totalUsers=kis.conutCurrentAcUsers(KAIdUtil.readAcId());
			PrintWriter out=response.getWriter();
			out.write(totalUsers+"");
			out.close();
		} catch (Exception e) {
			System.err.println("KAIdUtil.readAcId()="+KAIdUtil.readAcId()+",这个参数可能有问题，请检查...TVShowController");
			try {
				PrintWriter out = response.getWriter();
				out.write(0);
				out.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 显示每组获奖用户。
	 * @author tangkunyin
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView ShowWinners(HttpServletRequest request,
			HttpServletResponse response) {
		List<String> list=new ArrayList<String>();
		try {
			Map<String,KactivityUser> map=WinnerUtil.getWinners();
			Set<String> set=map.keySet();
			for(Object key:set){
				String mykey=(String) key;
				KactivityUser user=map.get(mykey);
				FileRWUtil.writeWinnerOrder(user);       //将候奖者名单写入文件，以备后用
				list.add(mykey);
			}
			FileRWUtil.setFileOnlyRead("currentUsers.txt");                //侯奖者名单写完了之后，就设置该文件只读。以防重写记录增多
			String str[]=FileRWUtil.readFromFile();
			request.setAttribute("rightAnswer", str[2]+str[1]+str[0]);
			for(int i=0;i<list.size();i++){
				String name= list.get(i);
				if(name.startsWith("special")){  //特殊用户单页显示
					request.setAttribute("specialUser", map.get("specialUser"));
					return new ModelAndView("admin/KJ-singleUser");
				}
			}
			for(int i=0;i<list.size();i++){
				String name= list.get(i);
				if(name.startsWith("normal")){  //一般用户一次显示俩
					return new ModelAndView("admin/KJ-doubleUser");
				}
			}
		} catch (Exception e) {
			if(e.getClass().equals(NullPointerException.class)){
				System.out.println("发现空指针异常");
			}
		}
		return new ModelAndView("admin/KJInput");
	}
	//显示双人获奖组。还要去掉标准用户
	public ModelAndView ShowDoubleWinners(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		FileRWUtil.delSpecialUser();
		String str[]=FileRWUtil.readFromFile();
		request.setAttribute("rightAnswer", str[2]+str[1]+str[0]);
		//一般用户一次显示俩
		return new ModelAndView("admin/KJ-doubleUser");
	}
	
	//初始化页面
	public ModelAndView InitiaWinner(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		String user1=FileRWUtil.readWinnersOrder();
		String user2=FileRWUtil.readWinnersOrder();
		out.write(user1+"#"+user2);
		out.close();
		return null;
	}
	
	/**
	 * 更换用户，当手机端点击重现选择时，返回一个用户
	 */
	public ModelAndView changeWinner(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.write(FileRWUtil.readWinnersOrder());
		out.close();
		return null;
	}
	/**
	 * 当用户点击显示到tv时，就清空文件的内容
	 * @param request
	 * @param response
	 * @return null
	 * @throws Exception
	 */
	public ModelAndView setWinnerOrderEmpty(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		FileRWUtil.setWinnerOrderEmpty();
		return null;
	}
	
	/**
	 * 将最终获奖用户写入文件
	 * @author tangkunyin
	 */
	public ModelAndView showWinnersOnTV(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		KactivityUserService acUserService=(KactivityUserService) SpringUtil.getBean("kacUserService");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		String uanswer=request.getParameter("uanswer");
		if(uanswer==null){
			//俩人
			String answers[]=request.getParameter("answers").split(",");
			if(answers.length==0){
				out.write("error");
				out.close();
			}else{
				String lanswer=answers[0];
				String ranswer=answers[1];
				KactivityUser luser=acUserService.findAcUserByRightAnswer(lanswer);
				KactivityUser ruser=acUserService.findAcUserByRightAnswer(ranswer);
				WinnerFileUtil.setFileWRable();
				WinnerFileUtil.setOrderEmpty();
				WinnerFileUtil.writeOrder(luser);
				WinnerFileUtil.writeOrder(ruser);
				WinnerFileUtil.setFileOnlyRead();
				out.write("ok");
				out.close();
			}
		}else{
			//单人
			KactivityUser user=acUserService.findAcUserByRightAnswer(uanswer);
			WinnerFileUtil.setFileWRable();
			WinnerFileUtil.setOrderEmpty();
			WinnerFileUtil.writeOrder(user);
			WinnerFileUtil.setFileOnlyRead();
			out.write("ok");
			out.close();
		}
		return null;
	}
	
	//开奖页面每隔几秒会请求这个方法，一旦winner.txt中有值，则显示获奖用户
	public ModelAndView getWinners(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<String> userList=WinnerFileUtil.readOrder();
		String user1=userList.get(0);
		String user2;
		if(userList.size()==1){
			user2="nobody";
		}else{
			user2=userList.get(1);
		}
		if(user1!="nobody"){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.write(user2);
			out.close();
		}
		return null;
	}
	
	//初始化TV显示页面
	public ModelAndView InitiaTVWinner(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		List<String> userList=WinnerFileUtil.readOrder();
		String user1=userList.get(0);
		String user2;
		if(userList.size()==1){
			user2="nobody";
		}else{
			user2=userList.get(1);
		}
		//答案
		String str[]=FileRWUtil.readFromFile();   //str[0]:个位   str[1]:十位  str[2]:百位
		out.write(user1+"#"+user2+"#"+str[0]+"#"+str[1]+"#"+str[2]);
		out.close();
		return null;
	}
	
	//HttpClient工具
	public static String getTVHtmlByHttpClient(String url){
		HttpClient httpClient=new DefaultHttpClient();
		HttpGet httpGet=new HttpGet(url);
		try {
			HttpResponse response=httpClient.execute(httpGet);
			HttpEntity entity=response.getEntity();
			if(200==response.getStatusLine().getStatusCode()){
				String contents=EntityUtils.toString(entity);
				//Set Encoding 
				String contentHtml=new String(contents.getBytes("UTF-8"),"UTF-8");
				int beginIndex=contentHtml.indexOf("<body");
				int endIndex=contentHtml.lastIndexOf("<!--");
				String body=contentHtml.substring(beginIndex, endIndex);
				return body;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
}