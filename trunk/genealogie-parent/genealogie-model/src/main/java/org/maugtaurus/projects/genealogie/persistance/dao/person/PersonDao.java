package org.maugtaurus.projects.genealogie.persistance.dao.person;

import org.maugtaurus.projects.genealogie.persistance.model.person.Person;

public interface PersonDao {
	void save(Person person) throws Exception;
	void delete(Person person) throws Exception;
	Person getPersonById(long id);
	Person getPersonByNameAndAge(Person person);
}
