package org.rency.utils.dao.jdbc;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.criterion.Order;
import org.rency.utils.dao.BasicDao;
import org.rency.utils.exceptions.CoreException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @desc JDBC Common Basic Dao
 * @author user_rcy@163.com
 * @date 2014年5月29日 下午10:06:33
 * @version 1.0.0
 */
public class JdbcDao implements BasicDao{

	private static final Logger logger = LoggerFactory.getLogger(JdbcDao.class);
	
	private DataSource dataSource;
	
	private Connection connection;
	
	private void init() throws CoreException{
		try {
			if(this.connection != null){
				return;
			}
			this.connection = this.getDataSource().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CoreException(e);
		}
	}

	@Override
	public <T> T get(Class<T> clazz, Serializable id) throws CoreException {
		throw new CoreException("please use hibernateDao implements.");
	}

	@Override
	public <T> T load(Class<T> clazz, Serializable id) throws CoreException {
		throw new CoreException("please use hibernateDao implements.");
	}

	@Override
	public <T> List<T> loadAll(Class<T> clazz) throws CoreException {
		throw new CoreException("please use hibernateDao implements.");
	}

	@Override
	public <T> List<T> findByHQL(String hql) throws CoreException {
		throw new CoreException("please use hibernateDao implements.");
	}

	@Override
	public <T> List<T> findByHQL(String hql, Object value) throws CoreException {
		throw new CoreException("please use hibernateDao implements.");
	}

	@Override
	public <T> List<T> findByHQL(String hql, Object... values)
			throws CoreException {
		throw new CoreException("please use hibernateDao implements.");
	}
	

	@Override
	public <T> List<T> findBySQL(String sql, Object[] values, Class<T> clazz)
			throws CoreException {
		throw new CoreException("please use hibernateDao implements.");
	}
	

	@Override
	public <T> List<T> findByProperty(HashMap<String, Object> properties,
			Class<T> clazz) throws CoreException {
		throw new CoreException("please use hibernateDao implements.");
	}
	

	@Override
	public <T> Integer getCount(Class<T> clazz) throws CoreException {
		throw new CoreException("please use hibernateDao implements.");
	}
	

	@Override
	public <T> List<T> getByPage(String entity, int pageNo, int pageSize,
			Order order) throws CoreException {
		throw new CoreException("please use hibernateDao implements.");
	}
	

	@Override
	public <T> List<T> getByPage(String hql, int pageNo, int pageSize,
			Object... values) throws CoreException {
		throw new CoreException("please use hibernateDao implements.");
	}
	

	@Override
	public boolean save(Object entity) throws CoreException {
		throw new CoreException("please use hibernateDao implements.");
	}
	

	@Override
	public boolean saveOrUpdate(Object entity) throws CoreException {
		throw new CoreException("please use hibernateDao implements.");
	}
	

	@Override
	public boolean update(Object entity) throws CoreException {
		throw new CoreException("please use hibernateDao implements.");
	}
	

	@Override
	public boolean updateByProperty(String entity, String whereName,Object whereValue, String setName, Object setValue)throws CoreException {
		throw new CoreException("please use hibernateDao implements.");
	}
	

	@Override
	public boolean updateByProperty(String entity,HashMap<String, Object> where, HashMap<String, Object> set)throws CoreException {
		throw new CoreException("please use hibernateDao implements.");
	}
	

	@Override
	public boolean deleteByProperty(String entity, String propertyName,Object propertyValue) throws CoreException {
		throw new CoreException("please use hibernateDao implements.");
	}
	

	@Override
	public boolean delete(Object entity) throws CoreException {
		throw new CoreException("please use hibernateDao implements.");
	}
	

	@Override
	public <T> boolean deleteAll(Class<T> clazz) throws CoreException {
		throw new CoreException("please use hibernateDao implements.");
	}
	

	@Override
	public <T> int bulkUpdate(String hql, Object... values)throws CoreException {
		throw new CoreException("please use hibernateDao implements.");
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public ResultSet find(String sql, Object... values) throws CoreException {
		logger.info("execute["+sql+"]");
		this.init();
		try{
			PreparedStatement pstmt = this.connection.prepareStatement(sql);
			for(int i=0;i<values.length;i++){
				pstmt.setObject(i+1, values[i]);
			}
			ResultSet rs = pstmt.executeQuery();
			return rs;
		}catch(SQLException e){
			logger.error("xxxx find["+sql+"], execute failed.",e);
			e.printStackTrace();
			throw new CoreException("find["+sql+"] execute failed."+e);
		}
	}

	
	@Override
	public int update(String sql, Object... values) throws CoreException {
		logger.info("execute["+sql+"]");
		this.init();
		try{
			PreparedStatement pstmt = this.connection.prepareStatement(sql);
			for(int i=0;i<values.length;i++){
				pstmt.setObject(i+1, values[i]);
			}
			int count = pstmt.executeUpdate();
			return count;
		}catch(SQLException e){
			logger.error("xxxx update["+sql+"], execute failed.",e);
			e.printStackTrace();
			throw new CoreException("update["+sql+"] execute failed."+e);
		}
	}
	
}