package org.maugtaurus.projects.genealogie.parser;

import java.util.List;

public class SimpleParser {

	public static <E> String parse(List<E> nodes){
		if(nodes != null && nodes.size() > 0){
			String concatedNodes = null;
			for(E abstractNode : nodes){
				concatedNodes += abstractNode.toString();
			}
			return concatedNodes;
		}
		return null;
		
	}
}
