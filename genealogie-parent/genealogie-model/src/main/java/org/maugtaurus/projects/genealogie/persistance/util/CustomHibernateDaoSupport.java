package org.maugtaurus.projects.genealogie.persistance.util;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class CustomHibernateDaoSupport extends HibernateDaoSupport {

	@Autowired
	public void save(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
	
	@Autowired
	public void delete(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
}
