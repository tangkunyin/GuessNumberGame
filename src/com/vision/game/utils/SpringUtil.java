package com.vision.game.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * SpringUtis
 * 通过此Util可以方便的获取Bean
 * @author tangkunyin
 * @see www.hadooper.org
 */
public class SpringUtil implements ApplicationContextAware{

	/**
	 * 当前IOC
	 */
	private static ApplicationContext applicationContext;

	//设置上下文环境，此方法由spring自动装配
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		applicationContext=arg0;
	}
	/**
	 * 从当前IOC获取bean
	 */
	public static Object getBean(String id){
		Object object=null;
		object=applicationContext.getBean(id);
		return object;
	}
}
