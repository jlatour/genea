package org.maugtaurus.projects.genealogie.persistance.model.event;

public class Birth extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6976492879433768089L;

	public Birth() {
		setEventType(EventType.BIRT);
	}
}
