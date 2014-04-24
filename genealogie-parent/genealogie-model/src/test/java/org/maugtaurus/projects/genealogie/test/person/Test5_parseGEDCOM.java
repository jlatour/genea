package org.maugtaurus.projects.genealogie.test.person;

import java.io.BufferedInputStream;
import java.io.IOException;

import org.gedcom4j.model.Family;
import org.gedcom4j.model.Individual;
import org.gedcom4j.parser.GedcomParser;
import org.gedcom4j.parser.GedcomParserException;
import org.junit.Test;
import org.maugtaurus.projects.genealogie.parser.PersonParser;
import org.maugtaurus.projects.genealogie.test.common.CommonTestCase;
import org.springframework.core.io.Resource;

public class Test5_parseGEDCOM extends CommonTestCase{

	@Test
	public void test() {
        // Load the gedcom file
        GedcomParser parser = new GedcomParser();
        try {
        	Resource resource = getAppContext().getResource("classpath:richer-fisson.ged");
//        	Test5_parseGEDCOM.class.getResourceAsStream("richer-fisson.ged");
        	parser.load((BufferedInputStream) resource.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GedcomParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
        // Stop if there were parser errors
        if (!parser.errors.isEmpty()) {
            for (String s : parser.errors) {
                System.err.println(s);
            }
//            return;
        }

//        Submitter submitter = parser.gedcom.submitters.values().iterator().next();
        for (Individual indi : parser.gedcom.individuals.values()) {
        	PersonParser personParser = new PersonParser(indi);
        }
        
//        System.out.println(parser.gedcom.individuals.size());
//        
//        for (Family parserFamily : parser.gedcom.families.values()) {
//        	
//        	System.out.println(parserFamily.husband);
//        	System.out.println(parserFamily.wife);
//        	System.out.println("-----------------");
//        	org.maugtaurus.projects.genealogie.persistance.model.family.Family family = new org.maugtaurus.projects.genealogie.persistance.model.family.Family();
//        	family.setLabel(parserFamily.husband.formattedName());
        	
        	
//        }
	}

	

}
