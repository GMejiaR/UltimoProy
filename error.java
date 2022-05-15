     // --------- Leer Archivo y Algoritmo ---------
    int estact = 0;
    int cont = 0;////////CONTADOR---------------------!!!!!!!!!!!!!!!!!!! :D
    ArrayList<Character> patron = reglas(path);
    patron.add(' ');
    patron.add(' ');
    while(archivo.hasNextLine()){
      String actual = archivo.next();
        // ------------- Validar Regla de Producción -------------  
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
        //////////////////////////////////////////////
        int ver = 0;
        int tam = repetidos.size();
        ArrayList<String> examinado = new ArrayList<String>();
        while(ver < tam){
          String inst =(repetidos.get(ver)).substring(3,((repetidos.get(ver)).length())-1);
          if(verificarRegla(inst)){
            if(verificarRetornable(inst, actual.substring(0,1))){
                 ////////////////////////
              System.out.println("o");
              int cantmin = inst.length()-1;
              if(cantmin > 1){
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
               // estadoss = estadoss + (cantmin-1);
                
              }else{
                String letraaqui = inst.substring(0,1);
                String aponer = Integer.toString(poscolm(letraaqui));
                String adonde = Integer.toString(estact+1);
                String ingresar = aponer+adonde;
                examinado.add(ingresar);
                System.out.println("aingresar: "+ingresar);
                cont++;
              }
              
              ///////////////
            }else{
              int cantmin = inst.length();
              if(cantmin > 1){
              //  estadoss = estadoss + (cantmin-1);
                cont++;
              }else if(cantmin == 0){
               // estadoss++;
                cont++;
              }
            }
          }else{
            int cantmin = inst.length();
            if(cantmin > 1){
             // estadoss = estadoss + (cantmin-1);
              cont++;
            }
          }
          ver++;
        }
        ////////////////  
      }else{
        String instruccion = actual.substring(3,actual.length());
        if(verificarRegla(instruccion)){
          if(verificarRetornable(instruccion, actual.substring(0,1))){
            int cantmin = instruccion.length()-1;
            if(cantmin > 1){
              /////////////////////////////////////////////////
              int c3 = 1;
              int au1 =0;
              int au2 = 1;
              int ant = estact;
              while(c3<=cantmin){
                if(c3 == cantmin-1){
                  int ca1 = 0;
                  int poscol = poscolm(instruccion.substring(au1,au2));
                  
                  while(ca1<listAlfabeto.length){
                    if(ca1 == poscol){
                      String adonde= Integer.toString(ant);
                      matrizafd[estact][ca1] = adonde;
                      System.out.println("instruccion5");
                      System.out.println(adonde);
                      
                    }else{
                      matrizafd[estact][ca1] = "0";
                    }
                    ca1++;
                  }
                  estact = ant;
                  break;
                }else{
                  int ca1 = 0;
                  int poscol = poscolm(instruccion.substring(au1,au2));
                  
                  while(ca1<listAlfabeto.length){
                    if(ca1 == poscol){
                      String adonde= Integer.toString(estact+2);
                      matrizafd[estact][ca1] = adonde;
                      System.out.println("instruccion4");
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
              cont++;
            }else{
              int poscol = poscolm(instruccion.substring(0,1));
              int c1 = 0;
              while(c1<listAlfabeto.length){
                if(c1 == poscol){
                  String adonde= Integer.toString(estact);
                  matrizafd[estact][c1] = adonde;
                  System.out.println("instruccion3");
                }else{
                  matrizafd[estact][c1] = "0";
                }
              c1++;
              }
            }
          }else{
            int cantmin = instruccion.length();
            if(cantmin > 1){
              int c3 = 1;
              int au1 =0;
              int au2 = 1;
              while(c3<=cantmin){
                if(c3 == cantmin){
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
                  cont++;
                  au1++;
                  au2++;
                }
                c3++;
              }
            }else if(cantmin == 0){
              int poscol = poscolm(instruccion.substring(0,1));
              int c1 = 0;
              while(c1<listAlfabeto.length){
                if(c1 == poscol){
                  String adonde= Integer.toString(estact+2);
                  matrizafd[estact][c1] = adonde;
                  System.out.println("instruccion2");
                }else{
                  matrizafd[estact][c1] = "0";
                }
              c1++;
              }
              estact++;
              cont++;
            }
          }
        }else{
          int cantmin = instruccion.length();
          if(cantmin > 1){
            
          }else{
            
            int poscol = poscolm(instruccion.substring(0,1));
            int c1 = 0;
            while(c1<listAlfabeto.length){
              if(c1 == poscol){
                
                String adonde= Integer.toString(listAlfabeto.length);
                matrizafd[estact][c1] = adonde;
              }else{
                matrizafd[estact][c1] = "0";
              }
            c1++;
            }
            cont++;
          }
        }
      }
    }