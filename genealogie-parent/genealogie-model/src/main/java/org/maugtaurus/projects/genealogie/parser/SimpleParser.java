package org.maugtaurus.projects.genealogie.parser;

import java.util.List;

import org.gedcom4j.model.PersonalName;
/**
 * To flatten drastically collections from the parser gedcom4j
 * @author latour
 *
 */
public class SimpleParser {
	private static String NULL_REPRESENTATION = "-"; 
	public static <E> String parse(List<E> nodes){
		if(nodes != null && nodes.size() > 0){
			String concatedNodes = null;
			for(E abstractNode : nodes){
				if(abstractNode != null){
					if(abstractNode.getClass().equals(PersonalName.class)){
						PersonalName personalName = PersonalName.class.cast(abstractNode);
						String givenName = personalName.givenName != null ? personalName.givenName.value:NULL_REPRESENTATION;
						String surName =  personalName.surname != null ? personalName.surname.value:NULL_REPRESENTATION;
						concatedNodes = givenName + " " + surName;
					}
					else{
						concatedNodes += abstractNode.toString();	
					}
					
				}
			}
			return concatedNodes;
		}
		return null;
		
	}
}
