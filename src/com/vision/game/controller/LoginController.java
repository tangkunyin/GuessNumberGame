package com.vision.game.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.vision.game.bean.Address;
import com.vision.game.bean.AdminUser;
import com.vision.game.service.AdminUserService;
import com.vision.game.utils.AdminUserUtil;
import com.vision.game.utils.FileRWUtil;
import com.vision.game.utils.GameOrderUtil;
import com.vision.game.utils.KAIdUtil;
import com.vision.game.utils.SpringUtil;
import com.vision.game.utils.WinnerFileUtil;

/**
 * 登录控制器，处理：登录、登录检查、退出
 * @author tangkunyin
 * @see www.hadooper.org
 * @since 2013-06-6
 */
public class LoginController extends MultiActionController implements Filter{
	//登录过滤控制
	public void init(FilterConfig config) throws ServletException {
		//设置获奖者名单文件可写
		FileRWUtil.setFileWRable("currentUsers.txt");
		WinnerFileUtil.setFileWRable();
		
		FileRWUtil.setNumFileEmpty();
		FileRWUtil.setWinnerOrderEmpty();
		WinnerFileUtil.setOrderEmpty();
		
		GameOrderUtil.setGameOrderDefault();
		KAIdUtil.setKtvIdEmpty();
		
		//将所有登录字段为yes的改为noo
		if(AdminUserUtil.setAllAULoginStateOff()){
			System.out.println("用户登录状态已全部改变...");
		}
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) resp;
		HttpSession session = request.getSession();
		AdminUser auser=(AdminUser) session.getAttribute("currentUser");
		if(auser==null){
			java.io.PrintWriter out = response.getWriter(); 
			String path=request.getContextPath();
		    out.println("<html>");  
		    out.println("<script>");  
		    out.println("window.open ('"+path+"','_top')");   //使用js跳出外层iframe
		    out.println("</script>");  
		    out.println("</html>");  
		    out.close();
		}else{
			if(AdminUserUtil.getAdminUserLoginState(auser.getId()).equalsIgnoreCase("yes")){
				chain.doFilter(request, response); //放行
			}else{
				java.io.PrintWriter out = response.getWriter(); 
				String path=request.getContextPath();
			    out.println("<html>");  
			    out.println("<script>");  
			    out.println("window.open ('"+path+"','_top')");   //使用js跳出外层iframe
			    out.println("</script>");  
			    out.println("</html>");  
			    out.close();
			}
		}
	}
	public void destroy() {}
	
	//处理登录
	public ModelAndView AdminLogin(HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession();
		String name=request.getParameter("userName");
		String pasd=request.getParameter("repassword");
		String role=request.getParameter("role");
		//找Service做查询。
		AdminUserService aUserService=(AdminUserService) SpringUtil.getBean("aduserService");
		AdminUser user=aUserService.findByUserLogin(name, pasd);
		if(user!=null){
			if(role.equalsIgnoreCase(user.getRole()+"")){
				if(AdminUserUtil.getAdminUserLoginState(user.getId()).equalsIgnoreCase("yes")){
					request.setAttribute("loginMsg", "您的帐号已在别处登录");
					return new ModelAndView("error/loerror");
				}else{
					AdminUserUtil.setAULoginStateOn(user.getId());  //更改用户上线状态
					AdminUserUtil.setAUexpire(user.getId());  //设置定时器，当用户非正常退出时，自动更新数据库信息
					//正确登录。在根据角色显示不同的页面

//					String ip=request.getRemoteAddr();
//					if(!"127.0.0.1".equalsIgnoreCase(ip)){
//						Address addr=new GetAddrFromIp().getAddress(request.getRemoteAddr());
//						session.setAttribute("addr", addr);
//					}

					//保存当前用户
					session.setAttribute("currentUser", user);
					if(user.getRole()==1){  //系统管理员
						session.setAttribute("BgMessage", "欢迎进入后台！");
						response.sendRedirect("admin/main.jsp");
						return null;
					}else if(user.getRole()==2){  //主管编辑
						request.setAttribute("errorMsg","该角色尚未开启！");
						return new ModelAndView("admin");
					}else if(user.getRole()==3){  //摇奖录入
						session.setAttribute("ktvName", AdminUserUtil.getKtvNameById(user.getKtvId()));
						response.sendRedirect("admin/KJInput-gw.jsp");
						return null;
					}else{
						request.setAttribute("errorMsg","该角色尚未开启！");
						return new ModelAndView("admin");
					}
				}
			}else{
				//角色不对
				request.setAttribute("errorMsg","角色选择错误，请重新选择！");
				return new ModelAndView("admin");
			}
		}else{
		//登录信息错误
		request.setAttribute("errorMsg","登录信息错误，请检查！");
		return new ModelAndView("admin");
		}
	}

	//处理退出
	public ModelAndView AdminLoginOut(HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession();
		AdminUser adminUser=(AdminUser) session.getAttribute("currentUser");
		if(adminUser!=null){
			if(AdminUserUtil.setAULoginStateOff(adminUser.getId())){
				session.removeAttribute("currentUser");
				Address addr=(Address) session.getAttribute("addr");
				GameOrderUtil.setGameOrderDefault();
				if(addr!=null){
					session.removeAttribute("addr");
				}
				response.sendRedirect(request.getContextPath());
			}
		}
		request.setAttribute("errorMsg","登录状态已过期，请重新登录！");
		return new ModelAndView("admin");
	}
	
	//处理助理退出，只控制session移除，摇奖之后就不能再次登录，知道活动截至
	public ModelAndView GameOver(HttpServletRequest request,HttpServletResponse response) throws Exception{
		GameOrderUtil.setGameOrderDefault();
		HttpSession session=request.getSession();
		session.removeAttribute("currentUser");
		request.setAttribute("errorMsg","开奖结束，感谢使用！");
		return new ModelAndView("admin");
	}
}