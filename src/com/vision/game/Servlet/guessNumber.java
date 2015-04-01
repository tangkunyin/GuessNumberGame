package com.vision.game.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vision.game.bean.KactivityInfo;
import com.vision.game.bean.KactivityUser;
import com.vision.game.service.KactivityInfoService;
import com.vision.game.service.KactivityUserService;
import com.vision.game.utils.SpringUtil;
/**
 * 猜数字游戏页面转向控制
 * @author tangkunyin
 */
public class guessNumber extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		guessNumber guessNum=new guessNumber();
		String ktvId=request.getParameter("ktvId");
		String userId=request.getParameter("userId");
		String acId=request.getParameter("acId");
		boolean flag=guessNum.checkIsNull(ktvId,userId,acId);
		if(flag){
			KactivityInfo game=guessNum.checkGameAvalid(acId);
			//判断当前时间月活动设定时间的关系。如果当前时间已经过了活动的最后时间，则表示活动已经失效
			boolean isOutDate=guessNum.checkGameOutDate(game.getAcStartDate()+","+game.getAcCloseTime());
			if(isOutDate){
				try {
					short isValid=game.getAcValid();
					HttpSession session=request.getSession();
					if(isValid==1){ //活动有效
						//根据用户id查询用户参加表。如果根据userId查得0，则表示活动有效，但是没参加。此时，保存用户信息到KactivityUser表，并显示游戏页面
						KactivityUser kacUser=guessNum.selectKacUserById(userId);
						if(kacUser==null){ //活动存在，用户没有参加
							KactivityUser acUser=new KactivityUser();
							acUser.setActivityId(Integer.parseInt(acId));
							acUser.setKtvId(Integer.parseInt(ktvId));
							acUser.setUserId(Integer.parseInt(userId));
							session.setAttribute("acUser", acUser);
							session.setAttribute("game", game);
							request.getRequestDispatcher("/WEB-INF/client/guessNumber/game.jsp").forward(request, response);
						}else{
							//用户参加过活动，判断开奖与否。根据isWinner判断。null则表示未开奖，非null怎表示开过奖
							if(kacUser.getIsWinner()==null){
								//未开奖时，列举已提交数据
								KactivityUserService acUserService=(KactivityUserService) SpringUtil.getBean("kacUserService");
								List<KactivityUser> allAcUsers=acUserService.selectAllAcUser();
								request.setAttribute("KJTime", game.getAcCloseTime());   //设置开奖时间
								
								//设置开奖后跳转的链接。现由隐藏表单提交控制页面转向
								//request.setAttribute("forwardUrl", request.getLocalAddr()+":"+request.getLocalPort()+request.getContextPath()+"/GuessNum.ktv?action=showWinner");
								
								//设置隐藏域倒计时时间
								request.setAttribute("remainTimes", game.getAcStartDate().replace('-', '/')+" "+game.getAcCloseTime());
	 							request.setAttribute("yourAnswer", kacUser.getYourAnswer());
								request.setAttribute("allAcUsers", allAcUsers);  //取出集合，在页面迭代
								request.getRequestDispatcher("/WEB-INF/client/guessNumber/WeiKaiJiang.jsp").forward(request, response);
							}else{
								//已开奖，列举开奖完成的数据
								session.setAttribute("currentUser", kacUser);
								session.setAttribute("currentGameInfo", game);
								PrintWriter out=response.getWriter();
								String path="GuessNum.ktv?action=showWinner";
							    out.println("<html>");  
							    out.println("<script>");  
							    out.println("window.open ('"+path+"','_top')");   //使用js跳出外层iframe
							    out.println("</script>");  
							    out.println("</html>");  
							    out.close();
							}
						}
					}else{
						//没开始，查询活动数目，显示更多活动
						List<KactivityInfo> games=guessNum.selectAllGames();
						request.setAttribute("games", games);
						request.getRequestDispatcher("/WEB-INF/client/guessNumber/beforeGame.jsp").forward(request, response);
					}
				} catch (NullPointerException e) {
					e.printStackTrace();
					response.sendRedirect(request.getContextPath()+"/error/error.jsp");
				}
			}else{
				request.getRequestDispatcher("/WEB-INF/client/outdate.jsp").forward(request, response);  //活动超时
			}
		}else{
			response.sendRedirect(request.getContextPath()+"/error/error.jsp");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
	/**
	 * 根据活动id查询单个活动信息
	 * @param acId
	 * @return
	 */
	private KactivityInfo checkGameAvalid(String acId){
		KactivityInfoService gameInfoService=(KactivityInfoService) SpringUtil.getBean("GameInfoService");
		KactivityInfo gameInfo=gameInfoService.findByGameAcId(Integer.parseInt(acId));
		if(gameInfo!=null){
			return gameInfo;
		}else{
			return null;
		}
	}
	/**
	 * 查询所有活动详情
	 * @return list
	 */
	private List<KactivityInfo> selectAllGames(){
		KactivityInfoService gameInfoService=(KactivityInfoService) SpringUtil.getBean("GameInfoService");
		List<KactivityInfo> games=gameInfoService.selectAll();
		if(games.size()!=0){
			return games;
		}else{
			return null;
		}
	}
	/**
	 * @param userId
	 * @return
	 */
	private KactivityUser selectKacUserById(String userId){
		KactivityUserService acUserService=(KactivityUserService) SpringUtil.getBean("kacUserService");
		KactivityUser kacUser=acUserService.findAcUserById(Integer.parseInt(userId));
		if(kacUser!=null){
			return kacUser;
		}else{
			return null;
		}
	}
	
	
	/**
	 * 验证传参是否是空值
	 * @param arg
	 * @return
	 */
	private boolean checkIsNull(String...arg){
		if(arg[0]==null || arg[0]==""){
			return false;
		}else if(arg[1]==null || arg[1]==""){
			return false;
		}else if(arg[2]==null || arg[2]==""){
			return false;
		}else{
			return true;
		}
	}
	
	private boolean checkGameOutDate(String acCloseTime){
		try {
			SimpleDateFormat dateType = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss", Locale.CHINA);
			//现在时间
			Date now1=new Date();
			long now2=now1.getTime();
			
			Date nowTime=new Date(now2+1000*60*60); //现在时刻后延1小时
			Date acTime=dateType.parse(acCloseTime);
			return acTime.after(nowTime);
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
	}
}
