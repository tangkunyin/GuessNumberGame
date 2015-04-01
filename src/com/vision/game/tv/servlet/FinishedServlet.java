package com.vision.game.tv.servlet;

/**
 * 1、活动结束时，现在本次活动中奖情况
 * 2、将本次未中奖的用户字段更新为:n
 */
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FinishedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//查询信息...
		
		
		System.out.println("获取所有获奖信息，幻灯片式滚动中....");
		
		
		
		
		request.getRequestDispatcher("/WEB-INF/client/tv/finished.jsp").forward(request, response);
	}
}
