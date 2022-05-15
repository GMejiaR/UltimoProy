import java.io.*;
import java.util.*;

public class Prueba{
  public static char[] listReglas;
  public static char[] listAlfabeto;
  public static int[][] matrizafd;
  
  public static void main(String[] args) throws Exception{

  
  String path = args[0];
  
  File documento = new File(path);
  Scanner archivo = new Scanner(documento);  

  String linea = archivo.next();
  String reglas = linea.replace(",", "");
  listReglas = new char[reglas.length()];
  
  for(int i = 0; i < listReglas.length; i++){
    listReglas[i] = reglas.charAt(i);
  }  
  System.out.println("Lista de reglas: " + Arrays.toString(listReglas));
  //---------------------------------------

  // ------------ Alfabeto ------------
  String linea2 = archivo.next();
  String alfabeto = linea2.replace(",", "");
  listAlfabeto = new char[alfabeto.length()];

  for(int i = 0; i < listAlfabeto.length; i++){
    listAlfabeto[i] = alfabeto.charAt(i);
  }

  System.out.println("Lista de alfabeto leída: " + Arrays.toString(listAlfabeto));
  // ----------------------------------

  // ------------ Símbolo Inicial ------------
  String linea3 = archivo.next();
  char SimbInicial = linea3.charAt(0);
  System.out.println("Símbolo inicial: " + SimbInicial);

    int pollo = cantidadEstados(path);
  //-------------------------------------------

     // --------- Leer Archivo y Algoritmo ---------
    int estact = 1;
    int cont = 0;////////CONTADOR---------------------!!!!!!!!!!!!!!!!!!! :D
    ArrayList<Character> patron = reglas(path);
    patron.add(' ');
    while(archivo.hasNextLine()){
      String actual = archivo.next();
        // ------------- Validar Regla de Producción -------------  
      if (patron.get(cont) == patron.get(cont+1)){

        
      }else{
        String instruccion = actual.substring(2,actual.length());
        if(verificarRegla(instruccion)){
          if(verificarRetornable(instruccion,actual.substring(0,1))){
            // estamos men lo de retornar a si mismo cuando X = X del final en ese sentido faltan los demas
          }
        }
        
     
      }
      
    }
    archivo.close();
    }

  
    ////AQUI
   public static int cantidadEstados(String Path) throws Exception{
    File doc = new File(Path);
    Scanner archivo2 = new Scanner(doc);
    archivo2.next();
    String estados = archivo2.next();
    int cantest = (estados.replace(",","")).length();
    archivo2.next();

    int cont = 0;
    int estadoss = 2;
    ArrayList<Character> patron = reglas(Path);
    patron.add(' ');
    patron.add(' ');
    while(archivo2.hasNext()){
      String actual = archivo2.next();
      if (patron.get(cont) == patron.get(cont+1)){
        ArrayList<String> repetidos = new ArrayList<String>();
        repetidos.add(actual);
        while(true){
          if(archivo2.hasNext()){
            if (patron.get(cont) == patron.get(cont+1)){
              actual = archivo2.next();
              repetidos.add(actual);
              cont++;
            }else{
              break;
            }
          }else{
              break;
            }
          
        }
        int ver = 0;
        int tam = repetidos.size();
        while(ver < tam){
          String inst =(repetidos.get(ver)).substring(3,((repetidos.get(ver)).length())-1);
          if(verificarRegla(inst)){
            if(verificarRetornable(inst, actual.substring(0,1))){
              int cantmin = inst.length()-1;
              if(cantmin > 1){
                estadoss = estadoss + (cantmin-1);
                cont++;
              }
            }else{
              int cantmin = inst.length();
              if(cantmin > 1){
                estadoss = estadoss + (cantmin-1);
                cont++;
              }else if(cantmin == 0){
                estadoss++;
                cont++;
              }
            }
          }else{
            int cantmin = inst.length();
            if(cantmin > 1){
              estadoss = estadoss + (cantmin-1);
              cont++;
            }
          }
          ver++;
        }
          
      }else{
        String instruccion = actual.substring(3,actual.length());
        if(verificarRegla(instruccion)){
          if(verificarRetornable(instruccion, actual.substring(0,1))){
            int cantmin = instruccion.length()-1;
            if(cantmin > 1){
              System.out.println("instr "+instruccion + cantmin);
              estadoss = estadoss + (cantmin-1);
              cont++;
            }
          }else{
            int cantmin = instruccion.length();
            if(cantmin > 1){
              estadoss = estadoss + (cantmin-1);
              cont++;
            }else if(cantmin == 0){
              estadoss++;
              cont++;
            }
          }
        }else{
          int cantmin = instruccion.length();
          if(cantmin > 1){
            estadoss = estadoss + (cantmin-1);
            cont++;
          }
        }
      }
    }
    archivo2.close();
    return estadoss;
  }//AQUI

  
    
 /* ArrayList<Character> patron = reglas(archLeido);
  patron.add(' ');
  int cont = 0;
  while(cont < patron.size()-1){
    String instruccion = archivo.next();
    if (patron.get(cont) == patron.get(cont+1)){
      System.out.println("hay otra misma regla");

    }
    else{
      System.out.println("se pasa a otra regla diferente");

    } 
    cont+=1;
    }
      
    archivo.close();
  }*/

  public static ArrayList<Character> reglas(String path) throws Exception{
    File documento = new File(path);
    Scanner archivo2 = new Scanner(documento); 

    archivo2.next();
    archivo2.next();
    archivo2.next();
    ArrayList<Character> patron = new ArrayList<Character>();
    while(archivo2.hasNext()){
      String linea = archivo2.next();
      char SimbInicial = linea.charAt(0);
      patron.add(SimbInicial);
    }
    archivo2.close();
    return patron;
    
  }

  public static boolean verificarRegla(String instruccion){
    int cont = 0;
    boolean siono = false;
    while(cont < instruccion.length()){
      char posRegla = listReglas[cont];// AVISARLE A BARRIOS :)
      if((instruccion.substring(instruccion.length()-1,instruccion.length())).charAt(0) == posRegla){
        siono = true;
      }
      cont++;
    }
    return siono;
  }

  public static boolean verificarRetornable(String instruccion, String letra){
    boolean siono = false;
    if(instruccion.substring(instruccion.length()-1,instruccion.length()).equals(letra)){
      siono = true;
    }
    return siono;
  }
      
}  
