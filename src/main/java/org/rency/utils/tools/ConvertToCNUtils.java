package org.rency.utils.tools;

import org.rency.utils.common.CONST;

/**
 * @desc 系统日志辅助类
 * @author T-rency
 * @date 2014年12月3日 下午3:45:19
 */
public class ConvertToCNUtils {

	/**
	 * @desc 根据Action类名、方法名获取中文描述
	 * @date 2014年12月3日 下午3:45:36
	 * @param className 类名
	 * @param methodName 方法名
	 * @return
	 */
	public static String getCNName(String className,String methodName){
		return getClassCNName(className)+"-"+getMethodCNName(methodName);
	}
	
	/**
	 * @desc 获取action描述
	 * @date 2014年12月3日 下午3:43:43
	 * @param className
	 * @return
	 */
	private static String getClassCNName(String className){
		if("AuthorityAction".equals(className)){
			return "权限";
		}else if("CrawlerAction".equals(className)){
			return "爬虫";
		}else if("IndexAction".equals(className)){
			return "自定义";
		}else if("LuceneAction".equals(className)){
			return "索引";
		}else if("MenusAction".equals(className)){
			return "菜单";
		}else if("UserAction".equals(className)){
			return "用户";
		}else if("UserTypeAction".equals(className)){
			return "用户类别";
		}else{
			return "未知";
		}
	}
	
	/**
	 * @desc 获取action中method描述
	 * @date 2014年12月3日 下午3:44:03
	 * @param methodName
	 * @return
	 */
	private static String getMethodCNName(String methodName){
		if(methodName.startsWith("load") || methodName.startsWith("get") || methodName.startsWith("list") || methodName.startsWith("query")){
			return "加载";
		}else if(methodName.startsWith("add") || methodName.startsWith("save")){
			return "添加";
		}else if(methodName.startsWith("update") || methodName.startsWith("modify")){
			return "修改";
		}else if(methodName.startsWith("delete") || methodName.startsWith("remove")){
			return "删除";
		}else if(methodName.startsWith("clearQueue")){
			return "清除队列";
		}else if(methodName.startsWith("search")){
			return "请求搜索";
		}else if(methodName.startsWith("doSearch")){
			return "搜索";
		}else if(methodName.equals("login")){
			return "登录";
		}else if(methodName.equals("loginInput")){
			return "请求登录";
		}else if(methodName.equals("logout")){
			return "注销";
		}else if(methodName.startsWith("start")){
			return "启动";
		}else if(methodName.startsWith("stop")){
			return "停止";
		}else if(methodName.startsWith("status")){
			return "状态";
		}else if(methodName.equals("index")){
			return "主页面";
		}else if(methodName.equals("error")){
			return "错误";
		}else{
			return "未知";
		}
	}

	/**
	 * @desc 获取中文描述项目服务组件种类
	 * @date 2014年12月9日 下午5:05:07
	 * @param serviceKind
	 * @return
	 */
	public static String getServiceKind(int serviceKind){
		switch (serviceKind) {
			case CONST.SERVICE_KIND_CRAWLER:
				return "爬虫服务";
			case CONST.SERVICE_KIND_LUCENE:
				return "数据索引服务";
			case CONST.SERVICE_KIND_TRIGGER:
				return "定时任务服务";
			default:
				return "未知";
		}
	}
	
}