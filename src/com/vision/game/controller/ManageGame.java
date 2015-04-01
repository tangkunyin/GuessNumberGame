package com.vision.game.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.view.RedirectView;

import com.vision.game.bean.KactivityInfo;
import com.vision.game.bean.KactivityTemplaite;
import com.vision.game.service.KactivityInfoService;
import com.vision.game.service.KactivityTemplaiteService;
import com.vision.game.utils.CheckDateType;
import com.vision.game.utils.SpringUtil;
import com.vision.game.utils.TempleteUtil;

/**
 * 游戏管理控制 设计游戏增、删、改、查
 * @author tangkunyin
 * @since 2013-06-05
 */
public class ManageGame extends MultiActionController {
	// 查询游戏信息
	public ModelAndView ShowGames(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		KactivityInfoService gameInfoService=(KactivityInfoService) SpringUtil.getBean("GameInfoService");
		int n=gameInfoService.countAll();
		if(n==0){
			//没有数据
			request.setAttribute("BgMessage", "没有游戏活动信息，请先根据模版或自行增加游戏活动！");
			return new ModelAndView("/admin/right");
		}else{
			List<KactivityInfo> games=gameInfoService.selectAll();
			request.setAttribute("totals", n);
			request.setAttribute("games", games);
			
			//分页。初始化迭代参数
			int totalPages=(n+2)/3;
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
			
			return new ModelAndView("admin/listGames");
		}
	}
	//查找模版并显示
	public ModelAndView GetTemp(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		KactivityTemplaiteService KacTemplaiteService = (KactivityTemplaiteService) SpringUtil.getBean("KacTemplaiteService");
		// 总记录数
		int total = KacTemplaiteService.countAll();
		// 模版详情
		List<KactivityTemplaite> KacTemplaites = KacTemplaiteService.selectAll();
		request.setAttribute("totals", total);
		request.setAttribute("KacTemplaites", KacTemplaites);
		
		//分页。初始化迭代参数。每页计划排8个
		int totalPages=(total+7)/8;
		request.setAttribute("totalPages", totalPages);
		String parames=request.getParameter("pagenumber");
		if(""==parames || parames==null){
			int beginIndex=0;
			int endIndex=7;
			int currentPage=1;
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("beginIndex", beginIndex);
			request.setAttribute("endIndex", endIndex);
		}else{
			request.setAttribute("currentPage", Integer.parseInt(parames));
			request.setAttribute("beginIndex", Integer.parseInt(parames)*8-8);
			request.setAttribute("endIndex", Integer.parseInt(parames)*8-1);
		}
		
		return new ModelAndView("admin/listTemp");
	}
	
	/*
	 *  显示具体添加游戏活动的页面，搜集表单数据
	 */
	public ModelAndView showForm(HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		String temId=request.getParameter("id");
		KactivityTemplaiteService KacTemplaiteService = (KactivityTemplaiteService) SpringUtil.getBean("KacTemplaiteService");
		// 通过id查找模版
		KactivityTemplaite template=KacTemplaiteService.findByActivityId(Integer.parseInt(temId));
		request.setAttribute("temp", template);
		
		return new ModelAndView("admin/add1");
	}
	
	//删除
	public ModelAndView DelGames(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String gameId=request.getParameter("id");
		System.out.println("客户端要删除："+gameId+"号活动");
		KactivityInfoService gameInfoService=(KactivityInfoService) SpringUtil.getBean("GameInfoService");
		String picPath=gameInfoService.findByGameAcId(Integer.parseInt(gameId)).getQrcodeImg();
		int n=0;
		n=gameInfoService.deleteGame(Integer.parseInt(gameId));
		if(n!=0){
			//删除二维码图片
			@SuppressWarnings("deprecation")
			String realPath=request.getRealPath(picPath);
			File file=new File(realPath);
			if(file.delete()){
				System.out.println(file+"删除成功！");
			}else{
				System.out.println("文件删除失败！！");
			}
			new TempleteUtil().reduceTempUsedTimes();
			request.setAttribute("BgMessage", gameId+"号活动删除成功！");
		}else{
			request.setAttribute("BgMessage", gameId+"号活动删除失败！");
		}
		return new ModelAndView("admin/right");
	}
	
	//修改显示
	public ModelAndView ShowUpdateGame(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String gameId=request.getParameter("id");
		KactivityInfoService gameInfoService=(KactivityInfoService) SpringUtil.getBean("GameInfoService");
		KactivityInfo gameInfo=gameInfoService.findByGameAcId(Integer.parseInt(gameId));
		request.setAttribute("gameInfo", gameInfo);
		return new ModelAndView("admin/updateGame");
	}
	//显示
	public ModelAndView UpdateGame(HttpServletRequest request,
			HttpServletResponse response,KactivityInfo gameInfo) throws Exception {
		gameInfo.setWeek(CheckDateType.getWeek(gameInfo.getAcStartDate()+","+gameInfo.getAcStartTime()));
		int i=0;
		if(gameInfo.getPrize1()!=""){i++;}
		if(gameInfo.getPrize2()!=""){i++;}
		if(gameInfo.getPrize3()!=""){i++;}
		if(gameInfo.getPrize4()!=""){i++;}
		if(gameInfo.getPrize5()!=""){i++;}
		if(gameInfo.getPrize6()!=""){i++;}
		if(gameInfo.getPrize7()!=""){i++;}
		if(gameInfo.getPrize8()!=""){i++;}
		if(gameInfo.getPrize9()!=""){i++;}
		if(gameInfo.getPrize10()!=""){i++;}
		gameInfo.setWinNum(i);
		KactivityInfoService gameInfoService=(KactivityInfoService) SpringUtil.getBean("GameInfoService");
		gameInfoService.updateGame(gameInfo);
		return new ModelAndView(new RedirectView("ManageGame.ktv?action=ShowGames"));
	}
}
