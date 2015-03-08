package org.rency.utils.tools;

import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.rency.utils.exceptions.CoreException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {
	
	private static final Logger logger = LoggerFactory.getLogger(Utils.class);
	
	/**
	 * @desc 获取当前时间(格式：yyyy-MM-dd HH:mm:dd:SSS)
	 * @date 2014年11月26日 上午9:46:52
	 * @return
	 */
	public static String getNowDateTime(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:dd:SSS").format(new Date());
	}
	
	/**
	 * @desc 获取当前时间(格式：yyyyMMdd)
	 * @date 2014年11月26日 上午9:47:09
	 * @return
	 */
	public static String getNowDate(){
		return new SimpleDateFormat("yyyyMMdd").format(new Date());
	}
	
	/**
	 * @desc 生成主键
	 * @date 2014年11月11日 下午3:52:44
	 * @param length 主键长度(建议长度25位)
	 * @return
	 * @throws CoreException
	 */
	public synchronized static String generateId(int length){
		StringBuilder builder = new StringBuilder();
		try{
			String dateTime = new SimpleDateFormat("yyyyMMddHHmmddSSS").format(new Date());//17
			String pid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
			builder.append(pid);
			builder.append(dateTime);
			if(builder.length() > length){
				builder.append(builder.subSequence(0, length));
			}else{
				int offset = length - builder.length();
				int ran = generateInteger(offset);
				builder.append(ran);
			}
		}catch(CoreException e){
			logger.error("生成主键异常."+e);
			e.printStackTrace();
		}
		
		return builder.toString();
	}
	
	public static String parseDateStr(String paramDate) throws CoreException{
		try {
			SimpleDateFormat sf = new SimpleDateFormat("EEE MMM dd yyyy hh:mm:ss z", Locale.ENGLISH);
		    Date date = sf.parse(paramDate);
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		    return sdf.format(date);
		} catch (ParseException e) {
			logger.error("parseDate error.date is "+paramDate);
			e.printStackTrace();
			return getNowDateTime();
		}
	}
	
	public static String parseLastModified(String lastModified) throws CoreException{
		try {
			if(StringUtils.isBlank(lastModified)){
				logger.debug("parseDate error, and lastModified is null. Return now date time.");
				return getNowDateTime();
			}
		    SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.US);  
		    Date date = sdf.parse(lastModified);
		    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(date);
		} catch (ParseException e) {
			logger.error("parseDate error.date is "+lastModified);
			e.printStackTrace();
			return getNowDateTime();
		}
	}
	
	/**
	 * @desc 生成随机数
	 * @date 2014年8月18日 上午10:21:17
	 * @param 随机数长度
	 * @return
	 * @throws CoreException
	 */
	public synchronized static int generateInteger(int length) throws CoreException{
		StringBuilder sbBuilder = new StringBuilder();
		for(int i=0;i<length;i++){
			int ran = (int)(Math.random()*10);
			if(ran == 0){
				i--;
				continue;
			}
			sbBuilder.append(ran);
		}
		return Integer.parseInt(sbBuilder.toString());
	} 
	
	/**
	 * @desc 生成随机字符串
	 * @date 2014年8月18日 上午10:20:07
	 * @param 生成字符串长度
	 * @return
	 * @throws CoreException
	 */
	public static String generateString(int length) throws CoreException{
		int uuidLength = 32;
		int count = 0;
		if(length >=uuidLength){
			count = (length % uuidLength) == 0 ? 1: (length / uuidLength) +1;
		}else{
			count = (uuidLength % length) == 0 ? 1: (uuidLength / length) +1;
		}
		StringBuilder sbBuilder = new StringBuilder();
		for(int i=0;i<count;i++){
			sbBuilder.append(UUID.randomUUID().toString().replaceAll("-", ""));
		}
		String result = sbBuilder.substring(0,length).toUpperCase();
		logger.info("generate character["+result+"] with length "+length);
		return result;
	} 
	
	/**
	 * @desc 根据类、方法名获取方法实体
	 * @date 2015年2月5日 下午12:31:40
	 * @param actionClass
	 * @param methodName
	 * @return
	 * @throws CoreException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Method getActionMethod(Class actionClass, String methodName)throws CoreException {
		Method method;
		try {
			method = actionClass.getMethod(methodName, new Class[0]);
		} catch (NoSuchMethodException e) {
			logger.error("获取方法失败.",e);
			e.printStackTrace();
			throw new CoreException(e);
		}
		return method;
	}
	
	public static void main(String[] args) throws CoreException {
		System.out.println(Utils.generateId(23));
	}
	
}