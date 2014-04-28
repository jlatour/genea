package org.maugtaurus.projects.genealogie.test.person;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.gedcom4j.model.Individual;
import org.gedcom4j.parser.GedcomParser;
import org.gedcom4j.parser.GedcomParserException;
import org.junit.Test;
import org.maugtaurus.projects.genealogie.parser.PersonParser;
import org.maugtaurus.projects.genealogie.persistance.bo.family.FamilyBo;
import org.maugtaurus.projects.genealogie.persistance.bo.person.PersonBo;
import org.maugtaurus.projects.genealogie.persistance.model.family.Family;
import org.maugtaurus.projects.genealogie.persistance.model.person.Person;
import org.maugtaurus.projects.genealogie.test.common.CommonTestCase;
import org.springframework.core.io.Resource;

public class Test5_parseGEDCOM extends CommonTestCase {

	@Test
	public void test() {
//		PersonBo personBo = (PersonBo) getAppContext().getBean("personBo");
		FamilyBo familyBo = (FamilyBo) getAppContext().getBean("familyBo");
		// Load the gedcom file
		GedcomParser parser = new GedcomParser();

		Set<Person> persons = null;
		Family finalFamily = null;

		try {
			Resource resource = getAppContext().getResource("classpath:richer-fisson.ged");
			parser.load((BufferedInputStream) resource.getInputStream());

			for (org.gedcom4j.model.Family family : parser.gedcom.families.values()) {
				finalFamily = new Family();
				if (family.husband != null) {
					PersonParser father = new PersonParser(family.husband);
					finalFamily.setFather(father.getPerson());
//					personBo.save(father.getPerson());
				}

				if (family.wife != null) {
					PersonParser mother = new PersonParser(family.wife);
					finalFamily.setMother(mother.getPerson());
//					personBo.save(mother.getPerson());
				}

				if (family.children != null) {
					persons = new HashSet<Person>();
					for (Individual indi : family.children) {
						PersonParser personParser = new PersonParser(indi);
						persons.add(personParser.getPerson());
					}
					finalFamily.setChildren(persons);
				}

				familyBo.save(finalFamily);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (GedcomParserException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
