package org.rency.utils.common;

import java.util.Random;
import java.util.regex.Pattern;

public class CONST {
	
	public static final String CHARSET = "UTF-8";
	
	/********************    ************************/
	/**
	 * session用户标识
	 */
	public static final String SESSION_USER_KEY = "user";
	
	/**
	 * session超时
	 */
	public static final String SESSION_TIMEOUT = "会话超时，请重新登录！";
	
	public static final String SESSION_TIMEOUT_KEY = "timeout";
	
	public static final String DENIED_ACCESS_KEY = "denied";
	
	public static final String DENIED_ACCESS_RESOURCES = "抱歉，权限不足！";
	
	/**
	 * 分隔符
	 */
	public static final String SPLIT_KEY = "-";
	
	/**
	 * json错误消息模板
	 */
	public static final String JSON_ERROR = "{'"+ResultType.RESPONSE_TYPE_KEY+"':'"+ResultType.RESPONSE_TYPE_ERROR+"','"+ResultType.RESPONSE_INFO_KEY+"':'{0}'}";
	
	/******************** 用户类型 ************************/
	public static final int USER_TYPE_SUPER = 1;
	public static final int USER_TYPE_ADMIN = 2;
	public static final int USER_TYPE_CUSTOMER = 3;
	
	/**
	 * URL正则表达式
	 */
	public static final Pattern PATTERN = Pattern.compile("^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$",Pattern.CASE_INSENSITIVE);
	
	/**
	 * 随机数
	 */
	public static final Random RANDOM = new Random(System.currentTimeMillis());
	
	/**
	 * 网络超时重试次数
	 */
	public static final int RETRY_COUNT = 5;
	
	/**
	 * 线程锁
	 */
	public static final Object THREAD_LOCK = new Object();
	
	/**
	 * 线程休眠时间
	 */
	public static final int THREAD_DELAY = 1000 * 10;//ms
	
	/**
	 * 项目服务组件种类:爬虫
	 */
	public static final int SERVICE_KIND_CRAWLER = 1;
	
	/**
	 * 项目服务组件种类:索引
	 */
	public static final int SERVICE_KIND_LUCENE = 2;
	
	/**
	 * 项目服务组件种类:定时任务
	 */
	public static final int SERVICE_KIND_TRIGGER = 3;
	
	/**
	 * 项目服务组件种类:MQ消息通信
	 */
	public static final int SERVICE_KIND_ACTIVEMQ = 4;
	
	/**
	 * 分页查询时页面记录数量
	 */
	public static final int PAGE_SIZE = 10;
	
}