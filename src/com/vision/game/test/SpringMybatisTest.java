package com.vision.game.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vision.game.service.KactivityUserService;

/**
 * 整合测试
 * @author tangkunyin
 *
 */
public class SpringMybatisTest {
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("applicationContext.xml");
		KactivityUserService acUserService=(KactivityUserService) app.getBean("kacUserService");
//		KactivityInfoService gameInfoService=(KactivityInfoService) app.getBean("GameInfoService");
//		int n=gameInfoService.updateQRCodeAddr("/resources/qrcode/Mybatismohuchaxunyi.png", "猜数字赢大奖");
		
		int n=acUserService.updateFailureAcUser();
		System.out.println(n);
	}
}
