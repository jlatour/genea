package org.maugtaurus.projects.genealogie.persistance.bo.event.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.maugtaurus.projects.genealogie.persistance.bo.event.EventBo;
import org.maugtaurus.projects.genealogie.persistance.dao.event.EventDao;
import org.maugtaurus.projects.genealogie.persistance.model.event.Event;
import org.maugtaurus.projects.genealogie.persistance.model.event.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("EventBo")
public class EventBoImpl implements EventBo {

	private static final Log log = LogFactory.getLog(EventBoImpl.class);

	@Autowired
	EventDao eventDao;
	
	public void save(Event event) {
		eventDao.save(event);
	}

	public void delete(Event event) {
		eventDao.delete(event);
	}

	public EventDao getEventDao() {
		return eventDao;
	}

	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}

	/**
	 * Test if the event type is the only one in the collection
	 *  
	 * @param events
	 * @param eventType
	 * @return
	 */
	public static boolean isEventUnique(Set<Event> events, EventType eventType){
		if(events != null){
			
			List<EventType> eventT = new ArrayList<EventType>();
			
			log.debug("event type wanted : " + eventType);
			
			for(Event event : events){
				
				if(event.getEventType().equals(eventType)){
					eventT.add(event.getEventType());
					log.debug("event type added : " + eventT);
				}
			}
			
			if(eventT.size() > 1){
				return false; 
			}
			
		}
		return true; 
		
	}
}
