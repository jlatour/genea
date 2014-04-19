package org.maugtaurus.projects.genealogie.apps;

import org.maugtaurus.projects.genealogie.persistance.bo.person.PersonBo;
import org.maugtaurus.projects.genealogie.persistance.model.person.Person;
import org.maugtaurus.projects.genealogie.persistance.model.person.SexType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Run {

	public static void main(String[] args) throws Exception {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("application-context.xml");

		PersonBo personBo = (PersonBo) appContext.getBean("personBo");
		
		 Person person = new Person();
		 person.setName("kaka");
		 person.setSexType(SexType.F);
		 personBo.save(person);

		System.out.println(personBo);
	}

}