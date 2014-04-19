package org.maugtaurus.projects.genealogie.persistance.bo.event;

import org.maugtaurus.projects.genealogie.persistance.model.event.Event;

public interface EventBo {
	void save(Event event);
	void delete(Event event);
}
