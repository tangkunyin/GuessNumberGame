package com.vision.game.Servlet;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.vision.game.bean.Customerinfo;
import com.vision.game.bean.KactivityInfo;
import com.vision.game.bean.KactivityUser;
import com.vision.game.service.KactivityInfoService;
import com.vision.game.service.KactivityUserService;
import com.vision.game.utils.GameInfoUtil;
import com.vision.game.utils.SelectCustomerById;
import com.vision.game.utils.SpringUtil;

/**
 * 处理猜数字赢大奖
 * @author tangkunyin
 */
public class GuessNumAction_bak extends MultiActionController {
	
	//提交数据的相等性判断。即：用户不能提交相同的数据
	public void equalJudge(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String input=request.getParameter("input");
		//根据答案去找用户，如果返回值为null，则表示该数字是可以提交的，否则表示已有用户提交过，不能在提交
		KactivityUserService acUserService=(KactivityUserService) SpringUtil.getBean("kacUserService");
		KactivityUser currentUser=acUserService.findAcUserByRightAnswer(input);
		PrintWriter out=response.getWriter();
		if(currentUser==null){
			out.write("ok");
		}else{
			out.write("");
		}
		out.close();
	}
	
	
	//处理提交后
	public ModelAndView submitData(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String input=request.getParameter("input");
		//保存用户信息，并转向填完未开奖页面
		HttpSession session=request.getSession();
		KactivityUser acUser=(KactivityUser) session.getAttribute("acUser");
		if(acUser!=null){
			//查询CustomerInfo，提取信息
			Customerinfo c=SelectCustomerById.getCustById(acUser.getUserId());
			if(c==null){
				request.setAttribute("gameError", "用户读取失败，提交失败");
				return new ModelAndView("error/acerror");
			}else{
				//头像格式：http://www.myktv.com/headpic/mid_50104.jpg。大小：100*100
				acUser.setHeadpicpath("http://www.myktv.com/headpic/mid_"+c.getId()+".jpg");
				//参加时间取当前系统时间
				SimpleDateFormat dateType = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss", Locale.CHINA);
				acUser.setJoinTime(dateType.format(new Date()));
				acUser.setNickname(c.getNickName());
				acUser.setSex(c.getSex());
				acUser.setYourAnswer(input);
				//保存该参与活动的用户
				KactivityUserService acUserService=(KactivityUserService) SpringUtil.getBean("kacUserService");
				int n=0;
				n=acUserService.addAcUser(acUser);
				if(n!=0){ //保存成功，跳转至提交后页面
					session.removeAttribute("acUser"); //为防止重复提交或刷新页面，需要移除session
					List<KactivityUser> allAcUsers=acUserService.selectAllAcUser();
					request.setAttribute("yourAnswer", input);
					request.setAttribute("allAcUsers", allAcUsers);  //取出集合，在页面迭代
					//倒计时相关
					KactivityInfo game=(KactivityInfo) session.getAttribute("game");
					String KJTime=game.getAcCloseTime();
					//设置隐藏域倒计时时间
					request.setAttribute("remainTimes", game.getAcStartDate().replace('-', '/')+" "+KJTime);
					request.setAttribute("KJTime", KJTime);
					return new ModelAndView("WEB-INF/client/guessNumber/WeiKaiJiang");
				}else{
					//提交处理失败
					request.setAttribute("gameError", "提交失败，请联系管理员！");
					return new ModelAndView("error/acerror");
				}
			}
		}else{
			//提交处理失败
			request.setAttribute("gameError", "页面已过期！");
			return new ModelAndView("error/acerror");
		}
	}
	
