package org.rency.utils.dao.hibernate;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.rency.utils.dao.BasicDao;
import org.rency.utils.exceptions.CoreException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;

/**
 * @desc Hibernate Common Basic Dao 
 * @author user_rcy@163.com
 * @date 2014年5月29日 下午10:06:33
 * @version 1.0.0
 */
public class HibernateDao implements BasicDao {
	
	private static final Logger logger = LoggerFactory.getLogger(HibernateDao.class);

	private HibernateTemplate hibernateTemplate;
	
	@Override
	public <T> T get(Class<T> clazz, Serializable id) throws CoreException{
		this.hibernateTemplate.setCacheQueries(true);
		try{
			return  (T) this.hibernateTemplate.get(clazz, id);
		}catch(DataAccessException e){
			logger.error("xxxx get["+clazz.getSimpleName()+"] and id is "+id+", execute failed.",e);
			e.printStackTrace();
			throw new CoreException("get["+clazz.getSimpleName()+"] execute failed."+e);
		}
	}
	
	@Override
	public <T> T load(Class<T> clazz, Serializable id) throws CoreException{
		this.hibernateTemplate.setCacheQueries(true);
		try{
			return (T) this.hibernateTemplate.load(clazz, id);
		}catch(DataAccessException e){
			logger.error("xxxx load["+clazz.getSimpleName()+"] and id is "+id+", execute failed.",e);
			e.printStackTrace();
			throw new CoreException("load["+clazz.getSimpleName()+"] execute failed."+e);
		}
	}
	
