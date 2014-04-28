package org.maugtaurus.projects.genealogie.persistance.dao.family.impl;

import org.maugtaurus.projects.genealogie.persistance.dao.family.FamilyDao;
import org.maugtaurus.projects.genealogie.persistance.model.family.Family;
import org.maugtaurus.projects.genealogie.persistance.util.CustomHibernateDaoSupport;

public class FamilyDaoImpl extends CustomHibernateDaoSupport implements FamilyDao {

	public void save(Family family) {
		getHibernateTemplate().saveOrUpdate(family);
	}

	public void delete(Family family) {
		getHibernateTemplate().delete(family);
	}

}
