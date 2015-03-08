package org.rency.utils.dao;

import org.junit.Before;
import org.junit.Test;
import org.rency.utils.dao.hibernate.HibernateDao;
import org.rency.utils.dao.jdbc.JdbcDao;
import org.rency.utils.dao.spring.SpringDao;
import org.rency.utils.exceptions.CoreException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class DaoTest {
	
	private ApplicationContext ctx;

	@Before
	public void before(){
		 ctx = new FileSystemXmlApplicationContext("src/test/resources/applicationContext.xml");
	}
	
	@Test
	public void test() throws CoreException {
		System.out.println("HibernateDao="+ctx.getBean(HibernateDao.class));
		System.out.println("JdbcDao="+ctx.getBean(JdbcDao.class));
		System.out.println("SpringDao="+ctx.getBean(SpringDao.class));
	}

}