	public ModelAndView moreGameInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		KactivityInfoService gameInfoService=(KactivityInfoService) SpringUtil.getBean("GameInfoService");
		List<KactivityInfo> games=gameInfoService.selectAll();
		request.setAttribute("games", games);
		return new ModelAndView("WEB-INF/client/guessNumber/beforeGame");
	}
	//查看更多已参与用户信息
	public ModelAndView SeeMoreAcUser(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		KactivityUserService acUserService=(KactivityUserService) SpringUtil.getBean("kacUserService");
		List<KactivityUser> allAcUsers=acUserService.selectAllAcUser();
		request.setAttribute("allAcUsers", allAcUsers);  //取出集合，在页面迭代
		//从session中拿到游戏信息，用户显示剩余时间
		HttpSession session = request.getSession();
		KactivityInfo game=(KactivityInfo) session.getAttribute("game");
		if(game==null){
			request.setAttribute("gameError", "页面已失效，请返回上一页");
			return new ModelAndView("error/acerror");
		}else{
			String KJTime=game.getAcCloseTime();
			if(allAcUsers.size()!=0){
				//设置隐藏域倒计时时间
				request.setAttribute("remainTimes", game.getAcStartDate().replace('-', '/')+" "+KJTime);
				request.setAttribute("KJTime", KJTime);
			}
			return new ModelAndView("WEB-INF/client/guessNumber/WeiKaiJiang");
		}
	}
	//处理开奖时间到了之后的操作
	public ModelAndView showWinner(HttpServletRequest request,
			HttpServletResponse response,KactivityUser currentUser) throws Exception {
		// 使用隐藏表单获取当前用户。再次刷新就是null
		HttpSession session=request.getSession();
		KactivityUserService acUserService=(KactivityUserService) SpringUtil.getBean("kacUserService");
		KactivityInfoService gameInfoService=(KactivityInfoService) SpringUtil.getBean("GameInfoService");
		if(currentUser.getYourAnswer()!=null){
			String answer=currentUser.getYourAnswer();
			//由于用户输入的答案是唯一的，所以可以根据答案去查询当前用户所有信息
			if(answer==""){
				return new ModelAndView("WEB-INF/client/guessNumber/game");
			}
			KactivityUser acUser=acUserService.findAcUserByRightAnswer(answer);
			KactivityInfo gameInfo=gameInfoService.findByGameAcId(acUser.getActivityId());
			session.setAttribute("currentUser", acUser);
			session.setAttribute("currentGameInfo", gameInfo);
		}
		KactivityUser acUser=(KactivityUser) session.getAttribute("currentUser");
		KactivityInfo gameInfo=(KactivityInfo) session.getAttribute("currentGameInfo");
		//如果超过一定时间，session过期，这时用户在刷新页面，就提示页面过期
		if(acUser==null && gameInfo==null){
			request.setAttribute("gameError", "页面已过期！");
			return new ModelAndView("error/acerror");
		}
		
		//根据 isWinner字段是否为y,分别查找用户集合。页面迭代的是用户
		List<KactivityUser> list=new ArrayList<KactivityUser>();
		for(int i=1;;i++){
			List<KactivityUser> users=acUserService.findAcWinerByTH(i);
			if(users.size()==0){
				System.out.println("第"+i+"次开奖进行中，获奖用户还未产生，系统处理中");
				break;
			}else{
				if(users.size()==1){
					System.out.println("第"+i+"次摇奖产生1个中奖用户。恭喜这个幸运儿");
					list.add(users.get(0));
					continue;
				}else if(users.size()==2){
					System.out.println("第"+i+"次摇奖产生2个中奖用户");
					list.add(users.get(0));
					list.add(users.get(1));
					continue;
				}else{
					System.out.println("================ 单次摇奖中奖用户不能超过三个。系统有误，请检查 ================");
					break;
				}	
			}
		}
		
		//判断奖品、答案与用户数量的关系
		if(2*gameInfo.getWinNum()>=list.size()){
			GameInfoUtil gu=new GameInfoUtil();
			List<String> answers=gu.getAnswers(gameInfo);
			List<String> prizes=gu.getPrizes(gameInfo);
			
			request.setAttribute("answers", answers);
			request.setAttribute("prizes", prizes);
			request.setAttribute("winner", list);
			//迭代时，奖品和获奖者从request域中获取，游戏信息从session域中获取 currentGameInfo
			return new ModelAndView("WEB-INF/client/guessNumber/KaiJiang");  //转向开奖页面
		}else{
			//数量对应关系不对
			System.err.println("==============[奖品、答案]与获奖用户的数量关系不对，请核查系统================");
			//转到游戏信息页面
			List<KactivityInfo> games=gameInfoService.selectAll();
			request.setAttribute("games", games);
			return new ModelAndView("WEB-INF/client/guessNumber/beforeGame");
		}
	}
}