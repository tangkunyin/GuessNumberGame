package com.vision.game.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.view.RedirectView;

import com.vision.game.bean.KactivityTemplaite;
import com.vision.game.service.KactivityTemplaiteService;
import com.vision.game.utils.SpringUtil;

/**
 * 模版管理
 * 
 * @author tangkunyin
 * @since 2013-05-30
 */
public class ManageTemp extends MultiActionController {

	public ModelAndView ShowPage(HttpServletRequest request,
			HttpServletResponse arg1) throws Exception {
		// 查询并在页面遍历
		KactivityTemplaiteService KacTemplaiteService = (KactivityTemplaiteService) SpringUtil.getBean("KacTemplaiteService");
		// 总记录数
		int total = KacTemplaiteService.countAll();
		// 模版详情
		List<KactivityTemplaite> KacTemplaites = KacTemplaiteService.selectAll();
		request.setAttribute("totals", total);
		request.setAttribute("KacTemplaites", KacTemplaites);
		
		//分页。初始化迭代参数
		int totalPages=(total+2)/3;
		request.setAttribute("totalPages", totalPages);
		String parames=request.getParameter("pagenumber");
		if(""==parames || parames==null){
			int beginIndex=0;
			int endIndex=2;
			int currentPage=1;
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("beginIndex", beginIndex);
			request.setAttribute("endIndex", endIndex);
		}else{
			request.setAttribute("currentPage", Integer.parseInt(parames));
			request.setAttribute("beginIndex", Integer.parseInt(parames)*3-3);
			request.setAttribute("endIndex", Integer.parseInt(parames)*3-1);
		}

		return new ModelAndView("admin/ManageTemp");
	}


	// 修改页面显示
	public ModelAndView showUpdateTemp(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id=request.getParameter("id");
		// 根据模版id查得具体模版，显示在网页
		KactivityTemplaiteService KacTemplaiteService = (KactivityTemplaiteService) SpringUtil.getBean("KacTemplaiteService");
		KactivityTemplaite temp=KacTemplaiteService.findByActivityId(Integer.parseInt(id));
		request.setAttribute("temple", temp);
		return new ModelAndView("admin/updateTemp");  
	}
	
	//修改操作
	public ModelAndView UpdateTemp(HttpServletRequest request,
			HttpServletResponse response,KactivityTemplaite temp) throws Exception {
		// 根据模版id查得具体模版，显示在网页
		KactivityTemplaiteService KacTemplaiteService = (KactivityTemplaiteService) SpringUtil.getBean("KacTemplaiteService");
		KacTemplaiteService.updateKaTemplaite(temp);
		//重定向
		return new ModelAndView(new RedirectView("ManageTemp.ktv?action=ShowPage"));
	}
	
	// 删除模版
	public ModelAndView deleteTemp(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 根据模版ID删除
		String id=request.getParameter("id");
		System.out.println("客户端要删除："+id+"号模版");
		KactivityTemplaiteService KacTemplaiteService = (KactivityTemplaiteService) SpringUtil.getBean("KacTemplaiteService");
		//查找图片地址
		String picPath=KacTemplaiteService.findByActivityId(Integer.parseInt(id)).getAcPicAddress();
		int n=0;
		n=KacTemplaiteService.deleteKaTemplaite(Integer.parseInt(id));
		if(n!=0){
			//在删除图片
			@SuppressWarnings("deprecation")
			String realPath=request.getRealPath(picPath);
			File file=new File(realPath);
			if(file.delete()){
				System.out.println(file+"删除成功！");
			}else{
				System.out.println("文件删除失败！！");
			}
			request.setAttribute("BgMessage", id+"号模版删除成功！");
		}else{
			request.setAttribute("BgMessage", id+"号模版删除失败！");
		}
		return new ModelAndView("admin/right");
	}
}
