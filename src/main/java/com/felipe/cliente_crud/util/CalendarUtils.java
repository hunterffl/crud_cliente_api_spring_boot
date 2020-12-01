package com.felipe.cliente_crud.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * @author Felipe Lemos
 */
public class CalendarUtils {
	
	private CalendarUtils() {
	    throw new IllegalStateException("Utility class");
	}
	
	public static int calculaIdade(Date dataNascimento){
		Calendar dateOfBirth = new GregorianCalendar();
		dateOfBirth.setTime(dataNascimento);
		Calendar today = Calendar.getInstance();
		int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
		dateOfBirth.add(Calendar.YEAR, age);
		if (today.before(dateOfBirth)) {
			age--;
		}
		return age;
	}

	public static boolean dataIsValid(Date date) {
		try {
			String dataStr = date.toString();
			if (StringUtils.isNullOrEmpty(dataStr)) {
				return false;
			}
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		    LocalDate.parse(dataStr, formatter);
		    return true;
		} catch (DateTimeParseException e) {
			return false;
		}   
	}
}
