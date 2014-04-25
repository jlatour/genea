package org.maugtaurus.projects.genealogie.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.maugtaurus.projects.genealogie.persistance.exception.DateException;

public class DateParser {
	
	private static final Log log = LogFactory.getLog(DateParser.class);
	
	private static final String JULIAN = "@#DJULIAN@";
	private static final String ABT = "ABT";
	private static final String BEF = "BEF";
	private static final String AFT = "AFT";
	private static Map<String, String> months = null;
	
	static{
		months = new HashMap<String, String>();
		months.put("JAN", "01");
		months.put("FEB", "02");
		months.put("MAR", "03");
		months.put("APR", "04");
		months.put("MAY", "05");
		months.put("JUN", "06");
		months.put("JUL", "07");
		months.put("AUG", "08");
		months.put("SEP", "09");
		months.put("OCT", "10");
		months.put("NOV", "11");
		months.put("DEC", "12");
	}
	
	// year might contains 3 numbers (ex: 937 or 1970)
	private static final Pattern XX_XXX_XXXX_pattern = Pattern.compile("[0-9]{1,2} [A-Z]{3} [0-9]{3,4}");
	private static final Pattern XXX_XXXX_pattern = Pattern.compile("[A-Z]{3} [0-9]{3,4}");
	private static final Pattern XXXX = Pattern.compile("[0-9]{3,4}");

	private static final Pattern XXX = Pattern.compile("[a-zA-Z]{3}");
	
	private static final String XX_XXX_XXXX_dateFormat = "dd MM yyyy";
	private static final String XXXX_dateFormat = "yyyy";
	
	public static DateTime parseDate(String dateFromGedCom) throws Exception{
		
		if(dateFromGedCom != null){
			// Remove julian from string
			log.debug("Parse date : " + dateFromGedCom);
			dateFromGedCom = dateFromGedCom.replaceAll(JULIAN, "");
			// Remove prefix abt, bef, aft from string
			dateFromGedCom = dateFromGedCom.replaceAll(ABT, "");
			dateFromGedCom = dateFromGedCom.replaceAll(BEF, "");
			dateFromGedCom = dateFromGedCom.replaceAll(AFT, "");
			
			Matcher normalDateMatcher = XX_XXX_XXXX_pattern.matcher(dateFromGedCom);
			Matcher monthYearMatcher = XXX_XXXX_pattern.matcher(dateFromGedCom);
			Matcher onlyYearMatcher = XXXX.matcher(dateFromGedCom);
			
			DateTime dateTime = null;
			DateTimeFormatter dateTimeFormatter = null;
			
			if(normalDateMatcher.find()){;
				dateFromGedCom = correctDate(dateFromGedCom.trim());
				dateTimeFormatter = DateTimeFormat.forPattern(XX_XXX_XXXX_dateFormat);
			}
			else if(monthYearMatcher.find()){
				// Cut month part (FEB, JAN, etc.)
				dateFromGedCom = dateFromGedCom.replaceAll("\\s","");
				dateFromGedCom = dateFromGedCom.replaceAll("[a-zA-Z]{3}","");
				dateTimeFormatter = DateTimeFormat.forPattern(XXXX_dateFormat);	
			}
			else if(onlyYearMatcher.find()){
				dateFromGedCom = dateFromGedCom.replaceAll("\\s","");
				dateTimeFormatter = DateTimeFormat.forPattern(XXXX_dateFormat);	
			}
			
			try {
				
				dateTime = dateTimeFormatter.parseDateTime(dateFromGedCom);
			} catch (Exception ex) {
				if(ex instanceof IllegalArgumentException){
					throw new DateException(DateException.DATE_FORMAT_EXCEPTION + " " + dateFromGedCom);
				}
				throw ex;
			}
			
			log.debug("Parsed date : " + dateFromGedCom);
			return dateTime;
		}
		
		return null;
	}
	
	private static String correctDate(String date){
		String month = null;
		Matcher matcherMonth = XXX.matcher(date);
		
		if(matcherMonth.find()){
			month = matcherMonth.group();
			String newString = date.replaceAll(month, months.get(month));
			log.debug(date + " => " + newString);
			return newString;
		}
		return null;
	}
}
