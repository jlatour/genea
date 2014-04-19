package org.maugtaurus.projects.genealogie.test.family;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.maugtaurus.projects.genealogie.persistance.bo.family.FamilyBo;
import org.maugtaurus.projects.genealogie.persistance.bo.person.PersonBo;
import org.maugtaurus.projects.genealogie.persistance.model.event.Birth;
import org.maugtaurus.projects.genealogie.persistance.model.event.Death;
import org.maugtaurus.projects.genealogie.persistance.model.family.Family;
import org.maugtaurus.projects.genealogie.persistance.model.person.Person;
import org.maugtaurus.projects.genealogie.persistance.model.person.SexType;
import org.maugtaurus.projects.genealogie.test.common.CommonTestCase;

public class Test1_AddFamily extends CommonTestCase{

	@Test
	public void test() {
		try {
			getLog().debug("Test create a new family");
			
			PersonBo personBo = (PersonBo) getAppContext().getBean("personBo");
			FamilyBo familyBo = (FamilyBo) getAppContext().getBean("familyBo");
			Person father = createFather();
			Person mother = createMother();
			
			Person child = createChild();
			Set<Person> children = new HashSet<Person>();
			children.add(child);
			
			personBo.save(father);
			personBo.save(mother);
			personBo.save(child);

			
			Family family = new Family();
			family.setLabel("Famille Latour");
			family.setFather(father);
			family.setMother(mother);
			family.setChildren(children);
			familyBo.save(family);

		} catch (Exception ex) {
			
			Assert.fail();
			getLog().error(ex);
		}	
	}
	
	private Person createFather(){
		Person person = new Person();
		person.setName("Père");
		person.setSexType(SexType.M);

		Birth birth = new Birth();
		DateTime birthDate = new DateTime(1970, 9, 26, 14, 30, 0, 0);
		birth.setDate(new Timestamp(birthDate.getMillis()));
		birth.setPlace("Argentan");
		birth.setSource("biographie");
		birth.setNote("Une note");

		Death death = new Death();
		DateTime deathDate = new DateTime(2070, 8, 30, 14, 30, 0, 0);
		death.setDate(new Timestamp(deathDate.getMillis()));

		person.setBirth(birth);
		person.setDeath(death);
		return person;
	}

	private Person createMother(){
		Person person = new Person();
		person.setName("Mère");
		person.setSexType(SexType.F);

		Birth birth = new Birth();
		DateTime birthDate = new DateTime(1969, 9, 18, 14, 30, 0, 0);
		birth.setDate(new Timestamp(birthDate.getMillis()));
		birth.setPlace("Corps");
		birth.setSource("biographie");
		birth.setNote("Une autre note");

		Death death = new Death();
		DateTime deathDate = new DateTime(2035, 8, 30, 14, 30, 0, 0);
		death.setDate(new Timestamp(deathDate.getMillis()));

		person.setBirth(birth);
		person.setDeath(death);
		return person;
	}
	
	private Person createChild(){
		Person person = new Person();
		person.setName("Alexia Latour");
		person.setSexType(SexType.F);

		Birth birth = new Birth();
		DateTime birthDate = new DateTime(1998, 1, 10, 4, 57, 0, 0);
		birth.setDate(new Timestamp(birthDate.getMillis()));
		birth.setPlace("Vire");
		birth.setSource("biographie");
		birth.setNote("Pas de note");

		person.setBirth(birth);
		return person;
	}
}
