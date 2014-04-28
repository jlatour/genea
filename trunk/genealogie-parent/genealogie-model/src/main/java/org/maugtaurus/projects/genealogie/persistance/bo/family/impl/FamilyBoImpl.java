package org.maugtaurus.projects.genealogie.persistance.bo.family.impl;

import java.util.ArrayList;
import java.util.List;

import org.maugtaurus.projects.genealogie.persistance.bo.family.FamilyBo;
import org.maugtaurus.projects.genealogie.persistance.dao.family.FamilyDao;
import org.maugtaurus.projects.genealogie.persistance.model.family.Family;
import org.maugtaurus.projects.genealogie.persistance.model.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("FamilyBo")
public class FamilyBoImpl implements FamilyBo {

	@Autowired
	FamilyDao familyDao;
	
	public void save(Family family) {
		// for each person, test if exists, then merge it
		List<Person> persons = new ArrayList<Person>();
		for(Person person : persons){
			
		}
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
