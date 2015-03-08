package org.rency.utils.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.rency.utils.exceptions.CoreException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropUtils {

	private static final Logger logger = LoggerFactory.getLogger(PropUtils.class);
	private static Properties props = new Properties();
	private static Object lock = new Object();

	static{
		synchronized (lock) {
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("init.properties");
			if(is == null){
				logger.error("Properties File[setting.properties] load error.");
			}else{
				try {
					props.load(is);
				} catch (IOException e) {
					logger.error("load properties file error.",e);
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * @desc 单独加载配置文件
	 * @date 2014年12月3日 上午10:14:28
	 * @param fileName 文件名称
	 * @param propName
	 * @return
	 * @throws CoreException
	 */
	public static Object load(String fileName,String propName) throws CoreException{
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
		if(is == null){
			throw new CoreException("Properties File["+fileName+"] load error.");
		}else{
			try {
				props.load(is);
				return props.get(propName);
			} catch (IOException e) {
				logger.error("load properties file["+fileName+"] error.",e);
				e.printStackTrace();
				throw new CoreException("load properties file["+fileName+"] error."+e);
			}
		}
	}
	
	/**
	 * @desc 获取系统默认配置文件属性
	 * @date 2014年12月3日 上午10:13:14
	 * @param key
	 * @return
	 */
	public static String getString(String key){
		return props.getProperty(key);
	}
	
	/**
	 * @desc 获取系统默认配置文件属性
	 * @date 2014年12月3日 上午10:13:14
	 * @param key
	 * @return
	 */
	public static Integer getInteger(String key){
		return Integer.parseInt(props.getProperty(key));
	}
	
	/**
	 * @desc 获取系统默认配置文件属性
	 * @date 2014年12月3日 上午10:13:14
	 * @param key
	 * @return
	 */
	public static boolean getBoolean(String key){
		return Boolean.parseBoolean(props.getProperty(key));
	}
	
	/**
	 * @desc 获取系统默认配置文件属性
	 * @date 2014年12月29日 下午3:00:53
	 * @param key
	 * @return
	 */
	public static long getLong(String key){
		return new Long(getInteger(key));
	}
	
}
