import java.io.*;
import java.util.*;

public class Gramatica{
  
  public static char[] listReglas;
  public static char[] listAlfabeto;
  public static String[][] matrizafd;
  
	public static void main(String[] args) throws Exception{
		//Su codigo aqui
    String banderita = args[1];  
    String nomArchivo = args[2];
    String ArchGramatica = args[0];
    
    if (banderita.equals("-afn")){
      creador(ArchGramatica,nomArchivo);
      conversion()
    }else if(banderita.equals("-afd")){
      //No se como hacer esto 
    }else{
      System.out.println("Error: bandera invalida");
    }
    
	}

  public static void creador(String path, String nombrearch) throws Exception{
    File documento = new File(path);
  Scanner archivo = new Scanner(documento);  

  String linea = archivo.next();
  String reglas = linea.replace(",", "");
  listReglas = new char[reglas.length()];
  
  for(int i = 0; i < listReglas.length; i++){
    listReglas[i] = reglas.charAt(i);
  }  
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
        int estadoenmomento = estact;
        ArrayList<String> repetidos = new ArrayList<String>();
        repetidos.add(actual);
        while(true){
          if(archivo.hasNext()){
            if (patron.get(cont) == patron.get(cont+1)){
              actual = archivo.next();
              repetidos.add(actual);
              cont++;
            }else{
              //cont++;
              cont++;
              break;
            }
          }else{
            //cont++;
              break;
            }
          
        }
        
        int ver = 0;
        int tam = repetidos.size();
        ArrayList<String> examinado = new ArrayList<String>();
        while(ver < tam){
          String inst =(repetidos.get(ver)).substring(3,((repetidos.get(ver)).length()));
          if(verificarRegla(inst)){
            if(verificarRetornable(inst, (repetidos.get(ver)).substring(0,1))){
              int cantmin = inst.length()-1;
              if(cantmin > 1){
                ///////////////////////////////X->baX//////////////////////////////////////
                int ca1 = 0;
                int ant = estact;
                int a1 = 0;
                int a2 = 1;
                while(ca1<cantmin){
                  if(ca1 == cantmin-1){
                    String letraaqui = inst.substring(a1,a2);
                    String aponer = Integer.toString(poscolm(letraaqui));
                    String adonde = Integer.toString(ant+1);
                    String ingresar = aponer+adonde;
                    examinado.add(ingresar);
                    estact = ant;
                    break;
                  }else{
                    String letraaqui = inst.substring(a1,a2);
                    String aponer = Integer.toString(poscolm(letraaqui));
                    String adonde = Integer.toString(estact+2);
                    String ingresar = aponer+adonde;
                    examinado.add(ingresar);
                    ca1++;
                    estact++;
                    a1++;
                    a2++;
                  }
                }
                /////////////////////////////////////////////////////////////////////
                
                 // cont++;
              }else if(cantmin == 1){
                ///////////////////////////////X->bX//////////////////////////////////////
                String letraaqui = inst.substring(0,1);
                String aponer = Integer.toString(poscolm(letraaqui));
                String adonde = Integer.toString(estact+1);
                String ingresar = aponer+adonde;
                examinado.add(ingresar);
                /////////////////////////////////////////////////////////////////////
              //  cont++;
              }
            }else{
              int cantmin = inst.length()-1;
              if(cantmin >= 1){
                ///////////////////////////////X->aY//////////////////////////////////////
                int ca1 = 0;
                int a1 = 0;
                int a2 = 1;
                while(ca1 < cantmin){
                  if(ca1 == cantmin-1){
                    String letraaqui = inst.substring(a1,a2);
                    String aponer = Integer.toString(poscolm(letraaqui));
                    String adonde = Integer.toString(estact+2);
                    String ingresar = aponer+adonde;
                    examinado.add(ingresar);
                    ca1++;
                    estact++;
                    System.out.println(estact);
                  }else{
                    String letraaqui = inst.substring(a1,a2);
                    String aponer = Integer.toString(poscolm(letraaqui));
                    String adonde = Integer.toString(estact+2);
                    String ingresar = aponer+adonde;
                    examinado.add(ingresar);
                    ca1++;
                    a1++;
                    a2++;
                    estact++;
                  }
                }
                ////////////////////////////////////////////////////////////////////
               // cont++;
              }else if(cantmin == 0){
                ///////////////////////////////X->Y/////////////////////////////////////
                String aponer = "0";
                String adonde = Integer.toString(estact+2);
                String ingresar = aponer+adonde;
                examinado.add(ingresar);
                estact++;
                ////////////////////////////////////////////////////////////////////
              //  cont++;
              }
            }
          }else{
            int cantmin = inst.length();
            if(cantmin >= 1){
              //////////////////////////////X->ab///////////////////////////////
              int ca1 = 0;
              int a1 = 0;
              int a2 = 1;
              while(ca1 < cantmin){
                if(ca1 == cantmin-1){      
                  String letraaqui = inst.substring(a1,a2);
                  String aponer = Integer.toString(poscolm(letraaqui));
                  String adonde = Integer.toString(pollo);
                  String ingresar = aponer+adonde;
                  examinado.add(ingresar);
                  ca1++;

                }else{
                  String letraaqui = inst.substring(a1,a2);
                  String aponer = Integer.toString(poscolm(letraaqui));
                  String adonde = Integer.toString(estact+2);
                  String ingresar = aponer+adonde;
                  examinado.add(ingresar);
                  ca1++;
                  a1++;
                  a2++;
                  estact++;
                }
              }
              ////////////////////////////////////////////////////////////
            //  cont++;
            }else if(cantmin == 1){
              //////////////////////////////X->b///////////////////////////////
              String letraaqui = inst.substring(0,1);
              String aponer = Integer.toString(poscolm(letraaqui));
              String adonde = Integer.toString(pollo);
              String ingresar = aponer+adonde;
              examinado.add(ingresar);
              ////////////////////////////////////////////////////////////
              //cont++;
            }
          }
          ver++;
        }
        ingresomat(examinado,estadoenmomento);  
      }else{
        String instruccion = actual.substring(3,actual.length());
        if(verificarRegla(instruccion)){
          if(verificarRetornable(instruccion, actual.substring(0,1))){
            int cantmin = instruccion.length()-1;
            if(cantmin > 1){
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
          if(cantmin >= 1){
            ////////////////////////////X->ab////////////////////////////////////
            int ca1 = 0;
              int a1 = 0;
              int a2 = 1;
              while(ca1 < cantmin){
                if(ca1 == cantmin-1){      
                  int ca2 = 0;
                  int poscol = poscolm(instruccion.substring(a1,a2));
                  
                  while(ca2<listAlfabeto.length){
                    if(ca2 == poscol){
                      String adonde= Integer.toString(pollo);
                      matrizafd[estact][ca2] = adonde;
                     
                      
                    }else{
                      matrizafd[estact][ca2] = "0";
                    }
                    ca2++;
                  }

                }else{
                  int ca2 = 0;
                  int poscol = poscolm(instruccion.substring(a1,a2));
                  
                  while(ca2<listAlfabeto.length){
                    if(ca2 == poscol){
                      String adonde= Integer.toString(estact+2);
                      matrizafd[estact][ca2] = adonde;
                      
                    }else{
                      matrizafd[estact][ca2] = "0";
                    }
                    ca2++;
                  }
                  
                  
                  a1++;
                  a2++;
                  estact++;
                }
                ca1++;
              }
            ////////////////////////////////////////////////////////////////
            cont++;
          }
        }
      }
    }
    int ca1 = 0;
    while(ca1<listAlfabeto.length){
      matrizafd[pollo-1][ca1] = "0";
      ca1++;
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
  crear(nombrearch, pollo);
  }

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
              cont++;
              break;
              
            }
          }else{
              break;
            }
          
        }
        int ver = 0;
        int tam = repetidos.size();
        while(ver < tam){
          String inst =(repetidos.get(ver)).substring(3,((repetidos.get(ver)).length()));
          if(verificarRegla(inst)){
            if(verificarRetornable(inst, actual.substring(0,1))){
              int cantmin = inst.length()-1;
              if(cantmin > 1){
                estadoss = estadoss + (cantmin-1);
               // cont++;
              }
            }else{
              int cantmin = inst.length()-1;
              if(cantmin >= 1){
                estadoss = estadoss + (cantmin);
               // cont++;
              }else if(cantmin == 0){
                estadoss++;
               // cont++;
              }
            }
          }else{
            int cantmin = inst.length();
            if(cantmin > 1){
              estadoss = estadoss + (cantmin-1);
             // cont++;
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
              
              estadoss = estadoss + (cantmin-1);
             // cont++;
            }
          }else{
            int cantmin = instruccion.length();
            if(cantmin > 1){
              estadoss = estadoss + (cantmin-1);
             // cont++;
            }else if(cantmin == 0){
              estadoss++;
             // cont++;
            }
          }
        }else{
          int cantmin = instruccion.length();
          if(cantmin > 1){
            estadoss = estadoss + (cantmin-1);
           // cont++;
          }
        }
      }
    }
    archivo2.close();
    return estadoss;
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
    while(cont < listReglas.length){
      char posRegla = listReglas[cont];// AVISARLE A BARRIOS :)
      if(instruccion.charAt(instruccion.length()-1) == posRegla){
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

  public static void ingresomat(ArrayList<String> verificar,int estadoenmomento){
    int ca = 0;
    while(ca < listAlfabeto.length){
      String yacasi = "";
      int cc = 0;
      while(cc< verificar.size()){
        char que = (verificar.get(cc)).charAt(0);
        int puede = Character.getNumericValue(que);
        
        if(poscolm(String.valueOf(listAlfabeto[ca])) == puede){
          if(yacasi.length() == 0){
            yacasi = yacasi + (verificar.get(cc)).charAt(1);
          }else{
            yacasi = yacasi + ";" + (verificar.get(cc)).charAt(1);
          }
        }
        cc++;
      }
      if(yacasi.length() >0){
        matrizafd[estadoenmomento][ca] = yacasi;
                      
      }else{
        matrizafd[estadoenmomento][ca] = "0";
      }
      ca++;
    }  
    
  }

  public static void crear(String nombre, int cantest)throws Exception{
    FileWriter archivo = new FileWriter(nombre+".afn");
    int cont = 1;
    while(cont < listAlfabeto.length){
      if(cont == listAlfabeto.length-1){
        archivo.write(listAlfabeto[cont]);
      }else{
        archivo.write(listAlfabeto[cont]+","); 
      }  
      cont++;
    }
    archivo.write('\n');
    String cantestados = Integer.toString(cantest);
    archivo.write(cantestados);

    archivo.write('\n');
    String finals = Integer.toString(cantest);
    archivo.write(finals);
    archivo.write('\n');

    for (int x=0; x < matrizafd.length; x++) {
      for (int y=0; y < matrizafd[x].length; y++) {
        System.out.println(matrizafd[y][x]);
        if (y!=matrizafd[x].length-1) archivo.write("\n");
      }
    }
    
    archivo.close();
  }

  public static void Conversion(String nombre, String path) throws Exception{
    File documento = new File(path);
    Scanner archivo = new Scanner(documento);

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
  
    String linea2 = archivo.next();
    int numEstadosN = Integer.parseInt(linea2);
    String estFinal = archivo.next();
    String linea4 = archivo.next();
  
    String[] transiciones = linea4.split(",");

    ArrayList<String> transicionLamb = new ArrayList<String>();
    
    
    for(int i = 0; i < transiciones.length; i++){
      transicionLamb.add(transiciones[i]);
    }
    
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
    System.out.println();
    System.out.println();


    //lLenar la matriz
    for(int i = 0; i < transicionLamb.size()-1; i ++){
      for(int j = 1; j < listAlfabeto.length; j++){
        int cont = 0;
        String transicionL = transicionLamb.get(i+1);
        
        if(transicionL.length() > 1){
          ArrayList<String> doble = new ArrayList<String>();
          while(cont < 2){
            char elemento = transicionL.charAt(cont);
            String cambio = Cambio(path, elemento, j);
            int llenar = transicionLamb.indexOf(cambio);
            String finalPos = Integer.toString(llenar);
            doble.add(finalPos);
            cont++;
          }
          doble.remove("0");
          if(doble.size()  ==  1){
            matrizPos[i][j] = doble.get(0);
          }
        }
        else{
          char elementotrans = transicionL.charAt(0);
          String cambio = Cambio(path, elementotrans, j);
          
          for(int k = 1; k<transicionLamb.size();k++){
            if(transicionLamb.get(k).length()>1){
                char estado = (transicionLamb.get(k)).charAt(0);
                char estado2 =(transicionLamb.get(k)).charAt(1);
                if((Character.toString(estado)).startsWith(cambio) | (Character.toString(estado2)).startsWith(cambio)){
                  matrizPos[i][j] = Integer.toString(k);
                }
            }else{
              int llenar = transicionLamb.indexOf(cambio);
              String finalPos = Integer.toString(llenar);
              matrizPos[i][j] = finalPos;
            }
          }
        }
      }
    }
    
// Imprimir matriz llena otra vez
  for (int x=0; x < matrizPos.length; x++) {
        System.out.print("|");
      for (int y=0; y < matrizPos[x].length; y++) {
        System.out.print (matrizPos[x][y]);
        if (y!=matrizPos[x].length-1) System.out.print("\t");
      }
      System.out.println("|");
    }
  
    archivo.close();
  //ESCRIBIR ALFABETO
  FileWriter res = new FileWriter(nombre +".afd");//meterle el nombre 
  for(int i = 1; i < listAlfabeto.length; i++){
    if(i == listAlfabeto.length-1){
        res.write(listAlfabeto[i]);
    }else{
      res.write(listAlfabeto[i] + ",");
    }
  }

  //ESCRIBIR #ESTADOS Y  ESTADOS FINALES
  res.write('\n');
  res.write(Integer.toString(transicionLamb.size()));
  res.write('\n');
  res.write(estFinal);
  res.write('\n');

  //ESCRIBIR TRANSICIONES
  for(int i = 0; i < transicionLamb.size()-1; i ++){
      for(int j= 0; j < listAlfabeto.length; j++){
        if(j == listAlfabeto.length-1){
          res.write(matrizPos[i][j]);
          res.write('\n');
      }else{
        res.write(matrizPos[i][j] + ",");
      }
        
      }
    }
  res.close();
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

  
  
}