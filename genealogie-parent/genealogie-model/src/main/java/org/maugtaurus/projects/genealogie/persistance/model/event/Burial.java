package org.maugtaurus.projects.genealogie.persistance.model.event;

public class Burial extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8362041349257163811L;

	public Burial() {
		setEventType(EventType.BURI);
	}
}
