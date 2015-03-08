package org.rency.utils.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextHolder implements ApplicationContextAware{

	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext ctx)throws BeansException {
		applicationContext = ctx;
	}
	
	public static <T> T getBean(Class<T> requiredType){
		return applicationContext.getBean(requiredType);
	}

}
