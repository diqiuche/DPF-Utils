package org.rency.utils.tools;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.rency.utils.exceptions.CoreException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
* @ClassName: RSAUtils 
* @Description: RSA加密解密算法(非对称)
* @Author user_rcy@163.com
* @Date 2015年3月8日 下午10:30:13 
*
 */
public class RSAUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(RSAUtils.class);
	private static final String PRIVATE_KEY = "RSAPrivateKey";
	private static final String PUBLIC_KEY = "RSAPublicKey";
	private static final Map<String, Object> keyMap = new HashMap<String, Object>();
	
	static{
		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
			keyPairGen.initialize(1024);
			KeyPair keyPair = keyPairGen.generateKeyPair();
			RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
			RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
			keyMap.put(PUBLIC_KEY, publicKey);
			keyMap.put(PRIVATE_KEY, privateKey);
		} catch (Exception e) {
			logger.error("初始化RSA公钥、私钥异常.",e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: initPublicKey 
	* @Description: 获取RSA公钥
	* @Date: 2015年3月8日 下午10:50:11
	* @return
	* @throws CoreException
	 */
	public static String initPublicKey() throws CoreException{
		try {
			Key key = (Key) keyMap.get(PUBLIC_KEY);  
	        return BASE64Utils.encryptBASE64(new String(key.getEncoded())); 
		} catch (Exception e) {
			logger.error("获取RSA公钥异常.",e);
			e.printStackTrace();
			throw new CoreException(e);
		}
	}
	
	/**
	 * 
	* @Title: initPrivateKey 
	* @Description: 获取RSA私钥
	* @Date: 2015年3月8日 下午10:50:27
	* @return
	* @throws CoreException
	 */
	public static String initPrivateKey() throws CoreException{
		try {
			Key key = (Key) keyMap.get(PRIVATE_KEY);  
	        return BASE64Utils.encryptBASE64(new String(key.getEncoded())); 
		} catch (Exception e) {
			logger.error("获取RSA私钥异常.",e);
			e.printStackTrace();
			throw new CoreException(e);
		}
	}
	
	/**
	 * 
	* @Title: encryptRSAWithPublicKey 
	* @Description: RSA公钥加密
	* @Date: 2015年3月8日 下午10:35:01
	* @param content
	* @param publicKey 公钥
	* @return
	* @throws CoreException
	 */
	public static String encryptRSAWithPublicKey(String content,String publicKey) throws CoreException{
		try{
			byte[] keyBytes = BASE64Utils.decryptBASE64(publicKey).getBytes();
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes); // 取得公钥  
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			Key key = keyFactory.generatePrivate(keySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return new String(cipher.doFinal(content.getBytes()));
		}catch(Exception e){
			logger.error("RSA公钥加密异常.",e);
			e.printStackTrace();
			throw new CoreException(e);
		}
	}

	/**
	 * 
	* @Title: encryptRSAWithPublicKey 
	* @Description: RSA私钥加密
	* @Date: 2015年3月8日 下午10:35:01
	* @param content
	* @param privateKey 私钥
	* @return
	* @throws CoreException
	 */
	public static String encryptRSAWithPrivateKey(String content,String privateKey) throws CoreException{
		try{
			byte[] keyBytes = BASE64Utils.decryptBASE64(privateKey).getBytes();
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes); // 取得私钥  
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			Key key = keyFactory.generatePrivate(keySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return new String(cipher.doFinal(content.getBytes()));
		}catch(Exception e){
			logger.error("RSA公钥加密异常.",e);
			e.printStackTrace();
			throw new CoreException(e);
		}
	}
	
	/**
	 * 
	* @Title: decryptWithPublicKey 
	* @Description: RSA公钥解密
	* @Date: 2015年3月8日 下午10:55:52
	* @param content
	* @param publicKey
	* @return
	* @throws CoreException
	 */
	public static String decryptWithPublicKey(String content, String publicKey)throws CoreException {  
		try{
			// 对密钥解密  
	        byte[] keyBytes = BASE64Utils.decryptBASE64(publicKey).getBytes();  
	        // 取得公钥  
	        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);  
	        KeyFactory keyFactory = KeyFactory.getInstance("RSA");  
	        Key key = keyFactory.generatePublic(x509KeySpec);  
	        // 对数据解密  
	        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());  
	        cipher.init(Cipher.DECRYPT_MODE, key);  
	        return new String(cipher.doFinal(content.getBytes()));  
		}catch(Exception e){
			logger.error("RSA公钥解密异常.",e);
			e.printStackTrace();
			throw new CoreException(e);
		}
    } 
	
	/**
	 * 
	* @Title: decryptWithPrivateKey 
	* @Description: RSA私钥解密
	* @Date: 2015年3月8日 下午10:55:39
	* @param content
	* @param privateKey
	* @return
	* @throws CoreException
	 */
	public static String decryptWithPrivateKey(String content, String privateKey) throws CoreException {  
        try{
			// 对密钥解密  
	        byte[] keyBytes = BASE64Utils.decryptBASE64(privateKey).getBytes();
	        // 取得私钥  
	        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);  
	        KeyFactory keyFactory = KeyFactory.getInstance("RSA");  
	        Key key = keyFactory.generatePrivate(pkcs8KeySpec);  
	        // 对数据解密  
	        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());  
	        cipher.init(Cipher.DECRYPT_MODE, key);  
	        return new String(cipher.doFinal(content.getBytes()));
        }catch(Exception e){
        	logger.error("RSA私钥解密异常.",e);
			e.printStackTrace();
			throw new CoreException(e);
        }
    } 
}