	@Override
	public <T> List<T> loadAll(Class<T> clazz) throws CoreException{
		this.hibernateTemplate.setCacheQueries(true);
		try{
			return  (List<T>) this.hibernateTemplate.loadAll(clazz);
		}catch(DataAccessException e){
			logger.error("xxxx get all["+clazz.getSimpleName()+"] execute failed.",e);
			e.printStackTrace();
			throw new CoreException("get all["+clazz.getName()+"] execute failed."+e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> findByHQL(String hql) throws CoreException{
		this.hibernateTemplate.setCacheQueries(true);
		try{
			return  (List<T>) this.hibernateTemplate.find(hql);
		}catch(DataAccessException e){
			logger.error("xxxx find By HQL["+hql+"] execute failed.",e);
			e.printStackTrace();
			throw new CoreException("find by hql["+hql+"] execute failed."+e);
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> findByHQL(String hql,Object value) throws CoreException{
		this.hibernateTemplate.setCacheQueries(true);
		try{
			return (List<T>) this.hibernateTemplate.find(hql,value);
		}catch(DataAccessException e){
			logger.error("xxxx find By HQL["+hql+"] execute failed.",e);
			e.printStackTrace();
			throw new CoreException("find by hql["+hql+"] execute failed."+e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> findByHQL(String hql,Object... values) throws CoreException{
		this.hibernateTemplate.setCacheQueries(true);
		try{
			return (List<T>) this.hibernateTemplate.find(hql,values);
		}catch(DataAccessException e){
			logger.error("xxxx find By HQL["+hql+"] execute failed.",e);
			e.printStackTrace();
			throw new CoreException("find by hql["+hql+"] execute failed."+e);
		}
	}
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> List<T> findBySQL(final String sql,final Object[] values,final Class<T> clazz) throws CoreException{
		this.hibernateTemplate.setCacheQueries(true);
		try{
			return (List<T>) this.hibernateTemplate.execute(new HibernateCallback() {
				public Object doInHibernate(Session session) throws HibernateException {
					Query query = session.createSQLQuery(sql).addEntity(clazz).setCacheable(true);
					if(values != null){
						for (int i = 0; i < values.length; i++){
							query.setParameter(i, values[i]);
						}
					}
					return query.list();
				}
			});
		}catch(DataAccessException e){
			logger.error("xxxx find By SQL["+sql+"] execute failed.",e);
			e.printStackTrace();
			throw new CoreException("find by sql["+sql+"] execute failed."+e);
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> findByProperty(HashMap<String, Object> properties,Class<T> clazz) throws CoreException{
		this.hibernateTemplate.setCacheQueries(true);
		try{
			DetachedCriteria dc = DetachedCriteria.forClass(clazz);
			for(String key : properties.keySet()){
				dc.add(Restrictions.eq(key, properties.get(key)));
			}
			return (List<T>) this.hibernateTemplate.findByCriteria(dc);
		}catch(DataAccessException e){
			logger.error("xxxx find By Properties["+properties+"] execute failed.",e);
			e.printStackTrace();
			throw new CoreException("find by Properties["+properties+"] execute failed."+e);
		}
	}
	
	@Override
	public <T> Integer getCount(Class<T> clazz) throws CoreException{
		try{
			Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
			Criteria criteria = session.createCriteria(clazz);
			return criteria.list().size();
		}catch(DataAccessException e){
			logger.error("xxxx get count entity["+clazz.getName()+"] failed.",e);
			e.printStackTrace();
			throw new CoreException("get count entity["+clazz.getName()+"] failed."+e);
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> getByPage(String entity,int pageNo,int pageSize,Order order) throws CoreException {
		this.hibernateTemplate.setCacheQueries(true);
		try{
			Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
			Criteria criteria = session.createCriteria(entity).setCacheable(true);
			criteria.setFirstResult( (pageNo -1) * pageSize);
			criteria.setMaxResults(pageSize);
			criteria.addOrder(order);
			return criteria.list();
		}catch(DataAccessException e){
			logger.error("xxxx get By Page entity["+entity+"] failed.",e);
			e.printStackTrace();
			throw new CoreException("get By Page entity["+entity+"] failed."+e);
		}
	}
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> List<T> getByPage(final String hql,final int pageNo,final int pageSize,final Object... values) throws CoreException {
		this.hibernateTemplate.setCacheQueries(true);
		try{
			return (List<T>) this.hibernateTemplate.execute(new HibernateCallback() {
				public Object doInHibernate(Session session) throws HibernateException {
					Query query = session.createQuery(hql).setCacheable(true);
					query.setFirstResult( (pageNo -1) * pageSize);
					query.setMaxResults(pageSize);
					if(values != null){
						for (int i = 0; i < values.length; i++){
							query.setParameter(i, values[i]);
						}
					}
					return query.list();
				}
			});
		}catch(DataAccessException e){
			logger.error("xxxx getByPage HQL["+hql+"] failed.",e);
			e.printStackTrace();
			throw new CoreException("getByPage HQL["+hql+"] failed."+e);
		}
	}
	
	@Override
	public boolean save(Object entity) throws CoreException {
		this.hibernateTemplate.setCacheQueries(true);
		try{
			hibernateTemplate.save(entity);
			return true;
		}catch(DataAccessException e){
			logger.error("xxxx save entity["+entity+"] failed.",e);
			e.printStackTrace();
			throw new CoreException("save entity["+entity+"] failed."+e);
		}
	}
	
	@Override
	public boolean saveOrUpdate(Object entity) throws CoreException {
		this.hibernateTemplate.setCacheQueries(true);
		try{
			hibernateTemplate.saveOrUpdate(entity);
			return true;
		}catch(DataAccessException e){
			logger.error("xxxx save entity["+entity+"] failed.",e);
			e.printStackTrace();
			throw new CoreException("save entity["+entity+"] failed."+e);
		}
	}
	
	@Override
	public boolean update(Object entity) throws CoreException {
		this.hibernateTemplate.setCacheQueries(true);
		try{
			hibernateTemplate.update(entity);
			return true;
		}catch(DataAccessException e){
			logger.error("xxxx update entity["+entity+"] failed.",e);
			e.printStackTrace();
			throw new CoreException("update entity["+entity+"] failed."+e);
		}
	}
	
	@Override
	public boolean updateByProperty(String entity,String whereName,Object whereValue,String setName,Object setValue) throws CoreException{
		this.hibernateTemplate.setCacheQueries(true);
		String updateString = "update "+entity+" as model set model."+setName+"=? where model."+whereName+"=?";
		try{
			Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
			Query query = session.createQuery(updateString);
			query.setParameter(0, setValue);
			query.setParameter(1, whereValue);
			int count = query.executeUpdate();
			session.flush();
			if(count > 0){
				return true;
			}else{
				return false;
			}
		}catch(DataAccessException e){
			logger.error("xxxx updateByProperty["+updateString+"] execute failed.",e);
			e.printStackTrace();
			throw new CoreException("update entity["+entity+"] failed."+e);
		}
	}
	
	@Override
	public boolean updateByProperty(String entity,HashMap<String, Object> where,HashMap<String, Object> set) throws CoreException{
		this.hibernateTemplate.setCacheQueries(true);
		String queryString = "update "+entity+" as model set ";
		if(where.isEmpty() || set.isEmpty()){
			return false;
		}
		Object[] values = new Object[where.size()+set.size()];
		int index=0;
		try{
			for(String key : set.keySet()){
				queryString += "model."+key+"=?,";
				values[index++]=set.get(key);
			}
			queryString = queryString.substring(0, queryString.lastIndexOf(",")) + " where ";
			for(String key : where.keySet()){
				queryString += "model."+key+"=? and ";
				values[index++]=where.get(key);
			}
			queryString += "1=1";
			int count = hibernateTemplate.bulkUpdate(queryString, values);
			if(count > 0){
				logger.debug("update["+entity+"] success, and execute count:"+count);
				 return true;
			 }else{
				 return false;
			 }
		}catch(DataAccessException e){
			logger.error("xxxx queryString["+queryString+"] execute failed.",e);
			e.printStackTrace();
			throw new CoreException("update entity["+entity+"] failed."+e);
		}
	}
	
	@Override
	public boolean deleteByProperty(String entity,String propertyName,Object propertyValue) throws CoreException {
		this.hibernateTemplate.setCacheQueries(true);
		String queryString = "delete from "+entity+" as model where model."+propertyName+"=?";
		try{
			 int count = hibernateTemplate.bulkUpdate(queryString, propertyValue);
			 if(count > 0){
				 logger.debug("delete success, and execute count:"+count);
				 return true;
			 }else{
				 return false;
			 }
		}catch(DataAccessException e){
			logger.error("xxxx queryString["+queryString+"] execute failed.",e);
			e.printStackTrace();
			throw new CoreException("delete entity["+entity+"] failed."+e);
		}
		
	}
	
	@Override
	public boolean delete(Object entity) throws CoreException {
		this.hibernateTemplate.setCacheQueries(true);
		try{
			hibernateTemplate.delete(entity);
			return true;
		}catch(DataAccessException e){
			logger.error("xxxx delete entity["+entity+"] failed.",e);
			e.printStackTrace();
			throw new CoreException("delete entity["+entity+"] failed."+e);
		}
	}

	@Override
	public <T> boolean deleteAll(Class<T> clazz) throws CoreException {
		this.hibernateTemplate.setCacheQueries(true);
		final String deleteString = "delete from "+clazz.getName();
		try{
			int count = (Integer) hibernateTemplate.execute(new HibernateCallback<Integer>() {
				@Override
				public Integer doInHibernate(Session session) throws HibernateException {
					session.beginTransaction();
					Query query = session.createQuery(deleteString);
					int result = query.executeUpdate();
					session.getTransaction().commit();
					return result;
				}
			});
			if(count > 0){
				return true;
			}else{
				return false;
			}
		}catch(DataAccessException e){
			logger.error("xxxx deleteString["+deleteString+"] execute failed.",e);
			e.printStackTrace();
			throw new CoreException("delete all["+clazz.getName()+"] failed."+e);
		}
	}
	
	@Override
	public <T> int bulkUpdate(String hql,Object... values) throws CoreException{
		this.hibernateTemplate.setCacheQueries(true);
		try{
			int count = hibernateTemplate.bulkUpdate(hql, values);
			hibernateTemplate.flush();
			hibernateTemplate.clear();
			logger.debug("execute hql["+hql+"] success, and execute count:"+count);
			return count;
		}catch(DataAccessException e){
			logger.error("xxxx execute hql["+hql+"] failed. ",e);
			e.printStackTrace();
			throw new CoreException("execute hql["+hql+"] failed."+e);
		}
	}
	
	@Resource(name="hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	/**
	 * @desc 获取hibernateTemplate模板对象
	 * @date 2014年12月11日 下午2:36:40
	 * @return
	 */
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	
	@Override
	public ResultSet find(String sql, Object... values) throws CoreException {
		throw new CoreException("please use jdbcDao implements.");
	}
	

	@Override
	public int update(String sql, Object... values) throws CoreException {
		throw new CoreException("please use jdbcDao implements.");
	}
	
}