package org.maugtaurus.projects.genealogie.persistance.bo.person.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.maugtaurus.projects.genealogie.persistance.bo.event.impl.EventBoImpl;
import org.maugtaurus.projects.genealogie.persistance.bo.person.PersonBo;
import org.maugtaurus.projects.genealogie.persistance.dao.person.PersonDao;
import org.maugtaurus.projects.genealogie.persistance.exception.EventException;
import org.maugtaurus.projects.genealogie.persistance.model.event.EventType;
import org.maugtaurus.projects.genealogie.persistance.model.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("PersonBo")
public class PersonBoImpl implements PersonBo {

	private static final Log log = LogFactory.getLog(PersonBoImpl.class);

	@Autowired
	PersonDao personDao;

	public void save(Person person) throws Exception {
		try {
			testForUniquenessEvent(person);
			personDao.save(person);
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	/**
	 * Test for the uniqueness of the most important event
	 * @param person
	 * @throws Exception
	 */
	public void testForUniquenessEvent(Person person) throws Exception{
		boolean testForBIRT = EventBoImpl.isEventUnique(person.getEvents(), EventType.BIRT);
		boolean testForBURI = EventBoImpl.isEventUnique(person.getEvents(), EventType.BURI);
		boolean testForDEAT = EventBoImpl.isEventUnique(person.getEvents(), EventType.DEAT);
		boolean testForCHR = EventBoImpl.isEventUnique(person.getEvents(), EventType.CHR);

		log.debug("Test for the uniqueness of event BIRT : " + testForBIRT);
		log.debug("Test for the uniqueness of event BURI : " + testForBURI);
		log.debug("Test for the uniqueness of event DEAT : " + testForDEAT);
		log.debug("Test for the uniqueness of event CHR  : " + testForCHR);

		if (!testForBIRT) {
			throw new EventException(EventException.UNIQUE_EVENT_BIRT_EXCEPTION);
		}
		if (!testForBURI) {
			throw new EventException(EventException.UNIQUE_EVENT_BURI_EXCEPTION);
		}
		if (!testForDEAT) {
			throw new EventException(EventException.UNIQUE_EVENT_DEAT_EXCEPTION);
		}
		if (!testForCHR) {
			throw new EventException(EventException.UNIQUE_EVENT_CHR_EXCEPTION);
		}
	}
	
	public void delete(Person person) throws Exception {
		try {
			personDao.delete(person);
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	public Person getPersonById(long id) {
		Person person = null;
		if(id > 0){
			person = personDao.getPersonById(id);
		}
		return person;
	}

	public PersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

}
