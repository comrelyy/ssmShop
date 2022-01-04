package com.relyy.shop.backend.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Description 容器管理操作公共类
 * @Created by Reeve Cai
 * @Date 2022/1/4
 */
@Slf4j
@Component
public class ApplicationContextRegister implements ApplicationContextAware {

	private static ApplicationContext APPLICATION_CONTEXT;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		APPLICATION_CONTEXT = applicationContext;
	}

	public static ApplicationContext getApplicationContext(){
		return APPLICATION_CONTEXT;
	}

	/**
	 * 根据类型从容器中获取对象
	 * @param type
	 * @param <T>
	 * @return
	 */
	public static <T> T getBean(Class<T> type){
		return APPLICATION_CONTEXT.getBean(type);
	}
}
