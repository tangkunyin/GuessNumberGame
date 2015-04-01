package com.vision.game.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vision.game.bean.KactivityInfo;
import com.vision.game.service.KactivityInfoService;
import com.vision.game.utils.SpringUtil;

public class MobileIndex extends HttpServlet {

	private static final long serialVersionUID = 1L;

	//根据ktvid查询当前k场有多少有效的活动，并显示在手机上。并将userID和ktvID用隐藏域的形式传递到页面，当用户选择某一个活动时，在返回到活动Servlet
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MobileIndex mindex=new MobileIndex();
		String ktvId=request.getParameter("ktvid");
		String userId=request.getParameter("userid");
		
		boolean b=mindex.checkParameters(ktvId,userId);
		if(b){
			//查询活动信息，并返回活动列表页
			KactivityInfoService gameInfoService=(KactivityInfoService) SpringUtil.getBean("GameInfoService");
			List<KactivityInfo> gameInfo=gameInfoService.findGameByKtvId(Integer.parseInt(ktvId));
			if(gameInfo.size()==0){
				request.setAttribute("games", null);
			}else if(gameInfo.size()==1){
				//检查活动过期与否
				if(gameInfo.get(0).getAcValid()==0){
					request.getRequestDispatcher("/WEB-INF/client/outdate.jsp").forward(request, response);
				}else{
					response.sendRedirect("guessNumber?ktvId="+ktvId+"&userId="+userId+"&acId="+gameInfo.get(0).getActivityId());
				}
			}else{
				request.setAttribute("games", gameInfo);
				request.setAttribute("ktvId", ktvId);
				request.setAttribute("userId", userId);
				request.getRequestDispatcher("/WEB-INF/client/beforeStart.jsp").forward(request, response);
			}
			
		}else{
			System.out.println("参数为空喔...");
			response.sendRedirect(request.getContextPath()+"/error/error.jsp");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	private boolean checkParameters(String ktvId,String userId){
		if(ktvId==null || ktvId==""){
			return false;
		}else if(userId==null || userId==""){
			return false;
		}else{
			return true;
		}
	}
}
