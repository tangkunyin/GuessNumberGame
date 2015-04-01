package com.vision.game.tv.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vision.game.bean.KactivityUser;
import com.vision.game.utils.JDBCUtil;

/**
 * 
 * @author tangkunyin
 *
 */
public class TVSubmit extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TVSubmit tvSubmit=new TVSubmit();
		List<KactivityUser> users=tvSubmit.getAllSubmitedUsers();
		request.setAttribute("user", users);
		request.setAttribute("acEndTime", request.getAttribute("acEndTime"));
		request.getRequestDispatcher("/WEB-INF/client/tv/tv-submit.jsp").forward(request, response);
	}
	//get All submited users
	public List<KactivityUser> getAllSubmitedUsers(){
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="SELECT * FROM kactivityuser ORDER BY JoinTime DESC LIMIT 0,8";
		try {
			ps=conn.prepareStatement(sql);
			rs= ps.executeQuery();
			List<KactivityUser> list=new ArrayList<KactivityUser>();
			while(rs.next()){
				KactivityUser user=new KactivityUser();
				user.setHeadpicpath(rs.getString("headpicpath"));
				user.setNickname(rs.getString("nickname"));
				user.setYourAnswer(rs.getString("yourAnswer"));
				list.add(user);  //每次都添加
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.closeDBResource(rs, ps, conn);
		}
		return null;
	}
}
