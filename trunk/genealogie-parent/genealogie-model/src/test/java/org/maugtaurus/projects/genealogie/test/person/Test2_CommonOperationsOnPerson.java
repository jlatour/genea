package org.maugtaurus.projects.genealogie.test.person;

import java.sql.Timestamp;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.maugtaurus.projects.genealogie.persistance.bo.person.PersonBo;
import org.maugtaurus.projects.genealogie.persistance.model.event.Birth;
import org.maugtaurus.projects.genealogie.persistance.model.person.Person;
import org.maugtaurus.projects.genealogie.persistance.model.person.SexType;
import org.maugtaurus.projects.genealogie.test.common.CommonTestCase;

public class Test2_CommonOperationsOnPerson extends CommonTestCase{

	private final String EXPECTED_STRING = "Nom modifié";
	
	@Test
	public void test() {
		try {
			PersonBo personBo = (PersonBo) getAppContext().getBean("personBo");
			
			getLog().debug("Create person");
			Person createdPerson = createPerson("Valentin Valentinov", SexType.M, new DateTime(1998, 1, 10, 4, 57, 0, 0));
			personBo.save(createdPerson);
			
			getLog().debug("Update person");
			Person updatedPerson = personBo.getPersonById(1);
			
			updatedPerson.setName(EXPECTED_STRING);
			personBo.save(updatedPerson);
			Assert.assertEquals("The name has changed", EXPECTED_STRING, updatedPerson.getName());
	
			getLog().debug("Delete person");
			Person deletedPerson = personBo.getPersonById(1);
			personBo.delete(deletedPerson);
			
			Assert.assertTrue("Person has been deleted", personBo.getPersonById(1) == null);
			
		} catch (Exception ex) {
			
			Assert.fail(ex.getMessage());

		}	
	}
	
	private Person createPerson(String name, SexType sexType, DateTime dateTime){
		Person person = new Person();
		person.setName(name);
		person.setSexType(sexType);

		Birth birth = new Birth();
		DateTime birthDate = dateTime;
		birth.setDate(new Timestamp(birthDate.getMillis()));

		person.setBirth(birth);
		return person;
	}

}
