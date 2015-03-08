package org.rency.utils.tools;

import javax.persistence.Table;

import org.rency.utils.exceptions.CoreException;

/**
 * @desc Hibernate根据实体类获取表名、主键、字段名工具类
 * @author T-rency
 * @date 2014年12月30日 上午10:06:26
 */
public class HibernateUtils {

	/**
	 * @desc 根据实体名获取数据表名
	 * @date 2014年12月30日 上午10:14:48
	 * @param clazz
	 * @return
	 * @throws CoreException 
	 */
	public static <T> String getTableName(Class<T> clazz) throws CoreException{
		Table table = clazz.getAnnotation(Table.class);
		if(table != null){
			return table.name();
		}
		throw new CoreException("can not found table name with hibernate entity.");
	}
	
}