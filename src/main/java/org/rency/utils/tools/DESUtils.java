package org.rency.utils.tools;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.rency.utils.exceptions.CoreException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
* @ClassName: DESUtils 
* @Description: DES加密解密算法(对称)
* @Author user_rcy@163.com
* @Date 2015年3月8日 下午10:29:45 
*
 */
public class DESUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(DESUtils.class);

	/**
	 * 
	* @Title: encryptDES 
	* @Description: DES加密(对称算法)
	* @Date: 2015年3月8日 下午10:22:49
	* @param content
	* @param password
	* @return
	* @throws CoreException
	 */
	public static String encryptDES(String content,String password) throws CoreException{
		try{
			SecureRandom random = new SecureRandom();// DES算法要求有一个可信任的随机数源
			DESKeySpec desKey = new DESKeySpec(password.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");//DES、AES
			SecretKey secretKey = keyFactory.generateSecret(desKey);
			Cipher cipher = Cipher.getInstance("DES");//实际加密
			cipher.init(Cipher.ENCRYPT_MODE, secretKey,random);//用密匙初始化Cipher对象
			return new String(cipher.doFinal(content.getBytes()));//正式执行加密操作
		}catch(Exception e){
			logger.error("DES加密异常.",e);
			e.printStackTrace();
			throw new CoreException(e);
		}
	}
	
	/**
	 * 
	* @Title: decryptDES 
	* @Description: DES解密(对称算法)
	* @Date: 2015年3月8日 下午10:23:11
	* @param content
	* @param password
	* @return
	* @throws CoreException
	 */
	public static String decryptDES(String content,String password) throws CoreException{
		try{
			SecureRandom random = new SecureRandom();// DES算法要求有一个可信任的随机数源
			DESKeySpec desKey = new DESKeySpec(password.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");//DES、AES
			SecretKey secretKey = keyFactory.generateSecret(desKey);
			Cipher cipher = Cipher.getInstance("DES");//实际加密
			cipher.init(Cipher.DECRYPT_MODE, secretKey,random);//用密匙初始化Cipher对象
			return new String(cipher.doFinal(content.getBytes()));//正式执行加密操作
		}catch(Exception e){
			logger.error("DES解密异常.",e);
			e.printStackTrace();
			throw new CoreException(e);
		}
	}
	
}