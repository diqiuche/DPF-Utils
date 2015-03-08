package org.rency.utils.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @desc 日志Annotation
 * @author T-rency
 * @date 2015年2月6日 上午9:17:41
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Log{
	String logName() default "未定义";
	LogType logType() default LogType.UNDEFINED;
}