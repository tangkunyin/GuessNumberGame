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

import com.vision.game.bean.SingkBroadcastInfo;
import com.vision.game.utils.JDBCUtil;

/**
 * 根据游戏时间，显示广播信息。由于页面设有5秒刷新请求一次首页，因此无须设定时器。
 * @author tangkunyin
 * @since 2013-07-15
 */
public class TVIndexServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TVIndexServlet index=new TVIndexServlet();
		List<SingkBroadcastInfo> BroadcastInfo=index.getBroadcastInfo();
		request.setAttribute("BroadcastInfo", BroadcastInfo);
		request.setAttribute("acStartTime", request.getAttribute("acStartTime"));
		//查询后转发
		request.getRequestDispatcher("/WEB-INF/client/tv/brodcast.jsp").forward(request, response);
	}
	//使用JDBC做查询，并在页面显示
	public List<SingkBroadcastInfo> getBroadcastInfo(){
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		//按发送时间，查询前20条记录。保证时时更新中
		String sql="SELECT id,userId,nickName,fileUrl,textContent,type FROM  singk_broadcast_info ORDER BY sendTime DESC LIMIT 0,4";
		try {
			ps=conn.prepareStatement(sql);
			rs= ps.executeQuery();
			List<SingkBroadcastInfo> BroadcastInfo=new ArrayList<SingkBroadcastInfo>();
			while(rs.next()){
				SingkBroadcastInfo broadcastInfo=new SingkBroadcastInfo();
				broadcastInfo.setId(Integer.parseInt(rs.getString("id")));
				//空值检验
				if(rs.getString("userId")==null){
					broadcastInfo.setUserHeadPic("http://www.myktv.com/headpic/system/none.jpg");
				}else{
					broadcastInfo.setUserHeadPic("http://www.myktv.com/headpic/mid_"+Integer.parseInt(rs.getString("userId"))+".jpg");
				}
				
				if(rs.getString("nickName")==null){
					broadcastInfo.setNickName("无名氏");
				}else{
					broadcastInfo.setNickName(rs.getString("nickName"));
				}
				
				broadcastInfo.setFileUrl(rs.getString("fileUrl"));

				if(rs.getString("textContent")==null){
					broadcastInfo.setTextContent("这位用户表示自己无语");
				}else{
					broadcastInfo.setTextContent(rs.getString("textContent"));
				}
				if(rs.getString("type")==null){
					broadcastInfo.setType(0);
				}else{
					broadcastInfo.setType(Integer.parseInt(rs.getString("type")));
				}

				BroadcastInfo.add(broadcastInfo);  //每次都添加
			}
			return BroadcastInfo;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.closeDBResource(rs, ps, conn);
		}
		return null;
	}
}
