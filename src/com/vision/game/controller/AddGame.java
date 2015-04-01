package com.vision.game.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.vision.game.bean.KactivityInfo;
import com.vision.game.service.KactivityInfoService;
import com.vision.game.utils.CheckDateType;
import com.vision.game.utils.PinYinUitl;
import com.vision.game.utils.QRCodeUtil;
import com.vision.game.utils.SelectIdAndNameFromSHI;
import com.vision.game.utils.SpringUtil;
import com.vision.game.utils.TempleteUtil;

/**
 * 根据方法添加活动
 * 活动创建成功后，就埋下定时器：com.vision.game.controller.WinnerTask
 * @author tangkunyin
 */
public class AddGame extends MultiActionController {
	public ModelAndView adminUserShow(HttpServletRequest request,HttpServletResponse response) throws Exception{
		System.out.println("=========管理员添加活动通过二维码预览==========");
		return new ModelAndView("WEB-INF/client/guessNumber/gameDemo");
	}
	/**
	 * 搜集猜数字赢大奖的forms
	 * 将表单数据保存到session中，如果用户点击取消，则在增加活动中直接删除session中的值
	 * @throws Exception
	 */
	public ModelAndView addGuessNumberForm(HttpServletRequest request,HttpServletResponse response, KactivityInfo gameInfo)
			throws Exception {
		//设置编码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//根据ktvName获取ktvId
		gameInfo.setKtvId(SelectIdAndNameFromSHI.getSongHallId(gameInfo.getKtvName()));
		HttpSession session=request.getSession();
		//将二维码地址和ktvId信息保存在session中，此时不要往数据库里插入信息
		//生成二维码
		@SuppressWarnings("deprecation")
		String realPath=request.getRealPath("/resources/qrcode/");
		File file=new File(realPath+"\\"+PinYinUitl.getStringPinYin(gameInfo.getAcName())+".png");
		String text="http://"+request.getLocalAddr()+":"+request.getLocalPort()+request.getContextPath()+"/addGame.ktv?method=adminUserShow";
		String filepath=QRCodeUtil.getQRCodeImg(text, file);
		File qrcode=new File(filepath);
		String qrpath;
		if(qrcode.exists()){
			qrpath="resources/qrcode/"+PinYinUitl.getStringPinYin(gameInfo.getAcName())+".png";
		}else{
			qrpath=filepath;
		}
		gameInfo.setQrcodeImg(qrpath);
		gameInfo.setWinNum(Integer.parseInt(request.getParameter("prs")));
		session.setAttribute("gameInfo", gameInfo);
		return new ModelAndView("admin/add2");
	}
	/**
	 * 增加游戏
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public ModelAndView addGuessNumber(HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException{
		HttpSession session=request.getSession();
		KactivityInfo gameInfo=(KactivityInfo) session.getAttribute("gameInfo");
		if(gameInfo==null){
			request.setAttribute("BgMessage", "操作失败！");
			return new ModelAndView("admin/right");
		}else{
			//保存活动，先转换时间为星期
			gameInfo.setWeek(CheckDateType.getWeek(gameInfo.getAcStartDate()+","+gameInfo.getAcStartTime()));
			KactivityInfoService gameInfoService=(KactivityInfoService) SpringUtil.getBean("GameInfoService");
			int n=0;
			n=gameInfoService.addGame(gameInfo);
			if(n!=0){
				//保存二维码地址
				int x=0;
				x=gameInfoService.updateQRCodeAddr(gameInfo.getQrcodeImg(),gameInfo.getAcName());
				if(x!=0){
					System.out.println("二维码保存成功。。。");
				}
				
				//埋下定时器
				WinnerTask wt=new WinnerTask();
				wt.updateWinnerState(CheckDateType.getGameoverTime(gameInfo.getAcStartDate()+","+gameInfo.getAcCloseTime()));
				
				//更新模版使用次数
				new TempleteUtil().addTempUsedTimes();
				session.removeAttribute("gameInfo");
				request.setAttribute("BgMessage", "恭喜您，活动设置成功！");
				return new ModelAndView("admin/right");
			}else{
				request.setAttribute("BgMessage", "添加游戏活动失败，请检查所填信息是否正确！");
				return new ModelAndView("admin/right");
			}
		}
	}
	/*
	 * 取消游戏增加。直接删掉session即可
	 */
	public ModelAndView cancelAddGuessNumber(HttpServletRequest request,HttpServletResponse response) throws IOException{
		HttpSession session=request.getSession();
		KactivityInfo gameInfo=(KactivityInfo) session.getAttribute("gameInfo");
		if(gameInfo!=null){
			//删除二维码图片
			@SuppressWarnings("deprecation")
			String realPath=request.getRealPath(gameInfo.getQrcodeImg());
			File file=new File(realPath);
			if(file.delete()){
				System.out.println(file+"删除成功！");
			}else{
				System.out.println("文件删除失败！！");
			}
			session.removeAttribute("gameInfo");
			return new ModelAndView("admin/right");
		}else{
			request.setAttribute("BgMessage", "非法操作！");
			return new ModelAndView("admin/right");		
		}
	}
}