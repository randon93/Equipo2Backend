package com.ceiba.laboratorio.commonUtils.palindromo;

public class UtilPalindromic {

    private boolean palindromo(String palabra){
      for(int i =0; i< palabra.length(); i++){
          if(palabra.charAt(i)!= palabra.charAt(palabra.length() -i -1)){
            return false;
          }
      }
      return true;
    }

    public boolean esPalindromo(String palabra){
        return palindromo(palabra.trim());
    }
}
