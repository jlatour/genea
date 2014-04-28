package org.maugtaurus.projects.genealogie.persistance.model.event;

import java.io.Serializable;
import java.sql.Timestamp;

public abstract class Event implements Serializable{

	private static final long serialVersionUID = -1803655058099872557L;
	private long id;
	private Timestamp date;
	private String place;
	private String source;
	private String note;
	private EventType eventType;
	
	public Event() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
	
	@SuppressWarnings("rawtypes")
	public static Class getEventClassByType(EventType eventType){
		Class clazz = null;
		switch(eventType){
		case BIRT:
			clazz = Birth.class;
			break;
		case DEAT:
			clazz = Death.class;
			break;
		case CHR:
			clazz = Christening.class;
			break;
		case BURI:
			clazz = Burial.class;
			break;
		case MARR:
			clazz = Marriage.class;
			break;
		}
		return clazz;
	}
	
	@Override
	public boolean equals(Object obj) {
	   if (obj == null){
		      return false;
		   }

		   if (getClass() != obj.getClass()){
		      return false;
		   }

		   boolean dateCondition = false;

		   if (getDate() != null && ((Event)obj).getDate() != null){
			   dateCondition = getDate().equals(((Event)obj).getDate());
		   }
		   
		   return dateCondition;
	}
	
	@Override
	public String toString() {
		String toBeReturned = "Date: " + getDate();
		toBeReturned += ", Type: " + getEventType();
		toBeReturned += ", Place: " + getPlace();
		toBeReturned += ", Source: " + getSource();
		toBeReturned += ", Note: " + getNote();
		return toBeReturned;
	}
}
