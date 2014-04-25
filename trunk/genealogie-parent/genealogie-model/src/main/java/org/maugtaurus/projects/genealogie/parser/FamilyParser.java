package org.maugtaurus.projects.genealogie.parser;

import java.util.Set;

import org.maugtaurus.projects.genealogie.persistance.model.event.Event;
import org.maugtaurus.projects.genealogie.persistance.model.person.Person;

public class FamilyParser {
	private String label;
	private Set<Event> events;
	private Person father;
	private Person mother;
	private Set<Person> children;
	
	public FamilyParser(Person husband, Person wife, Set<Person> children) {
		if(husband != null){
			father = husband;
		}
		if(wife != null){
			mother = wife;
		}
		if(children != null){
			this.children = children;
		}
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Set<Event> getEvents() {
		return events;
	}
	public void setEvents(Set<Event> events) {
		this.events = events;
	}
	public Person getFather() {
		return father;
	}
	public void setFather(Person father) {
		this.father = father;
	}
	public Person getMother() {
		return mother;
	}
	public void setMother(Person mother) {
		this.mother = mother;
	}
	public Set<Person> getChildren() {
		return children;
	}
	public void setChildren(Set<Person> children) {
		this.children = children;
	}
	
}
