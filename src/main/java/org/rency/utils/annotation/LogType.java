package org.rency.utils.annotation;

public enum LogType{
	UNDEFINED(1,"未定义"),
	LOGIN(2,"登录"),
	MANAGEMENT(3,"管理"),
	CRAWLER(4,"爬虫"),
	LUCENE(5,"索引");
	
	private int id;
	private String description;

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}
	
	private LogType(int id,String desc){
		this.id = id;
		this.description = desc;
	}
	
}