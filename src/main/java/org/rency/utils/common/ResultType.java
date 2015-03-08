package org.rency.utils.common;


/**
 * @desc action常量类 
 * @author user_rcy@163.com
 * @date 2014年5月29日 下午10:20:54
 * @version 1.0.0
 */
public class ResultType {
	
	public static final String NONE = "none";
	
	public static final String SUCCESS = "success";
	
	public static final String ERROR = "error";
	
	public static final String INPUT = "input";
	
	public static final String PAGE_NOT_FOUND = "404";

	/**==========================================**/
	/***                 user                   ***/
	/**==========================================**/
	public static final String USER_LOGIN ="login";
	/**
	 * 登录
	 */
	public static final String USER_LOGIN_INPUT = "loginInput";
	/**
	 * 注册
	 */
	public static final String USER_ADD_INPUT = "register";
	
	
	/**==========================================**/
	/***              JSON返回类型              ***/
	/**==========================================**/
	/**
	 * 返回标示
	 */
	public static final String RESPONSE_TYPE_KEY = "key";
	/**
	 * 返回结果
	 */
	public static final String RESPONSE_INFO_KEY = "info";
	/**
	 * 返回表格
	 */
	public static final String RESPONSE_ROWS_KEY = "rows";
	/**
	 * 成功
	 */
	public static final String RESPONSE_TYPE_NORMAL = "N";
	/**
	 * 失败
	 */
	public static final String RESPONSE_TYPE_ERROR = "E";
	
	public static final String RESPONSE_MESSAGE_KEY = "message";
	
	
	/**==========================================**/
	/***               JSP返回类型              ***/
	/**==========================================**/
	/**
	 * 用户维护
	 */
	public static final String MAINTAIN_USERS = "maintain_users";
	/**
	 * 用户类别维护
	 */
	public static final String MAINTAIN_USERT = "maintain_usert";
	/**
	 * 权限维护
	 */
	public static final String MAINTAIN_AUTHENCATION = "maintain_authencation";
	/**
	 * 资源维护
	 */
	public static final String MAINTAIN_MENUS = "maintain_menus";
	/**
	 * 系统日志
	 */
	public static final String MAINTAIN_SYSLOG = "maintain_syslog";
	
	/**
	 * Crawler爬虫监控
	 */
	public static final String CRAWLER_MONITOR = "crawler_monitor";
	/**
	 * Hadoop监控
	 */
	public static final String HADOOP_MONITOR = "hadoop_monitor";
	
	/**
	 * Lucene索引
	 */
	public static final String LUCENE_MONITOR = "lucene_monitor";
	
	/**
	 * Lucene搜索
	 */
	public static final String LUCENE_SEARCH = "lucene_search";

}
