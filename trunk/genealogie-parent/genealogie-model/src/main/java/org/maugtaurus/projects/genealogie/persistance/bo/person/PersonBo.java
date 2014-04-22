package org.maugtaurus.projects.genealogie.persistance.bo.person;

import org.maugtaurus.projects.genealogie.persistance.model.person.Person;

public interface PersonBo {
	void save(Person person) throws Exception;
	void delete(Person person) throws Exception;
	Person getPersonById(long id);
}
