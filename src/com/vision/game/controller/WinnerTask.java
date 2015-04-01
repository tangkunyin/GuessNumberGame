package com.vision.game.controller;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.vision.game.service.KactivityUserService;
import com.vision.game.utils.SpringUtil;

/**
 * 参与用户定时器---实现功能：
 * 当活动结束时，更新未中奖的用户字段为:isWinner=n
 * 当活动通过模版一生成时，该定时器启动。
 * @author tangkunyin
 */
public class WinnerTask extends TimerTask {
	KactivityUserService acUserService=(KactivityUserService) SpringUtil.getBean("kacUserService");
	private Timer winnerTimer;
	
	public void updateWinnerState(Date time){
		winnerTimer=new Timer();
		winnerTimer.schedule(new WinnerTask(), time);
	}
	
	@Override
	public void run() {
		try {
			int n=0;
			n=acUserService.updateFailureAcUser();
			if(n!=0){
				System.out.println("未中奖用户信息已更新");
				winnerTimer.cancel();
			}
		} catch (Exception e) {
			System.out.println("该活动时间可能设置有误，因为活动貌似没人参加，定时器找不到用户，更新失败！！");
			e.printStackTrace();
		}
	}
}
