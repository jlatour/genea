package org.maugtaurus.projects.genealogie.parser;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.gedcom4j.model.Individual;
import org.gedcom4j.model.IndividualEvent;
import org.joda.time.DateTime;
import org.maugtaurus.projects.genealogie.persistance.model.event.Birth;
import org.maugtaurus.projects.genealogie.persistance.model.event.Burial;
import org.maugtaurus.projects.genealogie.persistance.model.event.Christening;
import org.maugtaurus.projects.genealogie.persistance.model.event.Death;
import org.maugtaurus.projects.genealogie.persistance.model.event.Event;
import org.maugtaurus.projects.genealogie.persistance.model.person.SexType;

public class PersonParser {

	private final static String NO_NAME = "No name available";
	private final static String MALE = "m";
	private final static String FEMALE = "f";
	
	private String name;
	private SexType sexType;
	private Set<Event> events;

	public PersonParser(Individual indi) {
		if(indi != null){
			
			// Name part
			if(indi.names != null && indi.names.size() > 0){
				name = SimpleParser.parse(indi.names);
			}
			else{
				name = NO_NAME;
			}
			
			// Sex part
			if(indi.sex != null && indi.sex.value.equalsIgnoreCase(MALE)){
				sexType = SexType.MALE;
			}
			else if(indi.sex != null && indi.sex.value.equalsIgnoreCase(FEMALE)){
				sexType = SexType.FEMALE;
			}
			
			// Events part
			events = new HashSet<Event>();
			if(indi.events != null){
				for(IndividualEvent individualEvent : indi.events){
					Event event = null;
					DateTime dateTime = DateParser.parseDate(individualEvent.date.value);

					switch(individualEvent.type){
					case BIRTH:
						event = new Birth();
					case DEATH:
						event = new Death();
					case BURIAL:
						event = new Burial();
					case CHRISTENING:
						event = new Christening();
					}
					
					event.setDate(new Timestamp(dateTime.getMillis()));
					event.setNote(SimpleParser.parse(indi.notes));
					events.add(event);
				}
				
			}
			
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SexType getSexType() {
		return sexType;
	}

	public void setSexType(SexType sexType) {
		this.sexType = sexType;
	}

	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

}
