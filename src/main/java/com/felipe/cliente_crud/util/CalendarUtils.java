package com.felipe.cliente_crud.util;

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
		// Cria um objeto calendar com a data atual
		Calendar today = Calendar.getInstance();
		// Obtém a idade baseado no ano
		int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
		dateOfBirth.add(Calendar.YEAR, age);
		//se a data de hoje é antes da data de Nascimento, então diminui 1(um)
		if (today.before(dateOfBirth)) {
			age--;
		}
		return age;
	}

}
