package com.ceiba.laboratorio.commonUtils.prestamos;

/**
 * Clase que quita la letra inicial y la letra final de la palabra y suma sus
 * numeros enteros
 */
public class UtilsFechaEntrega {

	/**
	 * si tiene mas de 30 retorna verdadero
	 */
	private static boolean sumaNumerosPalabra(String palabra) {
		int suma = 0;

		for (int i = 0; i < palabra.length(); i++) {
			int number = Integer.valueOf(palabra.substring(i, i + 1)).intValue();
			suma += number;
		}

		return suma > 30 ? true : false;
	}

	/**
	 * metodo principal para saber en que condiciones se realiza el prestamo
	 * 
	 * @param palabra
	 * @return
	 */
	public static boolean prestamo(String palabra) {

		palabra = palabra.replaceAll("[^\\d]", "");

		return sumaNumerosPalabra(palabra);
	}

	public static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
