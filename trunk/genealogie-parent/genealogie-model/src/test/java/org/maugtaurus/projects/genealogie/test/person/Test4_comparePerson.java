package org.maugtaurus.projects.genealogie.test.person;

import java.sql.Timestamp;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.maugtaurus.projects.genealogie.persistance.bo.person.PersonBo;
import org.maugtaurus.projects.genealogie.persistance.exception.PersonException;
import org.maugtaurus.projects.genealogie.persistance.model.event.Birth;
import org.maugtaurus.projects.genealogie.persistance.model.person.Person;
import org.maugtaurus.projects.genealogie.persistance.model.person.SexType;
import org.maugtaurus.projects.genealogie.test.common.CommonTestCase;

public class Test4_comparePerson extends CommonTestCase{

	@Test
	public void test() {
		try {
			getLog().debug("Test create a new person");
			
			PersonBo personBo = (PersonBo) getAppContext().getBean("personBo");

			personBo.save(createPerson1());
			personBo.save(createPerson1());
			
			Assert.fail("this is not the expected operating !");
		} catch (Exception ex) {
			
			Assert.assertTrue(ex.getMessage(), ex instanceof PersonException);
			getLog().error(ex.getMessage());
		}	
	}

	private Person createPerson1(){
		Person person = new Person();
		person.setName("Gerald Ford");
		person.setSexType(SexType.M);

		Birth birth = new Birth();
		DateTime birthDate = new DateTime(1944, 9, 26, 14, 30, 0, 0);
		birth.setDate(new Timestamp(birthDate.getMillis()));
		birth.setPlace("Dallas");
		birth.setSource("Wikipedia");

		person.setBirth(birth);

		return person;
	}
}
