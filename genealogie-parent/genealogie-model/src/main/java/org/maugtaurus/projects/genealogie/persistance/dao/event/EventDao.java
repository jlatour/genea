package org.maugtaurus.projects.genealogie.persistance.dao.event;

import org.maugtaurus.projects.genealogie.persistance.model.event.Event;

public interface EventDao {
	void save(Event event);
	void delete(Event event);
}
