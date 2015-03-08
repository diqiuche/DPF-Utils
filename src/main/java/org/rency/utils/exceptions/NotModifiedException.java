/*   
 * Copyright (c) 2010-2020 Founder Ltd. All Rights Reserved.   
 *   
 * This software is the confidential and proprietary information of   
 * Founder. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Founder.   
 *   
 */
package org.rency.utils.exceptions;

/**
 * @desc 内容未变化异常 
 * @author user_rcy@163.com
 * @date 2014年5月29日 下午9:33:20
 * @version 1.0.0
 */
public class NotModifiedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4120481997093521661L;

	private String code;
	
	private String message;
	
	public NotModifiedException(){
		
		super();
		
	}
	
	public NotModifiedException(String message){
		
		super(message);
		
		this.message = message;
		
	}

	public NotModifiedException(String code,String message){
	
		super("["+code+"]"+message);
		
		this.code = code;
		
		this.message = message;
		
	}
	
	public NotModifiedException(Throwable th){
		
		super(th);
		
		this.message = th.getMessage();
		
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String toString(){
		
		return "CoreException:code["+code+"],message["+message+"]";
		
	}
	
}