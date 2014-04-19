package org.maugtaurus.projects.genealogie.persistance.bo.family.impl;

import org.maugtaurus.projects.genealogie.persistance.bo.family.FamilyBo;
import org.maugtaurus.projects.genealogie.persistance.dao.family.FamilyDao;
import org.maugtaurus.projects.genealogie.persistance.model.family.Family;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("FamilyBo")
public class FamilyBoImpl implements FamilyBo {

	@Autowired
	FamilyDao familyDao;
	
	public void save(Family family) {
		familyDao.save(family);
	}

	public void delete(Family family) {
		familyDao.delete(family);
	}

	public FamilyDao getFamilyDao() {
		return familyDao;
	}

	public void setFamilyDao(FamilyDao familyDao) {
		this.familyDao = familyDao;
	}

}
