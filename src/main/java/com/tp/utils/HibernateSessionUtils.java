package com.tp.utils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class HibernateSessionUtils extends HibernateDaoSupport {
	@Autowired
	public void initialize(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
}
