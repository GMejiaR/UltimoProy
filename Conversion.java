import java.io.*;
import java.util.*;
import java.lang.*;

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
    //--->2.1 CASES ver si esas transiciones tienen los mismos elementos a otras para juntarlas en un mismo arreglo y si son diferentes ponerlas en un arreglo aparte en siguiente posición --> YA
    
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
    //transicionLamb.add("");
    
    ArrayList<String> transicionAux = new ArrayList<String>();
    
    for(int i = 0; i<transicionLamb.size(); i++){
      if(((transicionLamb.get(i)).length() > 1) && ((transicionLamb.get(i+1)).length() > 1)){
        String actual = transicionLamb.get(i);
        actual = actual.replace(";", "");
        transicionLamb.set(i, actual);
        
        String posnext = transicionLamb.get(i+1);
        posnext = posnext.replace(";", "");
        StringBuilder validacion = new StringBuilder();
        validacion.append(posnext);
        StringBuilder inverso = validacion.reverse();
        
        if(actual.equals(inverso.toString())){
          transicionLamb.remove(i+1);
          System.out.println("Transicion borrada: " + transicionLamb);
          //continue;
        }
      }
      
    }

    //Crear la matriz y llenarla de 0
    String[][] matrizPos = new String[transicionLamb.size()-1][listAlfabeto.length];
    for(int i = 0; i < transicionLamb.size()-1; i ++){
      for(int j= 0; j < listAlfabeto.length; j++){
        matrizPos[i][j] = "0";
        
      }
    }

    //Imprimir matriz
    for (int x=0; x < matrizPos.length; x++) {
      System.out.print("|");
      for (int y=0; y < matrizPos[x].length; y++) {
        System.out.print (matrizPos[x][y]);
        if (y!=matrizPos[x].length-1) System.out.print("\t");
      }
      System.out.println("|");
    }
    System.out.println();
    System.out.println();
    //sacar un while con la matriz donde según el size de la pos de la arraylist con la clausura lambda vaya tirandole x veces al método con cambio, ahora lo que me devuelve el método tiene que ser el string en la linea de la letra en la posición que le mande, y si eso es diferente de o lo voy a copiar en la pos del [arreglolambda][letraalfabetovalidando]
    

    //lenar la matriz
    for(int i = 0; i < transicionLamb.size()-1; i ++){
      for(int j = 1; j < listAlfabeto.length; j++){
        int cont = 0;
        //string --> length()
        //array --> length
        //arraylist-->size()
        String transicionL = transicionLamb.get(i+1);
        
        if(transicionL.length() > 1){
          ArrayList<String> doble = new ArrayList<String>();
          while(cont < 2){
            System.out.println("Contador: " + cont);
            System.out.println("Estado j: "+ j);
            char elemento = transicionL.charAt(cont);
            System.out.println("elemento lamba: " + elemento);
            String cambio = Cambio(path, elemento, j);
            
            int llenar = transicionLamb.indexOf(cambio);
            String finalPos = Integer.toString(llenar);
            doble.add(finalPos);
            System.out.println("donde está el elemento del cambio: "+ llenar);
              
            cont++;
          }
          for(int k = 0; k < doble.size(); k++){
            if(doble.get(k) != "0"){
              matrizPos[i][j]= doble.get(k);
            }
            
          }

          
        }
        else{
            char elementotrans = transicionL.charAt(0);
            //System.out.println("elemento lamba: " + elemento);
            String cambio = Cambio(path, elementotrans, j);
            
            for(int k = 1; k<transicionLamb.size();k++){
              if(transicionLamb.get(k).length()>1){
                  char estado = (transicionLamb.get(k)).charAt(0);
                  char estado2 =(transicionLamb.get(k)).charAt(1);
                  if((Character.toString(estado)).startsWith(cambio) | (Character.toString(estado)).startsWith(cambio)){
                    matrizPos[i][j] = Integer.toString(k);
                  }
                
              }else{
                char estado =(transicionLamb.get(k)).charAt(0);
                if((Character.toString(estado)).startsWith(cambio)){
                  matrizPos[i][j] = Integer.toString(k);
                }
              }
            }
        }
      }
    }
// aki aki aki
  for (int x=0; x < matrizPos.length; x++) {
        System.out.print("|");
      for (int y=0; y < matrizPos[x].length; y++) {
        System.out.print (matrizPos[x][y]);
        if (y!=matrizPos[x].length-1) System.out.print("\t");
      }
      System.out.println("|");
    }
  
    archivo.close();
  }
  
  public static String Cambio(String path, char numerolamda, int letra) throws Exception{
    File documento = new File(path);
    Scanner archivo = new Scanner(documento);
    archivo.next();
    archivo.next();
    archivo.next();
    archivo.next();
    int cont = 0;
    String lineaLetra = "";
    
    while(cont < letra){
       lineaLetra = archivo.next();
      cont++;
    }
    
    String[] transiciones = lineaLetra.split(",");
    int posicionlamda = Character.getNumericValue(numerolamda);
    String reemplazo = transiciones[posicionlamda];
    
    archivo.close();
    return reemplazo;
  }
  //next x4
  //dependiendo de la #letra hacemos cantidad next
  //en el string de la linea que hayamos llegado nos vamos a la posicion numero -- 2
  //regresamos lo que está en esa posición  ->0
  
}