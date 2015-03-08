package org.rency.utils.tools;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.rency.utils.exceptions.CoreException;
import org.rency.utils.exceptions.NotModifiedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);
	
	/**
	 * @desc 请求页面
	 * @author user_rcy@163.com
	 * @date 2014年11月2日 下午6:00:01
	 * @param taskQueue
	 * @param data 请求参数
	 * @param taskQueueService 如果不为null，则更新URL请求状态
	 * @return
	 * @throws Exception
	 */
 	public static Response getResponse(String url) throws Exception{
		try{
			Connection conn = Jsoup.connect(url).timeout(5000).method(Method.GET);
			//配置模拟浏览器
			conn.userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.102 Safari/537.36");
			Response response = conn.execute();
			int statusCode = response.statusCode();
			//判断服务器返回状态
			if(httpResponseStatus(statusCode)){
				return response;
			}else{
				return null;
			}
		}catch(NotModifiedException e){
			logger.debug("jsoup connection["+url+"] error."+e);
			return null;
		}catch (UnknownHostException e) {
			logger.debug("jsoup connection["+url+"] error."+e);
			throw new SocketTimeoutException("jsoup connection["+url+"] error."+e);
		}catch(SocketTimeoutException e){
			logger.debug("jsoup connection["+url+"] error."+e);
			throw e;
		}catch(RuntimeException e){
			logger.error("jsoup connection["+url+"] error."+e);
			e.printStackTrace();
			return null;
		}catch(Exception e){
			logger.error("jsoup connection["+url+"] error."+e);
			e.printStackTrace();
			throw new CoreException("jsoup connection["+url+"] error."+e);
		}
	}

	/**
	 * @desc 根据Http相应状态码判断请求返回状态
	 * @date 2014年10月29日 下午1:43:59
	 * @param statusCode Http相应状态码
	 * @return
	 * @throws Exception
	 */
	public static boolean httpResponseStatus(int statusCode) throws Exception{
		switch (statusCode) {
			case 100:
				return true;
			case 101:
				return true;
			case 102:
				return true;
			case 200:
				return true;
			case 201:
				return true;
			case 202:
				return true;
			case 203:
				return true;
			case 204:
				return true;
			case 205:
				return true;
			case 206:
				return true;
			case 207:
				return true;
			case 300:
				return true;
			case 301:
				return true;
			case 302:
				return true;
			case 303:
				return true;
			case 304:
				throw new NotModifiedException("The request content not modify, and status code is "+statusCode);
			case 305:
				return true;
			case 306:
				return true;
			case 307:
				return true;
			case 400:
				throw new RuntimeException("The request cannot be understand on server, and status code is "+statusCode);
			case 401:
				throw new RuntimeException("The request should user authorized, and status code is "+statusCode);
			case 402:
				return true;
			case 403:
				throw new RuntimeException("The server has understood the request, but refused to execute.And status code is "+statusCode);
			case 404:
				throw new SocketTimeoutException("The request not found on server, and status code is "+statusCode);
			case 405:
				return false;
			case 406:
				return false;
			case 407:
				throw new RuntimeException("The request must use proxy on client, and status code is "+statusCode);
			case 408:
				throw new SocketTimeoutException("The request time out, and status code is "+statusCode);
			case 409:
				throw new RuntimeException("The request status is conflict, and cannot finish request.And status code is "+statusCode);
			case 410:
				throw new RuntimeException("The request is not avaliable, and status code is "+statusCode);
			case 411:
				return true;
			case 412:
				return true;
			case 413:
				throw new RuntimeException("The request entity too large, and status code is "+statusCode);
			case 414:
				throw new RuntimeException("The request is too long, and status code is "+statusCode);
			case 415:
				return true;
			case 416:
				return true;
			case 417:
				return true;
			case 421:
				throw new SocketTimeoutException("The request beyond connect max count, and status code is "+statusCode);
			case 422:
				return true;
			case 423:
				throw new SocketTimeoutException("The request locked, and status code is "+statusCode);
			case 424:
				throw new SocketTimeoutException("The request failed dependency, and status code is "+statusCode);
			case 425:
				return true;
			case 426:
				return true;
			case 449:
				return true;
			case 500:
				throw new SocketTimeoutException("Internal server error, and status code is "+statusCode);
			case 501:
				throw new SocketTimeoutException("Not Implemented, and status code is "+statusCode);
			case 502:
				throw new SocketTimeoutException("Bad Gateway, and status code is "+statusCode);
			case 503:
				throw new SocketTimeoutException("Service Unavailable, and status code is "+statusCode);
			case 504:
				throw new SocketTimeoutException("Gateway timeout, and status code is "+statusCode);
			case 505:
				throw new SocketTimeoutException("Http Version Not Supported, and status code is "+statusCode);
			case 506:
				throw new SocketTimeoutException("Variant Also Negotiates, and status code is "+statusCode);
			case 507:
				throw new SocketTimeoutException("Insufficient Storage, and status code is "+statusCode);
			case 508:
				throw new SocketTimeoutException("Loop Detected, and status code is "+statusCode);
			case 509:
				throw new SocketTimeoutException("Bandwidth Limit Exceeded, and status code is "+statusCode);
			case 510:
				throw new SocketTimeoutException("Not Extended, and status code is "+statusCode);
			case 600:
				return true;
			default:
				return false;
		}
	}
	
	/**
	 * @desc 将字符串http参数转换为Map形式
	 * @date 2015年1月9日 下午4:21:16
	 * @param httpParam
	 * @return
	 * @throws CoreException
	 */
	public static Map<String, String> String2Map(String httpParam) throws CoreException{
		Map<String, String> map = new HashMap<String, String>();
		try{
			if(StringUtils.isBlank(httpParam)){
				logger.warn("Http Param Str is empty, and only return new HashMap<String,String> instance");
			}
			String[] sections = httpParam.split("&");
			for(String section : sections){
				String[] keyValue = section.split("=");
				map.put(keyValue[0], keyValue[1]);
			}
		}catch(Exception e){
			logger.error("Http Param["+httpParam+"] Convert Error.",e);
			e.printStackTrace();
			throw new CoreException(e);
		}
		return map;
	}
	
	/**
	 * @desc 将Map转换为http字符串参数形式
	 * @date 2015年1月9日 下午4:37:09
	 * @param httpParamMap
	 * @return
	 * @throws CoreException
	 */
	public static String Map2String(Map<String, String> httpParamMap) throws CoreException{
		String httpParam = "";
		try{
			if(httpParamMap.isEmpty()){
				logger.warn("Http Param Map is empty, and only return empty String");
			}
			for(String key : httpParamMap.keySet()){
				httpParam += key;
				httpParam += "=";
				httpParam += httpParamMap.get(key);
				httpParam += "&";
			}
			httpParam = httpParam.substring(0, httpParam.lastIndexOf("&"));
		}catch(Exception e){
			logger.error("Http Param["+httpParamMap+"] Convert Error.",e);
			e.printStackTrace();
			throw new CoreException(e);
		}
		return httpParam.trim();
	}
	
	public static void main(String[] args) throws CoreException {
		Map<String, String> map = new HashMap<String,String>();
		map.put("name", "rency");
		map.put("age", "24");
		map.put("sex", "男");
		System.out.println(Map2String(map));
		System.out.println(String2Map("sex=男&age=24&name=rency"));
	}
}