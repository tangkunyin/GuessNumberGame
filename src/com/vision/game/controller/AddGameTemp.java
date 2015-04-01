package com.vision.game.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.vision.game.bean.KactivityTemplaite;
import com.vision.game.service.KactivityTemplaiteService;
import com.vision.game.utils.SpringUtil;

/**
 * 添加游戏模版
 * @author tangkunyin
 * @since 2013-05-30
 */
@SuppressWarnings("deprecation")
public class AddGameTemp extends SimpleFormController{

	public AddGameTemp(){
		this.setCommandClass(com.vision.game.bean.KactivityTemplaite.class);
		this.setCommandName("kactivityTemplaite");
	}
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object arg2, BindException arg3)
			throws Exception {
		KactivityTemplaite kaemplaite=(KactivityTemplaite) arg2;
		HttpSession session=request.getSession();
		//先判断图片session是否有数据
		String path=(String) session.getAttribute("imgsrc");
		if(path==null || path==""){
			request.setAttribute("Templete", kaemplaite);
			request.setAttribute("errorMsg", "请选择图片并上传！");
			return new ModelAndView("/admin/addTemp");
		}
		kaemplaite.setAcPicAddress(path);
		KactivityTemplaiteService KacTemplaiteService=(KactivityTemplaiteService) SpringUtil.getBean("KacTemplaiteService");
		int i=0;
		i=KacTemplaiteService.addKaTemplaite(kaemplaite);
		if(i!=0){
			//移除图片session，释放空间
			session.removeAttribute("imgsrc");
			//成功了  BgMessage
			request.setAttribute("BgMessage", "添加模版成功");
			return new ModelAndView("admin/right");
		}
		return null;
	}
}