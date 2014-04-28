package org.maugtaurus.projects.genealogie.persistance.dao.event.impl;

import org.maugtaurus.projects.genealogie.persistance.dao.event.EventDao;
import org.maugtaurus.projects.genealogie.persistance.model.event.Event;
import org.maugtaurus.projects.genealogie.persistance.util.CustomHibernateDaoSupport;

public class EventDaoImpl extends CustomHibernateDaoSupport implements EventDao {

	public void save(Event event) {
		getHibernateTemplate().saveOrUpdate(event);
	}

	public void delete(Event event) {
		getHibernateTemplate().delete(event);
	}

}
