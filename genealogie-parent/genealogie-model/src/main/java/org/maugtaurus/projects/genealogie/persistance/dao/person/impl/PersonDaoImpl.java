package org.maugtaurus.projects.genealogie.persistance.dao.person.impl;

import java.util.List;

import org.maugtaurus.projects.genealogie.persistance.dao.person.PersonDao;
import org.maugtaurus.projects.genealogie.persistance.model.person.Person;
import org.maugtaurus.projects.genealogie.persistance.util.CustomHibernateDaoSupport;

public class PersonDaoImpl extends CustomHibernateDaoSupport implements PersonDao {

	private final static String QUERY_PERSON_BY_NAME = "from Person p where p.name = ?";
	
	public void save(Person person) throws Exception {
		try {
			Person testedPerson = getPersonIfAlreadyExists(person);
			if(testedPerson != null){
				// get this person and merge it
				testedPerson.fusionWith(person);
				getHibernateTemplate().update(testedPerson);
			}
			else{
				getHibernateTemplate().save(person);
			}
		} catch (Exception ex) {
			throw ex;
		}
	}

	public void delete(Person person) throws Exception {
		try {
			getHibernateTemplate().delete(person);
		} catch (Exception ex) {
			throw ex;
		}	
	}

	@SuppressWarnings("unchecked")
	public Person getPersonIfAlreadyExists(Person person){
		Person targetedPerson = null;
		List<Person> allPerson = getHibernateTemplate().find(QUERY_PERSON_BY_NAME, person.getName());
		if(allPerson != null && allPerson.size() > 0){
			targetedPerson = allPerson.get(0);
			if(targetedPerson.equals(person)){
				return targetedPerson;
			}
		}
		return person;
	}

	@Override
	public Person getPersonByNameAndAge(Person person) {
		
		return null;
	}

	@Override
	public Person getPersonById(long id) {
		Person person = (Person)getHibernateTemplate().get(Person.class, id);
		return person;
	}

}
