package org.maugtaurus.projects.genealogie.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateParser {
	
	private static final Log log = LogFactory.getLog(DateParser.class);
	
	private static final String JULIAN = "@#DJULIAN@";
	private static final String ABT = "ABT";
	private static final String BEF = "BEF";
	private static final String AFT = "AFT";
	
	// year might contains 3 numbers (ex: 937 or 1970)
	private static final Pattern XX_XXX_XXXX_pattern = Pattern.compile("[1-9]{1,2} [A-Z]{3} [0-9]{3,4}");
	private static final Pattern XXXX = Pattern.compile("[0-9]{3,4}");

	
	public static DateTime parseDate(String dateFromGedCom){
		
		if(dateFromGedCom != null){
			// Remove julian from string
			log.debug("Parse date : " + dateFromGedCom);
			dateFromGedCom = dateFromGedCom.replaceAll(JULIAN, "");
			// Remove prefix abt, bef, aft from string
			dateFromGedCom = dateFromGedCom.replaceAll(ABT, "");
			dateFromGedCom = dateFromGedCom.replaceAll(BEF, "");
			dateFromGedCom = dateFromGedCom.replaceAll(AFT, "");
			dateFromGedCom = dateFromGedCom.replaceAll("\\s","");
			
			Matcher normalDateMatcher = XX_XXX_XXXX_pattern.matcher(dateFromGedCom);
			Matcher onlyYearMatcher = XXXX.matcher(dateFromGedCom);
			DateTime dateTime = null;
			if(normalDateMatcher.find()){
				dateTime = DateTime.parse(dateFromGedCom);
			}
			else if(onlyYearMatcher.find()){
				DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy");
				dateTime = dtf.parseDateTime(dateFromGedCom);
			}
			
			return dateTime;
		}
		
		return null;
	}
}
