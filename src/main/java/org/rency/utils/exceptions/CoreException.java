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
 * @desc 系统异常 
 * @author user_rcy@163.com
 * @date 2014年5月29日 下午9:33:20
 * @version 1.0.0
 */
public class CoreException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4120481997093521661L;

	private String code;
	
	private String message;
	
	public CoreException(){
		
		super();
		
	}
	
	public CoreException(String message){
		
		super(message);
		
		this.message = message;
		
	}

	public CoreException(String code,String message){
	
		super("["+code+"]"+message);
		
		this.code = code;
		
		this.message = message;
		
	}
	
	public CoreException(Throwable th){
		
		super(th);
		
		this.message = String.valueOf(th);
		
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