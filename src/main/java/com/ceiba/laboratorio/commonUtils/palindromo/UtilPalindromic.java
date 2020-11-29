package com.ceiba.laboratorio.commonUtils.palindromo;

/**
 * Clase para saber si el ISBN es palindromo
 * @author andres
 *
 */
public class UtilPalindromic {

	/**
	 * Logica de detección de palindromos
	 * @param palabra
	 * @return
	 */
    public static boolean palindromo(String palabra){
        for(int i =0; i< palabra.length(); i++){
            if(palabra.charAt(i)!= palabra.charAt(palabra.length() -i -1)){
              return false;
            }
        }
        return true;
      }

    /**
     * Metodo principal para saber si es palindromo
     * @param palabra
     * @return
     */
    public static boolean esPalindromo(String palabra){
        return palindromo(palabra);
    }

}
