package org.rency.utils.tools;

import java.io.IOException;

import org.rency.utils.exceptions.CoreException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 
* @ClassName: BASE64Utils 
* @Description: BASE64Utils加密解密算法
* @Author user_rcy@163.com
* @Date 2015年3月8日 下午9:52:20 
*
 */
public class BASE64Utils {
	
	private static final Logger logger = LoggerFactory.getLogger(BASE64Utils.class);
	
	/**
	 * 
	* @Title: decryptBASE64 
	* @Description: BASE64解密
	* @Date: 2015年3月8日 下午9:59:39
	* @param key
	* @return
	* @throws CoreException
	 */
	public static String decryptBASE64(String key) throws CoreException{
		try {
			return new String(new BASE64Decoder().decodeBuffer(key));
		} catch (IOException e) {
			logger.error("BASE64解密异常.",e);
			e.printStackTrace();
			throw new CoreException(e);
		}
	}
	
	/**
	 * 
	* @Title: encryBASE64 
	* @Description: BASE64加密
	* @Date: 2015年3月8日 下午10:01:48
	* @param key
	* @return
	* @throws CoreException
	 */
	public static String encryptBASE64(String content) throws CoreException{
		return new BASE64Encoder().encodeBuffer(content.getBytes());
	}
	
}