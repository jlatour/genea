package org.maugtaurus.projects.genealogie.persistance.model.family;

import java.io.Serializable;
import java.util.Set;

import org.maugtaurus.projects.genealogie.persistance.model.event.Event;
import org.maugtaurus.projects.genealogie.persistance.model.person.Person;

public class Family implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1372171543089502457L;

	private int id;
//	private String label;
	private Set<Event> events;
	private Person father;
	private Person mother;
	private Set<Person> children;

	public Family() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
//
//	public String getLabel() {
//		return label;
//	}
//
//	public void setLabel(String label) {
//		this.label = label;
//	}

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
