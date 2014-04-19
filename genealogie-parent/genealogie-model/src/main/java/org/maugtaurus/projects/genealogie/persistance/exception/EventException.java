package org.maugtaurus.projects.genealogie.persistance.exception;

public class EventException extends Exception {

	private static final long serialVersionUID = -1414090048617379563L;

	public static final String UNIQUE_EVENT_BIRT_EXCEPTION = "There must be an unique event BIRT";
	public static final String UNIQUE_EVENT_BURI_EXCEPTION = "There must be an unique event BURI";
	public static final String UNIQUE_EVENT_DEAT_EXCEPTION = "There must be an unique event DEAT";
	public static final String UNIQUE_EVENT_CHR_EXCEPTION = "There must be an unique event CHR";

	public EventException(String msg) {
		super(msg);
	}

}
