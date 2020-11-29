package com.ceiba.laboratorio.commonUtils.prestamos;

/**
 * Clase que quita la letra inicial y la letra final de la palabra y suma sus numeros enteros
 */
public class UtilsFechaEntrega {

    /**
     *  si tiene mas de 30 retorna verdadero
     */
	private boolean sumaNumerosPalabra(String palabra){
        int i= 0;
        int suma=0;
        while(i<palabra.length()) {
        	suma+=Character.getNumericValue(palabra.charAt(i));
        	i++;
        }
        return suma>30?true:false;
    }

	/**
	 * metodo principal para saber en que condiciones se realiza el prestamo
	 * @param palabra
	 * @return
	 */
    public boolean prestamo(String palabra){
    	int i=1;
    	palabra=palabra.trim();
    	String frase="";
    	while(i<palabra.length()-1) {
    		frase+=palabra.charAt(i);
    		i++;
    	}
        return sumaNumerosPalabra(frase);
    }
}
