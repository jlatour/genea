package org.maugtaurus.projects.genealogie.test.event;

import java.sql.Timestamp;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.maugtaurus.projects.genealogie.persistance.bo.person.PersonBo;
import org.maugtaurus.projects.genealogie.persistance.exception.EventException;
import org.maugtaurus.projects.genealogie.persistance.model.event.Birth;
import org.maugtaurus.projects.genealogie.persistance.model.person.Person;
import org.maugtaurus.projects.genealogie.persistance.model.person.SexType;
import org.maugtaurus.projects.genealogie.test.common.CommonTestCase;

public class Test1_UniquenessEvent extends CommonTestCase{

	@Test
	public void test() {
		try {
			getLog().debug("Test for the uniqueness of an event (birth)");

			PersonBo personBo = (PersonBo) getAppContext().getBean("personBo");
			
			Person person = new Person();
			person.setName("Jérôme Latour");
			person.setSexType(SexType.M);

			Birth birth = new Birth();
			DateTime birthDate = new DateTime(1970, 7, 3, 14, 30, 0, 0);
			birth.setDate(new Timestamp(birthDate.getMillis()));
			birth.setPlace("Corps");
			birth.setSource("biographie");
			birth.setNote("Eût à souffrir de son physique ingrat toute sa vie");

			Birth birth2 = new Birth();
			DateTime birthDate2 = new DateTime(1971, 7, 3, 14, 30, 0, 0);
			birth2.setDate(new Timestamp(birthDate2.getMillis()));
			birth2.setPlace("Corps");
			birth2.setSource("biographie");
			birth2.setNote("Eût à souffrir de son physique ingrat toute sa vie");
			
			person.setBirth(birth);
			person.setBirth(birth2);
			
			personBo.save(person);

			Assert.fail("this is not the expected operating !");
			
		} catch (Exception ex) {
			
			Assert.assertTrue(ex instanceof EventException);
			getLog().debug(ex);
		}	
	}

}
