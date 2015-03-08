package org.rency.utils.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

import org.hibernate.criterion.Order;
import org.rency.utils.exceptions.CoreException;

/**
 * @desc 访问数据库公用接口
 * @author T-rency
 * @date 2014年12月26日 上午10:12:44
 */
public interface BasicDao {

	/**
	 * @desc 获取对象
	 * @date 2014年12月18日 下午4:30:24
	 * @param clazz
	 * @param id
	 * @return
	 * @throws CoreException
	 */
	public abstract <T> T get(Class<T> clazz, Serializable id)throws CoreException;

	/**
	 * @desc 加载对象
	 * @date 2014年12月18日 下午4:30:10
	 * @param clazz
	 * @param id
	 * @return
	 * @throws CoreException
	 */
	public abstract <T> T load(Class<T> clazz, Serializable id)throws CoreException;

	/**
	 * @desc 查找所有记录
	 * @date 2014年12月18日 下午4:29:45
	 * @param clazz
	 * @return
	 * @throws CoreException
	 */
	public abstract <T> List<T> loadAll(Class<T> clazz) throws CoreException;

	/**
	 * @desc HQL语句查找
	 * @date 2014年12月18日 下午4:29:31
	 * @param hql
	 * @return
	 * @throws CoreException
	 */
	public abstract <T> List<T> findByHQL(String hql) throws CoreException;

	/**
	 * @desc HQL语句查找
	 * @date 2014年12月18日 下午4:29:17
	 * @param hql
	 * @param value
	 * @return
	 * @throws CoreException
	 */
	public abstract <T> List<T> findByHQL(String hql, Object value)throws CoreException;

	/**
	 * @desc HQL语句查找
	 * @date 2014年12月18日 下午4:29:03
	 * @param hql
	 * @param values
	 * @return
	 * @throws CoreException
	 */
	public abstract <T> List<T> findByHQL(String hql, Object... values)throws CoreException;

	/**
	 * @desc 通过SQL语句查找
	 * @date 2014年12月18日 下午4:28:37
	 * @param sql
	 * @param values
	 * @param clazz
	 * @return
	 * @throws CoreException
	 */
	public abstract <T> List<T> findBySQL(String sql, Object[] values,Class<T> clazz) throws CoreException;

	/**
	 * @desc 根据属性查找
	 * @date 2014年12月18日 下午4:27:14
	 * @param properties 查找条件
	 * @param clazz
	 * @return
	 * @throws CoreException
	 */
	public abstract <T> List<T> findByProperty(HashMap<String, Object> properties, Class<T> clazz)throws CoreException;

	/**
	 * @desc 获取表记录大小
	 * @date 2014年12月18日 下午4:27:48
	 * @param clazz
	 * @return
	 * @throws CoreException
	 */
	public abstract <T> Integer getCount(Class<T> clazz) throws CoreException;

	/**
	 * @desc 分页查找
	 * @date 2014年3月19日 下午10:45:10
	 * @param pageNo 当前页码
	 * @param pageSize 页大小
	 * @param order 排序(Order.desc('**')/Order.asc('**'))
	 * @return
	 * @throws CoreException 
	 */
	public abstract <T> List<T> getByPage(String entity, int pageNo,int pageSize, Order order) throws CoreException;

	/**
	 * @desc 分页查找
	 * @date 2014年6月27日 上午9:50:00
	 * @param hql 查询语句
	 * @param pageNo 页码
	 * @param pageSize 每页大小
	 * @param values 参数
	 * @return
	 * @throws CoreException
	 */
	public abstract <T> List<T> getByPage(String hql, int pageNo, int pageSize,Object... values) throws CoreException;

	/**
	 * @desc 保存对象
	 * @author user_rcy@163.com
	 * @date 2014年5月29日 下午10:03:32
	 * @param entity
	 * @return
	 * @throws CoreException
	 */
	public abstract boolean save(Object entity) throws CoreException;

	/**
	 * @desc 保存或更新
	 * @date 2014年8月8日 下午2:28:36
	 * @param entity
	 * @return
	 * @throws CoreException
	 */
	public abstract boolean saveOrUpdate(Object entity) throws CoreException;

	/**
	 * @desc 更新对象
	 * @author user_rcy@163.com
	 * @date 2014年5月29日 下午10:03:41
	 * @param entity
	 * @return
	 * @throws CoreException
	 */
	public abstract boolean update(Object entity) throws CoreException;

	/**
	 * @desc 根据给定条件更新列
	 * @date 2014年8月8日 下午2:53:40
	 * @param entity 更新实体
	 * @param whereName 条件
	 * @param whereValue 条件
	 * @param setName 更新列
	 * @param setValue 更新列
	 * @return
	 * @throws CoreException
	 */
	public abstract boolean updateByProperty(String entity, String whereName,Object whereValue, String setName, Object setValue)throws CoreException;

	/**
	 * @desc 根据条件更新一些字段
	 * @date 2014年8月8日 下午2:41:53
	 * @param entity 更新实体
	 * @param where 更新条件
	 * @param set 更新字段
	 * @return
	 * @throws CoreException
	 */
	public abstract boolean updateByProperty(String entity,HashMap<String, Object> where, HashMap<String, Object> set)throws CoreException;

	/**
	 * @desc 根据条件删除对象
	 * @author user_rcy@163.com
	 * @date 2014年5月29日 下午10:03:49
	 * @param entity
	 * @param propertyName
	 * @param propertyValue
	 * @return
	 * @throws CoreException
	 */
	public abstract boolean deleteByProperty(String entity,String propertyName, Object propertyValue) throws CoreException;

	/**
	 * @desc 删除对象
	 * @author user_rcy@163.com
	 * @date 2014年5月29日 下午10:04:01
	 * @param entity
	 * @return
	 * @throws CoreException
	 */
	public abstract boolean delete(Object entity) throws CoreException;

	/**
	 * @desc 删除表中所有记录
	 * @date 2014年12月18日 下午4:26:43
	 * @param clazz
	 * @return
	 * @throws CoreException
	 */
	public abstract <T> boolean deleteAll(Class<T> clazz) throws CoreException;

	/**
	 * @desc 执行批量HQL语句
	 * @date 2014年12月11日 下午2:36:05
	 * @param hql
	 * @param values
	 * @return
	 * @throws CoreException
	 */
	public abstract <T> int bulkUpdate(String hql, Object... values)throws CoreException;
	
	/**
	 * @desc JDBC SQL 查询
	 * @date 2014年12月30日 上午11:01:01
	 * @param sql
	 * @param values
	 * @return
	 * @throws CoreException
	 */
	public abstract ResultSet find(String sql, Object... values)throws CoreException;
	
	/**
	 * @desc JDBC SQL 更新
	 * @date 2014年12月30日 下午3:08:51
	 * @param sql
	 * @param values
	 * @return
	 * @throws CoreException
	 */
	public abstract int update(String sql, Object... values)throws CoreException;

}