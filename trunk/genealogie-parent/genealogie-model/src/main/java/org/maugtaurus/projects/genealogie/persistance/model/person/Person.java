package org.maugtaurus.projects.genealogie.persistance.model.person;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.maugtaurus.projects.genealogie.persistance.model.event.Birth;
import org.maugtaurus.projects.genealogie.persistance.model.event.Death;
import org.maugtaurus.projects.genealogie.persistance.model.event.Event;
import org.maugtaurus.projects.genealogie.persistance.model.event.EventType;

public class Person implements Serializable {

	private static final long serialVersionUID = -2655587384015022381L;

	private long id;
	private String name;
//	private String lastName;
	private SexType sexType;
	private Set<Event> events;

	public Person() {

	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public String getLastName() {
//		return lastName;
//	}
//
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}

	public SexType getSexType() {
		return sexType;
	}

	public void setSexType(SexType sexType) {
		this.sexType = sexType;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	public Set<Event> getEvents() {
		if (events == null) {
			events = new HashSet<Event>();
		}
		return events;
	}

	public void setDeath(Death death) {
		if (events == null) {
			events = new HashSet<Event>();
		}
		events.add(death);
	}

	public Death getDeath() {
		return getGenericEvent(EventType.DEAT);
	}

	public void setBirth(Birth birth) {
		if (events == null) {
			events = new HashSet<Event>();
		}
		events.add(birth);
	}

	public Birth getBirth() {
		return getGenericEvent(EventType.BIRT);
	}

	/**
	 * Get an event with its type
	 * 
	 * @param eventType
	 * @return Event
	 */
	@SuppressWarnings("unchecked")
	private <T extends Event> T getGenericEvent(EventType eventType) {
		Set<Event> events = getEventsByType(eventType);
		if (events != null && events.size() > 0) {
			Class<T> clazz = Event.getEventClassByType(eventType);
			return (T) clazz.cast(events.iterator().next());
		}
		return null;
	}

	private Set<Event> getEventsByType(EventType eventType) {
		Set<Event> allEvents = new HashSet<Event>();
		if (events != null) {
			for (Event event : events) {
				if (event.getEventType().equals(eventType)) {
					allEvents.add(event);
				}
			}
			return allEvents;
		}
		return null;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		boolean nameCondition = false;
		boolean birthCondition = false;

		if (name != null && ((Person) obj).name != null && getBirth() != null && ((Person) obj).getBirth() != null) {

			nameCondition = name.equalsIgnoreCase(((Person) obj).name);
			birthCondition = getBirth().equals(((Person) obj).getBirth());
		}

		return nameCondition && birthCondition;
	}

	@Override
	public String toString() {
		String toBeReturned = "Name : " + getName();
//		toBeReturned += ", Last name : " + getLastName();
		toBeReturned += ", Sex : " + getSexType();
		toBeReturned += ", Event(s) : " + System.getProperty("line.separator") + getEvents();
		return toBeReturned;
	}

}
