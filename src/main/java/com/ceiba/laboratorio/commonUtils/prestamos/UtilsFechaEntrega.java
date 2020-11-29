package com.ceiba.laboratorio.commonUtils.prestamos;

/**
 * Clase que quita la letra inicial y la letra final de la palabra y suma sus numeros enteros
 */
public class UtilsFechaEntrega {

    /**
     *  si tiene mas de 30 retorna verdadero
     */
    private boolean sumaNumerosPalabra(String isbn){
        int i= 0;
        int suma=0;
        while(i<isbn.length()) {
        	suma=+Character.getNumericValue(isbn.charAt(i));
        	i++;
        }
        return suma>30?true:false;
    }

    public boolean prestamo(String palabra){
        return sumaNumerosPalabra(palabra.trim());
    }
}
