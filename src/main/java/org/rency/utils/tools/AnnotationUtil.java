package org.rency.utils.tools;

import java.lang.reflect.Method;
import org.rency.utils.exceptions.CoreException;

/**
 * @desc annotation工具类
 * @author T-rency
 * @date 2015年2月5日 下午12:37:46
 */
public class AnnotationUtil {

	/**
	 * @desc 获取注解实例
	 * @date 2015年2月5日 下午2:06:29
	 * @param annotationClass
	 * @param execMethod
	 * @return
	 * @throws CoreException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> T getAnnotationValue(Class execClass,Method execMethod,Class annotationClass) throws CoreException{
		if(execClass == null && execMethod == null){
			throw new CoreException("annotation class or method can not be null.");
		}
		if(execMethod != null){
			return (T) execMethod.getAnnotation(annotationClass);
		}else{
			return (T) execClass.getAnnotation(annotationClass);
		}
		
	}
}