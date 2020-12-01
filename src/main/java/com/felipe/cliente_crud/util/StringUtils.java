package com.felipe.cliente_crud.util;

/**
 * 
 * @author Felipe Lemos
 */
public class StringUtils {

	public static String retornarApenasNumeros(String texto) {
		return texto.trim().replaceAll("[^0-9]", "");
	}
	
	public static String retornarCpfFormatado(String cpf) {
		StringBuilder id = new StringBuilder(String.valueOf(cpf));
		int size = id.length();
		id.insert(size - 2, "-");
		id.insert(size - 5, ".");
		id.insert(size - 8, ".");
		return id.toString();
	}
	
	public static boolean isNullOrEmpty(String str){
		return str == null || str.isEmpty();
	}
	
	private StringUtils() {
	    throw new IllegalStateException("Utility class");
	}
}
