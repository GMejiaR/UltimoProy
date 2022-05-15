import java.io.*;
import java.util.*;

public class Prueba{
  public static char[] listReglas;
  public static char[] listAlfabeto;
  public static String[][] matrizafd;
  
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
  listAlfabeto = new char[alfabeto.length()+1];
  listAlfabeto[0] = '0';
    int as = 0;
  for(int i = 1; i < listAlfabeto.length; i++){
    listAlfabeto[i] = alfabeto.charAt(as);
    as++;
  }

  System.out.println("Lista de alfabeto leída: " + Arrays.toString(listAlfabeto));
  // ----------------------------------

  // ------------ Símbolo Inicial ------------
  String linea3 = archivo.next();
  char SimbInicial = linea3.charAt(0);
  System.out.println("Símbolo inicial: " + SimbInicial);

    
  //-------------------------------------------
    int pollo = cantidadEstados(path);
    System.out.println("Estdos: " + pollo);
    matrizafd = new String[pollo][listAlfabeto.length];
    

     // --------- Leer Archivo y Algoritmo ---------
    int estact = 0;
    int cont = 0;////////CONTADOR---------------------!!!!!!!!!!!!!!!!!!! :D
    ArrayList<Character> patron = reglas(path);
    patron.add(' ');
    patron.add(' ');
////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////
    
    while(archivo.hasNext()){
      
      String actual = archivo.next();
      if (patron.get(cont) == patron.get(cont+1)){
        ArrayList<String> repetidos = new ArrayList<String>();
        repetidos.add(actual);
        while(true){
          if(archivo.hasNext()){
            if (patron.get(cont) == patron.get(cont+1)){
              actual = archivo.next();
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
        ArrayList<String> examinado = new ArrayList<String>();
        while(ver < tam){
          String inst =(repetidos.get(ver)).substring(3,((repetidos.get(ver)).length()));
          System.out.println("instruccion");
          System.out.println(inst);
          System.out.println("-------");
          if(verificarRegla(inst)){
            if(verificarRetornable(inst, actual.substring(0,1))){
              System.out.println("llego");
              System.out.println(inst);
              System.out.println("-------");
              int cantmin = inst.length()-1;
              if(cantmin > 1){
                ///////////////////////////////X->baX//////////////////////////////////////

                /////////////////////////////////////////////////////////////////////
                int a = 0;
                  cont++;
              }else if(cantmin == 1){
                ///////////////////////////////X->bX//////////////////////////////////////
                System.out.println("q");
                int ca1 = 0;
                int ant = estact;
                int a1 = 0;
                int a2 = 1;
                while(ca1<cantmin){
                  if(ca1 == cantmin-1){
                    String letraaqui = inst.substring(a1,a2);
                    String aponer = Integer.toString(poscolm(letraaqui));
                    String adonde = Integer.toString(estact+2);
                    String ingresar = aponer+adonde;
                    examinado.add(ingresar);
                    System.out.println("ingresar");
                    System.out.println(ingresar);
                    System.out.println("-------");
                    estact = ant;
                    break;
                  }else{
                    String letraaqui = inst.substring(a1,a2);
                    String aponer = Integer.toString(poscolm(letraaqui));
                    String adonde = Integer.toString(estact+2);
                    String ingresar = aponer+adonde;
                    examinado.add(ingresar);
                    System.out.println("ingresar");
                    System.out.println(ingresar);
                    System.out.println("-------");
                    ca1++;
                    estact++;
                    a1++;
                    a2++;
                  }
                }
                /////////////////////////////////////////////////////////////////////
                cont++;
              }
            }else{
              int cantmin = inst.length()-1;
              if(cantmin >= 1){
                ///////////////////////////////X->aY//////////////////////////////////////

                ////////////////////////////////////////////////////////////////////
                cont++;
              }else if(cantmin == 0){
                ///////////////////////////////X->Y/////////////////////////////////////

                ////////////////////////////////////////////////////////////////////
                cont++;
              }
            }
          }else{
            int cantmin = inst.length();
            if(cantmin > 1){
              //////////////////////////////X->ab///////////////////////////////

              ////////////////////////////////////////////////////////////
              cont++;
            }
          }
          ver++;
        }
          
      }else{
        String instruccion = actual.substring(3,actual.length());
        System.out.println("instruccion");
          System.out.println(instruccion);
          System.out.println("-------");
        if(verificarRegla(instruccion)){
          if(verificarRetornable(instruccion, actual.substring(0,1))){
            int cantmin = instruccion.length()-1;
            if(cantmin > 1){
              System.out.println("instr "+instruccion + cantmin);
              ///////////////////////X->abX///////////////////////////////

              //////////////////////////////////////////////////////
              cont++;
            }
          }else{
            int cantmin = instruccion.length()-1;
            if(cantmin >= 1){
              ///////////////////////////////X->aY//////////////////////////////////////
              int c3 = 1;
              int au1 =0;
              int au2 = 1;
              while(c3<=cantmin){
                if(c3 == cantmin){
                  ///
                  int ca1 = 0;
                  int poscol = poscolm(instruccion.substring(au1,au2));
                  
                  while(ca1<listAlfabeto.length){
                    if(ca1 == poscol){
                      String adonde= Integer.toString(estact+2);
                      matrizafd[estact][ca1] = adonde;
                      
                    }else{
                      matrizafd[estact][ca1] = "0";
                    }
                    ca1++;
                  }
                  estact++;
                  au1++;
                  au2++;
                  c3++;
                  ///
                  break;
                }else{
                  int ca1 = 0;
                  int poscol = poscolm(instruccion.substring(au1,au2));
                  
                  while(ca1<listAlfabeto.length){
                    if(ca1 == poscol){
                      String adonde= Integer.toString(estact+2);
                      matrizafd[estact][ca1] = adonde;
                      System.out.println("instruccion3");
                      System.out.println(adonde);
                      
                    }else{
                      matrizafd[estact][ca1] = "0";
                    }
                    ca1++;
                  }
                  estact++;
                  au1++;
                  au2++;
                }
                c3++;
              }
              ////////////////////////////////////////////////////////////////////
              cont++;
            }else if(cantmin == 0){
              ///////////////////////////////X->Y/////////////////////////////////////

              ////////////////////////////////////////////////////////////////////
              cont++;
            }
          }
        }else{
          int cantmin = instruccion.length();
          if(cantmin > 1){
            ////////////////////////////X->ab////////////////////////////////////

            ////////////////////////////////////////////////////////////////
            cont++;
          }
        }
      }
    }

    
///////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////
    for (int x=0; x < matrizafd.length; x++) {
      System.out.print("|");
      for (int y=0; y < matrizafd[x].length; y++) {
        System.out.print (matrizafd[x][y]);
        if (y!=matrizafd[x].length-1) System.out.print("\t");
      }
      System.out.println("|");
    }
   

  archivo.close();
  }

  public static int poscolm(String letra){
    int contemos = 0;
    int num = 0;
    while(contemos < listAlfabeto.length){
      if(letra.charAt(0) == listAlfabeto[contemos]){
        num = contemos;
        break;
      }
      contemos++;
    } 
    return num;
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
              int cantmin = inst.length()-1;
              if(cantmin >= 1){
                estadoss = estadoss + (cantmin);
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
