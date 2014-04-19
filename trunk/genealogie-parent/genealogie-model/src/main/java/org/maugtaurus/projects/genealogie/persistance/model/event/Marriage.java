package org.maugtaurus.projects.genealogie.persistance.model.event;

public class Marriage extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2752587139855512261L;

	public Marriage() {
		setEventType(EventType.MARR);
	}
}
