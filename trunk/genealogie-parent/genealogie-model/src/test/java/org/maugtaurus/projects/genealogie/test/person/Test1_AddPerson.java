package org.maugtaurus.projects.genealogie.test.person;

import java.sql.Timestamp;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.maugtaurus.projects.genealogie.persistance.bo.person.PersonBo;
import org.maugtaurus.projects.genealogie.persistance.model.event.Birth;
import org.maugtaurus.projects.genealogie.persistance.model.event.Death;
import org.maugtaurus.projects.genealogie.persistance.model.person.Person;
import org.maugtaurus.projects.genealogie.persistance.model.person.SexType;
import org.maugtaurus.projects.genealogie.test.common.CommonTestCase;

public class Test1_AddPerson extends CommonTestCase{

	@Test
	public void test() {
		try {
			getLog().debug("Test create a new person");
			
			PersonBo personBo = (PersonBo) getAppContext().getBean("personBo");
	
			Person person = new Person();
			person.setName("Louis XI");
			person.setSexType(SexType.M);

			Birth birth = new Birth();
			DateTime birthDate = new DateTime(1423, 7, 3, 14, 30, 0, 0);
			birth.setDate(new Timestamp(birthDate.getMillis()));
			birth.setPlace("Corps");
			birth.setSource("biographie");
			birth.setNote("Eût à souffrir de son physique ingrat toute sa vie");
	
			Death death = new Death();
			DateTime deathDate = new DateTime(1483, 8, 30, 14, 30, 0, 0);
			death.setDate(new Timestamp(deathDate.getMillis()));

			person.setBirth(birth);
			person.setDeath(death);
			personBo.save(person);

			getLog().debug(person);
			
		} catch (Exception ex) {
			
			Assert.fail();
			getLog().error(ex);
		}	
	}

}
