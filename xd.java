import java.io.*;
import java.util.*;

public class xd{
  public static void main(String[] args) throws Exception{
    char listReglas[] = new char[3];
    listReglas[0]='W';
    listReglas[1]='X';
    listReglas[2]='Y';
    String instruccion = "cY";
    int cont = 0;
    boolean siono = false;
    while(cont < listReglas.length){
      char posRegla = listReglas[cont];// AVISARLE A BARRIOS :)
      System.out.println(posRegla+" = "+instruccion.charAt(instruccion.length()-1));
      if(instruccion.charAt(instruccion.length()-1) == posRegla){
        siono = true;
      }
      cont++;
    }
    System.out.println(siono);
  }
}

.


