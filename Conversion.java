import java.io.*;
import java.util.*;

public class Conversion {
  public static void main(String[] args) throws Exception{
    char[] listAlfabeto;
    String path = args[0];
  
    File documento = new File(path);
    Scanner archivo = new Scanner(documento);
    
    /* FORMATO EJEMPLO
      a,b,c
      6
      4
      0,1;2,2;1,3,4,5
      0,3,0,3,0,0
      0,0,5,0,0,5
      0,0,0,4,0,4
    */

  ////ALFABETO---------------------
    String linea = archivo.next();
    String alfabeto = linea.replace(",", "");
    listAlfabeto = new char[alfabeto.length()+1];
    listAlfabeto[0] = '0';
      int as = 0;
    for(int i = 1; i < listAlfabeto.length; i++){
      listAlfabeto[i] = alfabeto.charAt(as);
      as++;
    }
  
    System.out.println("Lista de alfabeto leída: " + Arrays.toString(listAlfabeto));
  ///ALFABETO--------------------
  
  //1. sacar alfabeto -->YA
  //2. sacar transiciones lambda de la 4ta línea en un método a parte
    //--->2.1 CASES ver si esas transiciones tienen los mismos elementos a otras para juntarlas en un mismo arreglo y si son diferentes ponerlas en un arreglo aparte en siguiente posición
    
  // hacer una matriz con matriz[alfabeto][lengthtransicioneslambda]
  // con ahora la matriz ir recorriendola y en otro método hacer el cambio desde la línea 5-7 para ver que hay en la posicion de la clausura con cada una de las letras
    String linea2 = archivo.next();
    int numEstadosN = Integer.parseInt(linea2);
    archivo.next();
    String linea4 = archivo.next();
  
    String[] transiciones = linea4.split(",");
    System.out.println("Transiciones lambda: " + Arrays.toString(transiciones));
    
    ArrayList<String> transicionLamb = new ArrayList<String>();
    
    for(int i = 0; i < transiciones.length; i++){
      transicionLamb.add(transiciones[i]);
    }

    for(int i = 0; i<transiciones.length; i++){
      if((transicionLamb.get(i).size() > 1) && (transicionLamb.get(i+1).size > 1)){
        if(transicionLamb.get(i).substring(0,1).equals(transicionLamb.get(i+1).substring)){
          1;2, 2;1,
        }
      }
    }
    
    //1. pasamos los elementos a la arraylist
    //2. recorremos la arraylist y con .get .size >1 en pos y pos+1 tons son transiciones con lambda
        //2.1 pa verificar que se referencian entre si y guardarla en una sola transición, se comparan los dos numeritos separados por ; 
    archivo.close();
  }
}