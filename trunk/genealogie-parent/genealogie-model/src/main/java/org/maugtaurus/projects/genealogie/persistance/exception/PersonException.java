package org.maugtaurus.projects.genealogie.persistance.exception;

public class PersonException extends Exception {

	private static final long serialVersionUID = -1414090048617379563L;

	public static final String DUPLICATE_PERSON_EXCEPTION = "Person already exists !";

	public PersonException(String msg) {
		super(msg);
	}

}
