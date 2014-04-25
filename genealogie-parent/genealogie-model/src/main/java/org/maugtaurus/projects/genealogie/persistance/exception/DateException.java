package org.maugtaurus.projects.genealogie.persistance.exception;

public class DateException extends Exception {

	private static final long serialVersionUID = -1414090048617379563L;

	public static final String DATE_FORMAT_EXCEPTION = "Date format is not legal";

	public DateException(String msg) {
		super(msg);
	}

}
