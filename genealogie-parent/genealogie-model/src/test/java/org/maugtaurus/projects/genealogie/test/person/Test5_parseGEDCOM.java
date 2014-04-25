package org.maugtaurus.projects.genealogie.test.person;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.gedcom4j.model.Individual;
import org.gedcom4j.parser.GedcomParser;
import org.gedcom4j.parser.GedcomParserException;
import org.junit.Test;
import org.maugtaurus.projects.genealogie.parser.PersonParser;
import org.maugtaurus.projects.genealogie.persistance.bo.person.PersonBo;
import org.maugtaurus.projects.genealogie.persistance.model.family.Family;
import org.maugtaurus.projects.genealogie.persistance.model.person.Person;
import org.maugtaurus.projects.genealogie.test.common.CommonTestCase;
import org.springframework.core.io.Resource;

public class Test5_parseGEDCOM extends CommonTestCase{

	@Test
	public void test() {
		PersonBo personBo = (PersonBo) getAppContext().getBean("personBo");
        // Load the gedcom file
        GedcomParser parser = new GedcomParser();
        try {
        	Resource resource = getAppContext().getResource("classpath:richer-fisson.ged");
        	parser.load((BufferedInputStream) resource.getInputStream());
        	
//            List<Person> persons = new ArrayList<Person>();
//            for (Individual indi : parser.gedcom.individuals.values()) {
//            	PersonParser personParser = new PersonParser(indi);
//            	System.out.println(personParser.getPerson());
//            	persons.add(personParser.getPerson());
//            }
//            
//            System.out.println(persons.size());
        	
        	List<Family> families = new ArrayList<Family>();
        	for (org.gedcom4j.model.Family family : parser.gedcom.families.values()) {
        		Family finalFamily = new Family();  
        		if(family.husband != null){
        			PersonParser father = new PersonParser(family.husband);
        			finalFamily.setFather(father.getPerson());
        			personBo.save(father.getPerson());
        		}
        		
        		if(family.wife != null){
        			PersonParser mother = new PersonParser(family.wife);
        			finalFamily.setMother(mother.getPerson());
        			personBo.save(mother.getPerson());
        		}
        		families.add(finalFamily);
        	}
          System.out.println(families.size());

        	
		} catch (IOException e) {
			e.printStackTrace();
		} catch (GedcomParserException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	

}
