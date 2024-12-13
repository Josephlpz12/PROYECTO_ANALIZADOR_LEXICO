/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.lexico;

import java.util.LinkedList;

/**
 *
 * @author jose
 */
public class AnalisisLexico {

    String codigoHtml = "";
    String estado = "A";
    LinkedList<Simbolo> tablaLexico = new LinkedList<>();
    LinkedList<String> errorLexico = new LinkedList<>();

    public String anlizar(String entrada) {
        
        Simbolo s = new Simbolo();
        char[] letras = entrada.toCharArray();
        
        int columna = 0;
        int linea = 1;
        boolean validarCaracteres;
        for (char letra : letras) {
            columna++;
            validarCaracteres = false;
            switch (estado) {
                case "A" -> {
                    switch (letra) {
                        
                        case 'S' -> {
                            estado = "B";
                            s.lexema = "S";
                            s.columna = columna;
                            s.linea = linea;
                        }
                        case 'L' -> {
                            estado = "C";
                            s.lexema = "L";
                            s.columna = columna;
                            s.linea = linea;
                        }
                        case 'P' -> {
                            estado = "D";
                            s.lexema = "P";
                            s.columna = columna;
                            s.linea = linea;
                        }
                        
                        case 'D' -> {
                            estado = "T";
                            s.lexema = "D";
                            s.columna = columna;
                            s.linea = linea;
                        }
                        
                        case 'A' -> {
                            estado = "P";
                            s.lexema = "A";
                            s.columna = columna;
                            s.linea = linea;
                        }    
                        
                        case 'E' -> {
                            estado = "O";
                            s.lexema = "E";
                            s.columna = columna;
                            s.linea = linea;
                        }
                        
                        
                        case 'C' -> {
                            estado = "X";
                            s.lexema = "C";
                            s.columna = columna;
                            s.linea = linea;
                        }
                        
                        case 'H' -> {
                            estado = "H1";
                            s.lexema = "H";
                            s.columna = columna;
                            s.linea = linea;
                        }
                        case 'M' -> {
                            estado = "M1";
                            s.lexema = "M";
                            s.columna = columna;
                            s.linea = linea;
                        }
                        case 'N' -> {
                            estado = "N1";
                            s.lexema = "N";
                            s.columna = columna;
                            s.linea = linea;
                        }
                        case 'F' -> {
                            estado = "F1";
                            s.lexema = "F";
                            s.columna = columna;
                            s.linea = linea;
                        }
                        case 'b' -> {
                            estado = "b1";
                            s.lexema = "b";
                            s.columna = columna;
                            s.linea = linea;
                        }
                        
                        case '+' -> { signosValidacion(s,"red","+","+","mas",columna,linea);s = new Simbolo();}
                        case '-' -> { signosValidacion(s,"red","-","-","menos",columna,linea);s = new Simbolo();}
                        case '*' -> { signosValidacion(s,"red","*","*","multipli.",columna,linea);s = new Simbolo();}
                        case '/' -> { signosValidacion(s,"red","/","/","division",columna,linea);s = new Simbolo();}
                        case '=' -> { signosValidacion(s,"red","=","=","igual.",columna,linea);s = new Simbolo();}
                        case '<' -> { signosValidacion(s,"red","<","&lt;","menor que.",columna,linea);s = new Simbolo();}
                        case '>' -> { signosValidacion(s,"red",">","&gt;","mayor que.",columna,linea);s = new Simbolo();}
                        case ',' -> { signosValidacion(s,"purple",",",",","coma.",columna,linea);s = new Simbolo();}
                        case ':' -> { signosValidacion(s,"purple",":",":","dos puntos.",columna,linea);s = new Simbolo();}
                        case '(' -> { signosValidacion(s,"purple","(","(","par abre.",columna,linea);s = new Simbolo();}
                        case ')' -> { signosValidacion(s,"purple",")",")","par cierra.",columna,linea);s = new Simbolo();}
                     
                        case '\'' -> {
                            codigoHtml += "<font size='12' color='gray' >'</font>";
                            estado = "Z";
                        }
                        
                        case '"' -> {
                            codigoHtml += "<font size='12' color='orange' >\"</font>";
                            estado = "z";
                        }    
                        
                        case ' ' -> codigoHtml += "&nbsp;";
                        case '\n' -> {
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                        }
                        case '\t' -> codigoHtml += "&nbsp;&nbsp;&nbsp;";
                        default -> validarCaracteres = true;
                    }
                
                    if (validarCaracteres) {
                        if ((letra >= 65 && letra <= 90) || (letra >= 97 && letra <= 122)) {
                            estado = "E";
                            s.lexema = letra + "";
                            s.columna = columna;
                            s.linea = linea;
                        } else {
                            if (letra >= 48 && letra <= 57) {
                                estado = "F";
                                s.lexema = letra + "";
                                s.columna = columna;
                                s.linea = linea;
                            } else {
                                String error = "Error lexico linea " + linea + ", columna: " + columna + ", caracter no reconocido: "+letra;
                                errorLexico.add(error);
                            }
                        }

                    }
                }
               
                case "Z" -> {
                    if (letra != '\n') {
                        codigoHtml += "<font size='12' color='gray'>" + letra + "</font>";
                    } else {
                        codigoHtml += "</font><br>";
                        estado = "A";
                        columna = 0;
                        linea++;
                    }
                }
                    
                case "z" -> {
                    if (letra != '\n' && letra !='"') {
                        codigoHtml += "<font size='12' color='orange'>" + letra + "</font>";
                    } else {
                        codigoHtml += "<font size='12' color='orange'>" + "\"" + "</font>"; 
                        estado = "A";
                        columna = 0;
                        linea++;
                    }
                }
                    
                case "B" -> {
                    switch (letra) {
                        case 'i' -> {
                            estado = "G";
                            s.lexema = "Si";
                        }
                        case 'e' -> {
                            estado = "SGU1";
                            s.lexema = "Se";
                        }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                        }
                        case '+' -> {optimizar(s, "+", "red", "+", "mas", columna, linea); s = new Simbolo();}
                        case '-' -> {optimizar(s, "-", "red", "-", "menos", columna, linea);s = new Simbolo();}
                        case '*' -> {optimizar(s, "*", "red", "*", "multipli.", columna, linea);s = new Simbolo();}
                        case '/' -> {optimizar(s, "/", "red", "/", "division.", columna, linea);s = new Simbolo();}
                        case ',' -> {optimizar(s, ",", "purple", ",", "coma.", columna, linea);s = new Simbolo();}
                        case '<' -> {optimizar(s, "<", "red", "<", "menor", columna, linea);s = new Simbolo();}
                        case '>' -> {optimizar(s, ">", "red", ">", "mayor", columna, linea);s = new Simbolo();}
                        case '=' -> {optimizar(s, "=", "red", "=", "igual", columna, linea);s = new Simbolo();}
                        case '(' -> {optimizar(s, "(", "purple", "(", "par. abre", columna, linea);s = new Simbolo();}
                        case ')' -> {optimizar(s, ")", "purple", ")", "par. cierra", columna, linea);s = new Simbolo();}
                        case ':' -> {optimizar(s, ":", "purple", ":", "dos puntos", columna, linea);s = new Simbolo();}
                        case ' ' -> {
                            signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                            codigoHtml += "&nbsp;";
                            estado = "A";
                        }
                        case '\n' -> {
                            signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            estado = "A";
                        }
                        case '\t' -> {
                            signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            estado = "A";
                        }
                        default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }

                    
                case "G" -> {
                    switch (letra) {
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                        }
                        case 'n' -> {
                            estado = "G1";
                            s.lexema = "Sin";
                        }
                        case 'g' -> {
                            estado = "SG1";
                            s.lexema = "Sig";
                        }
                        case '+' -> {
                            //EstadosFinales(String token,String tokensimbolo, String color,Simbolo s,String simb,String expresion, int columna, int linea)
                            EstadosFinales("Si", "mas", "red", s,"+","+", columna, linea); s = new Simbolo();
                        }
                        case '-' -> {
                             EstadosFinales("Si", "menos", "red", s,"-","-", columna, linea);s = new Simbolo();
                        }
                        case '<' -> {
                             EstadosFinales("Si", "menor", "red", s,"<","<", columna, linea);s = new Simbolo();
                        }
                        case '>' -> {
                            EstadosFinales("Si", "mayor", "red", s,">",">", columna, linea);s = new Simbolo();
                        }
                        case '=' -> {
                            EstadosFinales("Si", "igual", "red", s,"=","=", columna, linea);s = new Simbolo();
                        }
                        case '(' -> {
                            EstadosFinales("Si", "par abre", "purple", s,"(","(", columna, linea);s = new Simbolo();
                        }
                        case ')' -> {
                            EstadosFinales("Si", "par cierra", "purple", s,")",")", columna, linea);s = new Simbolo();
                        }
                        case ':' -> {
                           EstadosFinales("Si", "dos puntos", "purple", s,":",":", columna, linea);s = new Simbolo();
                        }
                        case ' ' -> {
                            //SimbolosEsp(Simbolo s, String letra,int columna, int linea)
                            SimbolosEsp(s, " ", columna, linea,"Si");s = new Simbolo();
                            codigoHtml += "&nbsp;";
                            break;
                        }
                        case '\n' -> {
                             SimbolosEsp(s, "\n", columna, linea,"Si");s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        }
                        case '\t' -> {
                            SimbolosEsp(s, "\t", columna, linea,"Si");s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        }
                        default -> validarCaracteres = true;
                    }
                    validarEstadosFinales("Si", "green", validarCaracteres, letra, linea, columna, s);
                }
                    
                
                case "G1" -> {
                    switch (letra) {
                        case 'o' -> {
                            estado = "G2";
                            s.lexema = "Sino";
                        }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                        }
                        case '+' -> {optimizar(s, "+", "red", "+", "mas", columna, linea); s = new Simbolo();}
                        case '-' -> {optimizar(s, "-", "red", "-", "menos", columna, linea);s = new Simbolo();}
                        case '*' -> {optimizar(s, "*", "red", "*", "multipli.", columna, linea);s = new Simbolo();}
                        case '/' -> {optimizar(s, "/", "red", "/", "division.", columna, linea);s = new Simbolo();}
                        case ',' -> {optimizar(s, ",", "purple", ",", "coma.", columna, linea);s = new Simbolo();}
                        case '<' -> {optimizar(s, "<", "red", "<", "menor", columna, linea);s = new Simbolo();}
                        case '>' -> {optimizar(s, ">", "red", ">", "mayor", columna, linea);s = new Simbolo();}
                        case '=' -> {optimizar(s, "=", "red", "=", "igual", columna, linea);s = new Simbolo();}
                        case '(' -> {optimizar(s, "(", "purple", "(", "par. abre", columna, linea);s = new Simbolo();}
                        case ')' -> {optimizar(s, ")", "purple", ")", "par. cierra", columna, linea);s = new Simbolo();}
                        case ':' -> {optimizar(s, ":", "purple", ":", "dos puntos", columna, linea);s = new Simbolo();}
                        case ' ' -> {
                            signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                            codigoHtml += "&nbsp;";
                            estado = "A";
                        }
                        case '\n' -> {
                            signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            estado = "A";
                        }
                        case '\t' -> {
                            signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            estado = "A";
                        }
                        default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                
                case "G2" -> {
                    switch (letra) {
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                        }
                        
                       case '+' -> {
                            //EstadosFinales(String token,String tokensimbolo, String color,Simbolo s,String simb,String expresion, int columna, int linea)
                            EstadosFinales("Sino", "mas", "red", s,"+","+", columna, linea);s = new Simbolo();
                        }
                        case '-' -> {
                             EstadosFinales("Sino", "menos", "red", s,"-","-", columna, linea);s = new Simbolo();
                        }
                        case '<' -> {
                             EstadosFinales("Sino", "menor", "red", s,"<","<", columna, linea);s = new Simbolo();
                        }
                        case '>' -> {
                            EstadosFinales("Sino", "mayor", "red", s,">",">", columna, linea);s = new Simbolo();
                        }
                        case '=' -> {
                            EstadosFinales("Sino", "igual", "red", s,"=","=", columna, linea);s = new Simbolo();
                        }
                        case '(' -> {
                            EstadosFinales("Sino", "par abre", "purple", s,"(","(", columna, linea);s = new Simbolo();
                        }
                        case ')' -> {
                            EstadosFinales("Sino", "par cierra", "purple", s,")",")", columna, linea);s = new Simbolo();
                        }
                        case ':' -> {
                           EstadosFinales("Sino", "dos puntos", "purple", s,":",":", columna, linea);s = new Simbolo();
                        }
                        case ' ' -> {
                            //SimbolosEsp(Simbolo s, String letra,int columna, int linea)
                            SimbolosEsp(s, " ", columna, linea,"Sino");s = new Simbolo();
                            codigoHtml += "&nbsp;";
                            break;
                        }
                        case '\n' -> {
                             SimbolosEsp(s, "\n", columna, linea,"Sino");s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        }
                        case '\t' -> {
                            SimbolosEsp(s, "\t", columna, linea,"Sino");s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        }
                        default -> validarCaracteres = true;
                    }
                    validarEstadosFinales("Sino", "green", validarCaracteres, letra, linea, columna, s);
                }
                
                 case "SG1" -> {
                    switch (letra) {
                        case 'u' -> {
                            estado = "SG2";
                            s.lexema = "Sigu";
                        }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                        }
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                 case "SG2" -> {
                    switch (letra) {
                        case 'i' -> {
                            estado = "SG3";
                            s.lexema = "Sigui";
                        }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                        }
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                 case "SG3" -> {
                    switch (letra) {
                        case 'e' -> {
                            estado = "SG4";
                            s.lexema = "Siguie";
                        }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                        }
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                 case "SG4" -> {
                    switch (letra) {
                        case 'n' -> {
                            estado = "SG5";
                            s.lexema = "Siguien";
                        }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                        }
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                 case "SG5" -> {
                    switch (letra) {
                        case 't' -> {
                            estado = "SG6";
                            s.lexema = "Siguient";
                        }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                        }
                        
                        default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                 case "SG6" -> {
                    switch (letra) {
                        case 'e' -> {
                            estado = "SG7";
                            s.lexema = "Siguiente";
                        }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                        }
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                 
                  case "SG7" -> {
                    switch (letra) {
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                        }
                        
                       
                        case ' ' -> {
                            //SimbolosEsp(Simbolo s, String letra,int columna, int linea)
                            SimbolosEsp(s, " ", columna, linea,"Siguiente");s = new Simbolo();
                            codigoHtml += "&nbsp;";
                            break;
                        }
                        case '\n' -> {
                             SimbolosEsp(s, "\n", columna, linea,"Siguiente");s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        }
                        case '\t' -> {
                            SimbolosEsp(s, "\t", columna, linea,"Siguiente");s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        }
                        default -> validarCaracteres = true;
                    }
                    validarEstadosFinales("Siguiente", "green", validarCaracteres, letra, linea, columna, s);
                }
                  
                  case "SGU1" -> {
                    switch (letra) {
                        case 'g' -> {
                            estado = "SGU2";
                            s.lexema = "Seg";
                        }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                        }
                        case '+' -> {optimizar(s, "+", "red", "+", "mas", columna, linea); s = new Simbolo();}
                        case '-' -> {optimizar(s, "-", "red", "-", "menos", columna, linea);s = new Simbolo();}
                        case '*' -> {optimizar(s, "*", "red", "*", "multipli.", columna, linea);s = new Simbolo();}
                        case '/' -> {optimizar(s, "/", "red", "/", "division.", columna, linea);s = new Simbolo();}
                        case ',' -> {optimizar(s, ",", "purple", ",", "coma.", columna, linea);s = new Simbolo();}
                        case '<' -> {optimizar(s, "<", "red", "<", "menor", columna, linea);s = new Simbolo();}
                        case '>' -> {optimizar(s, ">", "red", ">", "mayor", columna, linea);s = new Simbolo();}
                        case '=' -> {optimizar(s, "=", "red", "=", "igual", columna, linea);s = new Simbolo();}
                        case '(' -> {optimizar(s, "(", "purple", "(", "par. abre", columna, linea);s = new Simbolo();}
                        case ')' -> {optimizar(s, ")", "purple", ")", "par. cierra", columna, linea);s = new Simbolo();}
                        case ':' -> {optimizar(s, ":", "purple", ":", "dos puntos", columna, linea);s = new Simbolo();}
                        case ' ' -> {
                            signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                            codigoHtml += "&nbsp;";
                            estado = "A";
                        }
                        case '\n' -> {
                            signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            estado = "A";
                        }
                        case '\t' -> {
                            signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            estado = "A";
                        }
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                  case "SGU2" -> {
                    switch (letra) {
                        case 'u' -> {
                            estado = "SGU3";
                            s.lexema = "Segu";
                        }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                        }
                        case '+' -> {optimizar(s, "+", "red", "+", "mas", columna, linea); s = new Simbolo();}
                        case '-' -> {optimizar(s, "-", "red", "-", "menos", columna, linea);s = new Simbolo();}
                        case '*' -> {optimizar(s, "*", "red", "*", "multipli.", columna, linea);s = new Simbolo();}
                        case '/' -> {optimizar(s, "/", "red", "/", "division.", columna, linea);s = new Simbolo();}
                        case ',' -> {optimizar(s, ",", "purple", ",", "coma.", columna, linea);s = new Simbolo();}
                        case '<' -> {optimizar(s, "<", "red", "<", "menor", columna, linea);s = new Simbolo();}
                        case '>' -> {optimizar(s, ">", "red", ">", "mayor", columna, linea);s = new Simbolo();}
                        case '=' -> {optimizar(s, "=", "red", "=", "igual", columna, linea);s = new Simbolo();}
                        case '(' -> {optimizar(s, "(", "purple", "(", "par. abre", columna, linea);s = new Simbolo();}
                        case ')' -> {optimizar(s, ")", "purple", ")", "par. cierra", columna, linea);s = new Simbolo();}
                        case ':' -> {optimizar(s, ":", "purple", ":", "dos puntos", columna, linea);s = new Simbolo();}
                        case ' ' -> {
                            signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                            codigoHtml += "&nbsp;";
                            estado = "A";
                        }
                        case '\n' -> {
                            signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            estado = "A";
                        }
                        case '\t' -> {
                            signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            estado = "A";
                        }
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                  case "SGU3" -> {
                    switch (letra) {
                        case 'n' -> {
                            estado = "SGU4";
                            s.lexema = "Segun";
                        }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                        }
                        case '+' -> {optimizar(s, "+", "red", "+", "mas", columna, linea); s = new Simbolo();}
                        case '-' -> {optimizar(s, "-", "red", "-", "menos", columna, linea);s = new Simbolo();}
                        case '*' -> {optimizar(s, "*", "red", "*", "multipli.", columna, linea);s = new Simbolo();}
                        case '/' -> {optimizar(s, "/", "red", "/", "division.", columna, linea);s = new Simbolo();}
                        case ',' -> {optimizar(s, ",", "purple", ",", "coma.", columna, linea);s = new Simbolo();}
                        case '<' -> {optimizar(s, "<", "red", "<", "menor", columna, linea);s = new Simbolo();}
                        case '>' -> {optimizar(s, ">", "red", ">", "mayor", columna, linea);s = new Simbolo();}
                        case '=' -> {optimizar(s, "=", "red", "=", "igual", columna, linea);s = new Simbolo();}
                        case '(' -> {optimizar(s, "(", "purple", "(", "par. abre", columna, linea);s = new Simbolo();}
                        case ')' -> {optimizar(s, ")", "purple", ")", "par. cierra", columna, linea);s = new Simbolo();}
                        case ':' -> {optimizar(s, ":", "purple", ":", "dos puntos", columna, linea);s = new Simbolo();}
                        case ' ' -> {
                            signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                            codigoHtml += "&nbsp;";
                            estado = "A";
                        }
                        case '\n' -> {
                            signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            estado = "A";
                        }
                        case '\t' -> {
                            signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            estado = "A";
                        }
                       default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                  
                   case "SGU4" -> {
                    switch (letra) {
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                        }
                        
                        case '+' -> {
                            //EstadosFinales(String token,String tokensimbolo, String color,Simbolo s,String simb,String expresion, int columna, int linea)
                            EstadosFinales("Segun", "mas", "red", s,"+","+", columna, linea);s = new Simbolo();
                        }
                        case '-' -> {
                             EstadosFinales("Segun", "menos", "red", s,"-","-", columna, linea);s = new Simbolo();
                        }
                        case '<' -> {
                             EstadosFinales("Segun", "menor", "red", s,"<","<", columna, linea);s = new Simbolo();
                        }
                        case '>' -> {
                            EstadosFinales("Segun", "mayor", "red", s,">",">", columna, linea);s = new Simbolo();
                        }
                        case '=' -> {
                            EstadosFinales("Segun", "igual", "red", s,"=","=", columna, linea);s = new Simbolo();
                        }
                        case '(' -> {
                            EstadosFinales("Segun", "par abre", "purple", s,"(","(", columna, linea);s = new Simbolo();
                        }
                        case ')' -> {
                            EstadosFinales("Segun", "par cierra", "purple", s,")",")", columna, linea);s = new Simbolo();
                        }
                        case ':' -> {
                           EstadosFinales("Segun", "dos puntos", "purple", s,":",":", columna, linea);s = new Simbolo();
                        }
                        case ' ' -> {
                            //SimbolosEsp(Simbolo s, String letra,int columna, int linea)
                            SimbolosEsp(s, " ", columna, linea,"Segun");s = new Simbolo();
                            codigoHtml += "&nbsp;";
                            break;
                        }
                        case '\n' -> {
                             SimbolosEsp(s, "\n", columna, linea,"Segun");s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        }
                        case '\t' -> {
                            SimbolosEsp(s, "\t", columna, linea,"Segun");s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        }
                        default -> validarCaracteres = true;
                    }
                    validarEstadosFinales("Segun", "green", validarCaracteres, letra, linea, columna, s);
                }

                
                
                 case "H1" -> {
                        switch (letra) {
                            case 'a' -> {
                                estado = "H2";
                                s.lexema = "Ha";
                            }
                            case '_' -> {
                                estado = "E";
                                s.lexema += "_";
                            }
                            case '+' -> {optimizar(s, "+", "red", "+", "mas", columna, linea); s = new Simbolo();}
                            case '-' -> {optimizar(s, "-", "red", "-", "menos", columna, linea);s = new Simbolo();}
                            case '*' -> {optimizar(s, "*", "red", "*", "multipli.", columna, linea);s = new Simbolo();}
                            case '/' -> {optimizar(s, "/", "red", "/", "division.", columna, linea);s = new Simbolo();}
                            case ',' -> {optimizar(s, ",", "purple", ",", "coma.", columna, linea);s = new Simbolo();}
                            case '<' -> {optimizar(s, "<", "red", "<", "menor", columna, linea);s = new Simbolo();}
                            case '>' -> {optimizar(s, ">", "red", ">", "mayor", columna, linea);s = new Simbolo();}
                            case '=' -> {optimizar(s, "=", "red", "=", "igual", columna, linea);s = new Simbolo();}
                            case '(' -> {optimizar(s, "(", "purple", "(", "par. abre", columna, linea);s = new Simbolo();}
                            case ')' -> {optimizar(s, ")", "purple", ")", "par. cierra", columna, linea);s = new Simbolo();}
                            case ':' -> {optimizar(s, ":", "purple", ":", "dos puntos", columna, linea);s = new Simbolo();}
                            case ' ' -> {
                                signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                                codigoHtml += "&nbsp;";
                                estado = "A";
                            }
                            case '\n' -> {
                                signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                                columna = 0;
                                linea++;
                                codigoHtml += "<br>";
                                estado = "A";
                            }
                            case '\t' -> {
                                signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                                codigoHtml += "&nbsp;&nbsp;&nbsp;";
                                estado = "A";
                            }
                            default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                 
                 case "H2" -> {
                        switch (letra) {
                            case 'c' -> {
                                estado = "H3";
                                s.lexema = "Hac";
                            }
                            case 's' -> {
                                estado = "H6";
                                s.lexema = "Has";
                            }
                            case '_' -> {
                                estado = "E";
                                s.lexema += "_";
                            }
                            case '+' -> {optimizar(s, "+", "red", "+", "mas", columna, linea); s = new Simbolo();}
                            case '-' -> {optimizar(s, "-", "red", "-", "menos", columna, linea);s = new Simbolo();}
                            case '*' -> {optimizar(s, "*", "red", "*", "multipli.", columna, linea);s = new Simbolo();}
                            case '/' -> {optimizar(s, "/", "red", "/", "division.", columna, linea);s = new Simbolo();}
                            case ',' -> {optimizar(s, ",", "purple", ",", "coma.", columna, linea);s = new Simbolo();}
                            case '<' -> {optimizar(s, "<", "red", "<", "menor", columna, linea);s = new Simbolo();}
                            case '>' -> {optimizar(s, ">", "red", ">", "mayor", columna, linea);s = new Simbolo();}
                            case '=' -> {optimizar(s, "=", "red", "=", "igual", columna, linea);s = new Simbolo();}
                            case '(' -> {optimizar(s, "(", "purple", "(", "par. abre", columna, linea);s = new Simbolo();}
                            case ')' -> {optimizar(s, ")", "purple", ")", "par. cierra", columna, linea);s = new Simbolo();}
                            case ':' -> {optimizar(s, ":", "purple", ":", "dos puntos", columna, linea);s = new Simbolo();}
                            case ' ' -> {
                                signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                                codigoHtml += "&nbsp;";
                                estado = "A";
                            }
                            case '\n' -> {
                                signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                                columna = 0;
                                linea++;
                                codigoHtml += "<br>";
                                estado = "A";
                            }
                            case '\t' -> {
                                signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                                codigoHtml += "&nbsp;&nbsp;&nbsp;";
                                estado = "A";
                            }
                             default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                 
                 case "H3" -> {
                        switch (letra) {
                            case 'e' -> {
                                estado = "H4";
                                s.lexema = "Hace";
                            }
                            case '_' -> {
                                estado = "E";
                                s.lexema += "_";
                            }
                            case '+' -> {optimizar(s, "+", "red", "+", "mas", columna, linea); s = new Simbolo();}
                            case '-' -> {optimizar(s, "-", "red", "-", "menos", columna, linea);s = new Simbolo();}
                            case '*' -> {optimizar(s, "*", "red", "*", "multipli.", columna, linea);s = new Simbolo();}
                            case '/' -> {optimizar(s, "/", "red", "/", "division.", columna, linea);s = new Simbolo();}
                            case ',' -> {optimizar(s, ",", "purple", ",", "coma.", columna, linea);s = new Simbolo();}
                            case '<' -> {optimizar(s, "<", "red", "<", "menor", columna, linea);s = new Simbolo();}
                            case '>' -> {optimizar(s, ">", "red", ">", "mayor", columna, linea);s = new Simbolo();}
                            case '=' -> {optimizar(s, "=", "red", "=", "igual", columna, linea);s = new Simbolo();}
                            case '(' -> {optimizar(s, "(", "purple", "(", "par. abre", columna, linea);s = new Simbolo();}
                            case ')' -> {optimizar(s, ")", "purple", ")", "par. cierra", columna, linea);s = new Simbolo();}
                            case ':' -> {optimizar(s, ":", "purple", ":", "dos puntos", columna, linea);s = new Simbolo();}
                            case ' ' -> {
                                signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                                codigoHtml += "&nbsp;";
                                estado = "A";
                            }
                            case '\n' -> {
                                signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                                columna = 0;
                                linea++;
                                codigoHtml += "<br>";
                                estado = "A";
                            }
                            case '\t' -> {
                                signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                                codigoHtml += "&nbsp;&nbsp;&nbsp;";
                                estado = "A";
                            }
                             default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                 case "H4" -> {
                        switch (letra) {
                            case 'r' -> {
                                estado = "H5";
                                s.lexema = "Hacer";
                            }
                            case '_' -> {
                                estado = "E";
                                s.lexema += "_";
                            }
                            case '+' -> {optimizar(s, "+", "red", "+", "mas", columna, linea); s = new Simbolo();}
                            case '-' -> {optimizar(s, "-", "red", "-", "menos", columna, linea);s = new Simbolo();}
                            case '*' -> {optimizar(s, "*", "red", "*", "multipli.", columna, linea);s = new Simbolo();}
                            case '/' -> {optimizar(s, "/", "red", "/", "division.", columna, linea);s = new Simbolo();}
                            case ',' -> {optimizar(s, ",", "purple", ",", "coma.", columna, linea);s = new Simbolo();}
                            case '<' -> {optimizar(s, "<", "red", "<", "menor", columna, linea);s = new Simbolo();}
                            case '>' -> {optimizar(s, ">", "red", ">", "mayor", columna, linea);s = new Simbolo();}
                            case '=' -> {optimizar(s, "=", "red", "=", "igual", columna, linea);s = new Simbolo();}
                            case '(' -> {optimizar(s, "(", "purple", "(", "par. abre", columna, linea);s = new Simbolo();}
                            case ')' -> {optimizar(s, ")", "purple", ")", "par. cierra", columna, linea);s = new Simbolo();}
                            case ':' -> {optimizar(s, ":", "purple", ":", "dos puntos", columna, linea);s = new Simbolo();}
                            case ' ' -> {
                                signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                                codigoHtml += "&nbsp;";
                                estado = "A";
                            }
                            case '\n' -> {
                                signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                                columna = 0;
                                linea++;
                                codigoHtml += "<br>";
                                estado = "A";
                            }
                            case '\t' -> {
                                signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                                codigoHtml += "&nbsp;&nbsp;&nbsp;";
                                estado = "A";
                            }
                            default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                 
                 
                 case "H5" -> {
                    switch (letra) {
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                        }
                        
                        case '+' -> {
                            //EstadosFinales(String token,String tokensimbolo, String color,Simbolo s,String simb,String expresion, int columna, int linea)
                            EstadosFinales("Hacer", "mas", "red", s,"+","+", columna, linea);s = new Simbolo();
                        }
                        case '-' -> {
                             EstadosFinales("Hacer", "menos", "red", s,"-","-", columna, linea);s = new Simbolo();
                        }
                        case '<' -> {
                             EstadosFinales("Hacer", "menor", "red", s,"<","<", columna, linea);s = new Simbolo();
                        }
                        case '>' -> {
                            EstadosFinales("Hacer", "mayor", "red", s,">",">", columna, linea);s = new Simbolo();
                        }
                        case '=' -> {
                            EstadosFinales("Hacer", "igual", "red", s,"=","=", columna, linea);s = new Simbolo();
                        }
                        case '(' -> {
                            EstadosFinales("Hacer", "par abre", "purple", s,"(","(", columna, linea);s = new Simbolo();
                        }
                        case ')' -> {
                            EstadosFinales("Hacer", "par cierra", "purple", s,")",")", columna, linea);s = new Simbolo();
                        }
                        case ':' -> {
                           EstadosFinales("Hacer", "dos puntos", "purple", s,":",":", columna, linea);s = new Simbolo();
                        }
                        case ' ' -> {
                            //SimbolosEsp(Simbolo s, String letra,int columna, int linea)
                            SimbolosEsp(s, " ", columna, linea,"Hacer");s = new Simbolo();
                            codigoHtml += "&nbsp;";
                            break;
                        }
                        case '\n' -> {
                             SimbolosEsp(s, "\n", columna, linea,"Hacer");s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        }
                        case '\t' -> {
                            SimbolosEsp(s, "\t", columna, linea,"Hacer");s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        }
                        default -> validarCaracteres = true;
                    }
                    validarEstadosFinales("Hacer", "green", validarCaracteres, letra, linea, columna, s);
                }
                 
                 case "H6" -> {
                        switch (letra) {
                           
                            case 't' -> {
                                estado = "H7";
                                s.lexema = "Hast";
                            }
                            case '_' -> {
                                estado = "E";
                                s.lexema += "_";
                            }
                            case '+' -> {optimizar(s, "+", "red", "+", "mas", columna, linea); s = new Simbolo();}
                            case '-' -> {optimizar(s, "-", "red", "-", "menos", columna, linea);s = new Simbolo();}
                            case '*' -> {optimizar(s, "*", "red", "*", "multipli.", columna, linea);s = new Simbolo();}
                            case '/' -> {optimizar(s, "/", "red", "/", "division.", columna, linea);s = new Simbolo();}
                            case ',' -> {optimizar(s, ",", "purple", ",", "coma.", columna, linea);s = new Simbolo();}
                            case '<' -> {optimizar(s, "<", "red", "<", "menor", columna, linea);s = new Simbolo();}
                            case '>' -> {optimizar(s, ">", "red", ">", "mayor", columna, linea);s = new Simbolo();}
                            case '=' -> {optimizar(s, "=", "red", "=", "igual", columna, linea);s = new Simbolo();}
                            case '(' -> {optimizar(s, "(", "purple", "(", "par. abre", columna, linea);s = new Simbolo();}
                            case ')' -> {optimizar(s, ")", "purple", ")", "par. cierra", columna, linea);s = new Simbolo();}
                            case ':' -> {optimizar(s, ":", "purple", ":", "dos puntos", columna, linea);s = new Simbolo();}
                            case ' ' -> {
                                signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                                codigoHtml += "&nbsp;";
                                estado = "A";
                            }
                            case '\n' -> {
                                signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                                columna = 0;
                                linea++;
                                codigoHtml += "<br>";
                                estado = "A";
                            }
                            case '\t' -> {
                                signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                                codigoHtml += "&nbsp;&nbsp;&nbsp;";
                                estado = "A";
                            }
                             default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                 case "H7" -> {
                        switch (letra) {
                           
                            case 'a' -> {
                                estado = "H8";
                                s.lexema = "Hasta";
                            }
                            case '_' -> {
                                estado = "E";
                                s.lexema += "_";
                            }
                            case '+' -> {optimizar(s, "+", "red", "+", "mas", columna, linea); s = new Simbolo();}
                            case '-' -> {optimizar(s, "-", "red", "-", "menos", columna, linea);s = new Simbolo();}
                            case '*' -> {optimizar(s, "*", "red", "*", "multipli.", columna, linea);s = new Simbolo();}
                            case '/' -> {optimizar(s, "/", "red", "/", "division.", columna, linea);s = new Simbolo();}
                            case ',' -> {optimizar(s, ",", "purple", ",", "coma.", columna, linea);s = new Simbolo();}
                            case '<' -> {optimizar(s, "<", "red", "<", "menor", columna, linea);s = new Simbolo();}
                            case '>' -> {optimizar(s, ">", "red", ">", "mayor", columna, linea);s = new Simbolo();}
                            case '=' -> {optimizar(s, "=", "red", "=", "igual", columna, linea);s = new Simbolo();}
                            case '(' -> {optimizar(s, "(", "purple", "(", "par. abre", columna, linea);s = new Simbolo();}
                            case ')' -> {optimizar(s, ")", "purple", ")", "par. cierra", columna, linea);s = new Simbolo();}
                            case ':' -> {optimizar(s, ":", "purple", ":", "dos puntos", columna, linea);s = new Simbolo();}
                            case ' ' -> {
                                signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                                codigoHtml += "&nbsp;";
                                estado = "A";
                            }
                            case '\n' -> {
                                signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                                columna = 0;
                                linea++;
                                codigoHtml += "<br>";
                                estado = "A";
                            }
                            case '\t' -> {
                                signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                                codigoHtml += "&nbsp;&nbsp;&nbsp;";
                                estado = "A";
                            }
                            default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                 
                  case "H8" -> {
                    switch (letra) {
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                        }
                        
                         case '+' -> {
                            //EstadosFinales(String token,String tokensimbolo, String color,Simbolo s,String simb,String expresion, int columna, int linea)
                            EstadosFinales("Hasta", "mas", "red", s,"+","+", columna, linea);s = new Simbolo();
                        }
                        case '-' -> {
                             EstadosFinales("Hasta", "menos", "red", s,"-","-", columna, linea);s = new Simbolo();
                        }
                        case '<' -> {
                             EstadosFinales("Hasta", "menor", "red", s,"<","<", columna, linea);s = new Simbolo();
                        }
                        case '>' -> {
                            EstadosFinales("Hasta", "mayor", "red", s,">",">", columna, linea);s = new Simbolo();
                        }
                        case '=' -> {
                            EstadosFinales("Hasta", "igual", "red", s,"=","=", columna, linea);s = new Simbolo();
                        }
                        case '(' -> {
                            EstadosFinales("Hasta", "par abre", "purple", s,"(","(", columna, linea);s = new Simbolo();
                        }
                        case ')' -> {
                            EstadosFinales("Hasta", "par cierra", "purple", s,")",")", columna, linea);s = new Simbolo();
                        }
                        case ':' -> {
                           EstadosFinales("Hasta", "dos puntos", "purple", s,":",":", columna, linea);s = new Simbolo();
                        }
                        case ' ' -> {
                            //SimbolosEsp(Simbolo s, String letra,int columna, int linea)
                            SimbolosEsp(s, " ", columna, linea,"Hasta");s = new Simbolo();
                            codigoHtml += "&nbsp;";
                            break;
                        }
                        case '\n' -> {
                             SimbolosEsp(s, "\n", columna, linea,"Hasta");s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        }
                        case '\t' -> {
                            SimbolosEsp(s, "\t", columna, linea,"Hasta");s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        }
                        default -> validarCaracteres = true;
                    }
                    validarEstadosFinales("Hasta", "green", validarCaracteres, letra, linea, columna, s);
                }
                
                
                    case "T" -> {
                        switch (letra) {
                            case 'i' -> {
                                estado = "W";
                                s.lexema = "Di";
                            }
                            case '_' -> {
                                estado = "E";
                                s.lexema += "_";
                            }
                            
                             default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }

                    
                    case "W" -> {
                        switch (letra) {
                            case 'm' -> {
                                estado = "V";
                                s.lexema = "Dim";
                            }
                            case '_' -> {
                                estado = "E";
                                s.lexema += "_";
                            }
                            
                             default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                    
                    
                case "V" -> {
                    switch (letra) {
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                        }
                        
                        case ' ' -> {
                            //SimbolosEsp(Simbolo s, String letra,int columna, int linea)
                            SimbolosEsp(s, " ", columna, linea,"Dim");s = new Simbolo();
                            codigoHtml += "&nbsp;";
                            break;
                        }
                        case '\n' -> {
                             SimbolosEsp(s, "\n", columna, linea,"Dim");s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        }
                        case '\t' -> {
                            SimbolosEsp(s, "\t", columna, linea,"Dim");s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        }
                        default -> validarCaracteres = true;
                    }
                    validarEstadosFinales("Dim", "green", validarCaracteres, letra, linea, columna, s);
                }
                    
                     case "P" -> {
                         switch (letra) {
                             case 's' -> {
                                 estado = "Y";
                                 s.lexema = "As";
                             }
                             case '_' -> {
                                 estado = "E";
                                 s.lexema += "_";
                             }
                            
                              default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                    
                case "Y" -> {
                    switch (letra) {
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                        
                        case ' ' -> {
                            //SimbolosEsp(Simbolo s, String letra,int columna, int linea)
                            SimbolosEsp(s, " ", columna, linea,"As");s = new Simbolo();
                            codigoHtml += "&nbsp;";
                            break;
                        }
                        case '\n' -> {
                             SimbolosEsp(s, "\n", columna, linea,"As");s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        }
                        case '\t' -> {
                            SimbolosEsp(s, "\t", columna, linea,"As");s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        }
                        default -> validarCaracteres = true;
                    }
                    validarEstadosFinales("As", "green", validarCaracteres, letra, linea, columna, s);
                }
                
 //--------------------------------------------------------------------------                   
                    
                       case "O" -> {
                           switch (letra) {
                               case 'n' -> {
                                   estado = "N";
                                   s.lexema = "En";
                               }
                               case 's' -> {
                                   estado = "S1";
                                   s.lexema = "Es";
                               }
                               case '_' -> {
                                   estado = "E";
                                   s.lexema += "_";
                               }
                               
                                default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }

                    
                    case "N" -> {
                        switch (letra) {
                            case 't' -> {
                                estado = "R";
                                s.lexema = "Ent";
                            }
                            case '_' -> {
                                estado = "E";
                                s.lexema += "_";
                            }
                            
                             default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                    
                    case "R" -> {
                        switch (letra) {
                            case 'e' -> {
                                estado = "Q";
                                s.lexema = "Ente";
                            }
                            case 'o' -> {
                                estado = "Q1";
                                s.lexema = "Ento";
                            }
                            
                            
                            case '_' -> {
                                estado = "E";
                                s.lexema += "_";
                            }
                            
                             default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }

                    
                    
                    case "Q" -> {
                        switch (letra) {
                            case 'r' -> {
                                estado = "S";
                                s.lexema = "Enter";
                            }
                            case '_' -> {
                                estado = "E";
                                s.lexema += "_";
                            }
                            
                            default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                    
                    
                    case "S" -> {
                        switch (letra) {
                            case 'o' -> {
                                estado = "M";
                                s.lexema = "Entero";
                            }
                            case '_' -> {
                                estado = "E";
                                s.lexema += "_";
                            }
                            
                            default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }

                    
                    
                case "M" -> {
                    switch (letra) {
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        case ' ' -> {
                            //SimbolosEsp(Simbolo s, String letra,int columna, int linea)
                            SimbolosEsp(s, " ", columna, linea,"Entero");s = new Simbolo();
                            codigoHtml += "&nbsp;";
                            break;
                        }
                        case '\n' -> {
                             SimbolosEsp(s, "\n", columna, linea,"Entero");s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        }
                        case '\t' -> {
                            SimbolosEsp(s, "\t", columna, linea,"Entero");s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        }
                        default -> validarCaracteres = true;
                    }
                    validarEstadosFinales("Entero", "green", validarCaracteres, letra, linea, columna, s);
                }
                    
                
                case "Q1" -> {
                        switch (letra) {
                            case 'n' -> {
                                estado = "Q2";
                                s.lexema = "Enton";
                            }
                            case '_' -> {
                                estado = "E";
                                s.lexema += "_";
                            }
                            
                            default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                
                 case "Q2" -> {
                        switch (letra) {
                            case 'c' -> {
                                estado = "Q3";
                                s.lexema = "Entonc";
                            }
                            case '_' -> {
                                estado = "E";
                                s.lexema += "_";
                            }
                            
                             default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                 
                 case "Q3" -> {
                        switch (letra) {
                            case 'e' -> {
                                estado = "Q4";
                                s.lexema = "Entonce";
                            }
                            case '_' -> {
                                estado = "E";
                                s.lexema += "_";
                            }
                            
                             default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                 
                 case "Q4" -> {
                        switch (letra) {
                            case 's' -> {
                                estado = "Q5";
                                s.lexema = "Entonces";
                            }
                            case '_' -> {
                                estado = "E";
                                s.lexema += "_";
                            }
                            
                             default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                 
                  case "Q5" -> {
                    switch (letra) {
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                        
                        case ' ' -> {
                            //SimbolosEsp(Simbolo s, String letra,int columna, int linea)
                            SimbolosEsp(s, " ", columna, linea,"Entonces");
                            codigoHtml += "&nbsp;";
                            break;
                        }
                        case '\n' -> {
                             SimbolosEsp(s, "\n", columna, linea,"Entonces");
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        }
                        case '\t' -> {
                            SimbolosEsp(s, "\t", columna, linea,"Entonces");
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        }
                        default -> validarCaracteres = true;
                    }
                    validarEstadosFinales("Entonces", "green", validarCaracteres, letra, linea, columna, s);
                }
                  
                  case "S1" -> {
                           switch (letra) {
                               
                               case 'c' -> {
                                   estado = "S2";
                                   s.lexema = "Esc";
                               }
                               case '_' -> {
                                   estado = "E";
                                   s.lexema += "_";
                               }
                               
                                default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                  case "S2" -> {
                           switch (letra) {
                               
                               case 'r' -> {
                                   estado = "S3";
                                   s.lexema = "Escr";
                               }
                               case '_' -> {
                                   estado = "E";
                                   s.lexema += "_";
                               }
                               
                               default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                  case "S3" -> {
                           switch (letra) {
                               
                               case 'i' -> {
                                   estado = "S4";
                                   s.lexema = "Escri";
                               }
                               case '_' -> {
                                   estado = "E";
                                   s.lexema += "_";
                               }
                               
                                default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                  case "S4" -> {
                           switch (letra) {
                               
                               case 'b' -> {
                                   estado = "S5";
                                   s.lexema = "Escrib";
                               }
                               case '_' -> {
                                   estado = "E";
                                   s.lexema += "_";
                               }
                               
                               default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                  case "S5" -> {
                           switch (letra) {
                               
                               case 'i' -> {
                                   estado = "S6";
                                   s.lexema = "Escribi";
                               }
                               case '_' -> {
                                   estado = "E";
                                   s.lexema += "_";
                               }
                              
                                default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                  case "S6" -> {
                           switch (letra) {
                               
                               case 'r' -> {
                                   estado = "S7";
                                   s.lexema = "Escribir";
                               }
                               case '_' -> {
                                   estado = "E";
                                   s.lexema += "_";
                               }
                               
                                default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                  
                  case "S7" -> {
                    switch (letra) {
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                        
                        case ' ' -> {
                            //SimbolosEsp(Simbolo s, String letra,int columna, int linea)
                            SimbolosEsp(s, " ", columna, linea,"Escribir");s = new Simbolo();
                            codigoHtml += "&nbsp;";
                            break;
                        }
                        case '\n' -> {
                             SimbolosEsp(s, "\n", columna, linea,"Escribir");s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        }
                        case '\t' -> {
                            SimbolosEsp(s, "\t", columna, linea,"Escribir");s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        }
                        default -> validarCaracteres = true;
                    }
                    validarEstadosFinales("Escribir", "green", validarCaracteres, letra, linea, columna, s);
                }

                   
                    
                       case "X" -> {
                           switch (letra) {
                               case 'a' -> {
                                   estado = "d";
                                   s.lexema = "Ca";
                               }
                               case 'o' -> {
                                   estado = "CO1";
                                   s.lexema = "Co";
                               }
                               case '_' -> {
                                   estado = "E";
                                   s.lexema += "_";
                               }
                               
                               default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                       case "CO1" -> {
                           switch (letra) {
                               
                               case 'm' -> {
                                   estado = "CO2";
                                   s.lexema = "Com";
                               }
                               case '_' -> {
                                   estado = "E";
                                   s.lexema += "_";
                               }
                               
                                default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                       case "CO2" -> {
                           switch (letra) {
                               
                               case 'o' -> {
                                   estado = "CO3";
                                   s.lexema = "Como";
                               }
                               case '_' -> {
                                   estado = "E";
                                   s.lexema += "_";
                               }
                               
                                default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }

                       
                        case "CO3" -> {
                    switch (letra) {
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                        case ' ' -> {
                            //SimbolosEsp(Simbolo s, String letra,int columna, int linea)
                            SimbolosEsp(s, " ", columna, linea,"Como");s = new Simbolo();
                            codigoHtml += "&nbsp;";
                            break;
                        }
                        case '\n' -> {
                             SimbolosEsp(s, "\n", columna, linea,"Como");s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        }
                        case '\t' -> {
                            SimbolosEsp(s, "\t", columna, linea,"Como");s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        }
                        default -> validarCaracteres = true;
                    }
                    validarEstadosFinales("Como", "green", validarCaracteres, letra, linea, columna, s);
                }

                    
                    case "d" -> {
                        switch (letra) {
                            case 'd' -> {
                                estado = "U";
                                s.lexema = "Cad";
                            }
                            case 's' -> {
                                estado = "U1";
                                s.lexema = "Cas";
                            }
                            case '_' -> {
                                estado = "E";
                                s.lexema += "_";
                            }
                            
                            default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }

                    
                    case "U" -> {
                        switch (letra) {
                            case 'e' -> {
                                estado = "i";
                                s.lexema = "Cade";
                            }
                            case '_' -> {
                                estado = "E";
                                s.lexema += "_";
                            }
                            
                             default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }

                    
                    
                    case "i" -> {
                        switch (letra) {
                            case 'n' -> {
                                estado = "p";
                                s.lexema = "Caden";
                            }
                            case '_' -> {
                                estado = "E";
                                s.lexema += "_";
                            }
                           
                             default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }

                    
                    
                    case "p" -> {
                        switch (letra) {
                            case 'a' -> {
                                estado = "f";
                                s.lexema = "Cadena";
                            }
                            case '_' -> {
                                estado = "E";
                                s.lexema += "_";
                            }
                            
                             default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }

                    
                    
                case "f" -> {
                    switch (letra) {
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                        
                        case ' ' -> {
                            //SimbolosEsp(Simbolo s, String letra,int columna, int linea)
                            SimbolosEsp(s, " ", columna, linea,"Cadena");s = new Simbolo();
                            codigoHtml += "&nbsp;";
                            break;
                        }
                        case '\n' -> {
                             SimbolosEsp(s, "\n", columna, linea,"Cadena");s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        }
                        case '\t' -> {
                            SimbolosEsp(s, "\t", columna, linea,"Cadena");s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        }
                        default -> validarCaracteres = true;
                    }
                    validarEstadosFinales("Cadena", "green", validarCaracteres, letra, linea, columna, s);
                }
                
                   case "U1" -> {
                        switch (letra) {
                            
                            case 'o' -> {
                                estado = "U2";
                                s.lexema = "Caso";
                            }
                            case '_' -> {
                                estado = "E";
                                s.lexema += "_";
                            }
                            
                             default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                    
                   
                      
                    
                case "U2" -> {
                    switch (letra) {
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                        case 'E' -> {
                            estado = "E1";
                            s.lexema = "CasoE";
                        }
                        
                        case ' ' -> {
                            //SimbolosEsp(Simbolo s, String letra,int columna, int linea)
                            SimbolosEsp(s, " ", columna, linea,"Caso");s = new Simbolo();
                            codigoHtml += "&nbsp;";
                            break;
                        }
                        case '\n' -> {
                             SimbolosEsp(s, "\n", columna, linea,"Caso");s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        }
                        case '\t' -> {
                            SimbolosEsp(s, "\t", columna, linea,"Caso");s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        }
                        default -> validarCaracteres = true;
                    }
                    validarEstadosFinales("Caso", "green", validarCaracteres, letra, linea, columna, s);
                }
                
                  case "E1" -> {
                        switch (letra) {
                            
                            case 'l' -> {
                                estado = "E2";
                                s.lexema = "CasoEl";
                            }
                            case '_' -> {
                                estado = "E";
                                s.lexema += "_";
                            }
                            
                             default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                  case "E2" -> {
                        switch (letra) {
                            
                            case 's' -> {
                                estado = "E3";
                                s.lexema = "CasoEls";
                            }
                            case '_' -> {
                                estado = "E";
                                s.lexema += "_";
                            }
                            
                             default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                  case "E3" -> {
                        switch (letra) {
                            
                            case 'e' -> {
                                estado = "E4";
                                s.lexema = "CasoElse";
                            }
                            case '_' -> {
                                estado = "E";
                                s.lexema += "_";
                            }
                            
                             default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                  
                  case "E4" -> {
                    switch (letra) {
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        case ' ' -> {
                            //SimbolosEsp(Simbolo s, String letra,int columna, int linea)
                            SimbolosEsp(s, " ", columna, linea,"CasoElse");s = new Simbolo();
                            codigoHtml += "&nbsp;";
                            break;
                        }
                        case '\n' -> {
                             SimbolosEsp(s, "\n", columna, linea,"CasoElse");s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        }
                        case '\t' -> {
                            SimbolosEsp(s, "\t", columna, linea,"CasoElse");s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        }
                        default -> validarCaracteres = true;
                    }
                    validarEstadosFinales("CasoElse", "green", validarCaracteres, letra, linea, columna, s);
                }
                    
                case "C" -> {
                    switch (letra) {
                        case 'e' -> {
                            estado = "H";
                            s.lexema = "Le";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                case "H" -> {
                    switch (letra) {
                        case 'e' -> {
                            estado = "I";
                            s.lexema = "Lee";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                case "I" -> {
                    switch (letra) {
                        case 'r' -> {
                            estado = "J";
                            s.lexema = "Leer";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                case "J" -> {
                    switch (letra) {
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                        
                        case ' ' -> {
                            //SimbolosEsp(Simbolo s, String letra,int columna, int linea)
                            SimbolosEsp(s, " ", columna, linea,"Leer");
                            codigoHtml += "&nbsp;";
                            break;
                        }
                        case '\n' -> {
                             SimbolosEsp(s, "\n", columna, linea,"Leer");
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        }
                        case '\t' -> {
                            SimbolosEsp(s, "\t", columna, linea,"Leer");
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        }
                        default -> validarCaracteres = true;
                    }
                    validarEstadosFinales("Leer", "green", validarCaracteres, letra, linea, columna, s);
                }
                case "D" -> {
                    switch (letra) {
                        case 'a' -> {
                            estado = "K";
                            s.lexema = "Pa";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                        
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                case "K" -> {
                    switch (letra) {
                        case 'r' -> {
                            estado = "L";
                            s.lexema = "Par";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                        
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                case "L" -> {
                    switch (letra) {
                        case 'a' -> {
                            estado = "L1";
                            s.lexema = "Para";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                       
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                case "L1" -> {
                    switch (letra) {
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                        
                        case ' ' -> {
                            //SimbolosEsp(Simbolo s, String letra,int columna, int linea)
                            SimbolosEsp(s, " ", columna, linea,"Para");s = new Simbolo();
                            codigoHtml += "&nbsp;";
                            break;
                        }
                        case '\n' -> {
                             SimbolosEsp(s, "\n", columna, linea,"Para");s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        }
                        case '\t' -> {
                            SimbolosEsp(s, "\t", columna, linea,"Para");s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        }
                        default -> validarCaracteres = true;
                    }
                    validarEstadosFinales("Para", "green", validarCaracteres, letra, linea, columna, s);
                }
                
                case "M1" -> {
                    switch (letra) {
                        case 'i' -> {
                            estado = "M2";
                            s.lexema = "Mi";
                    }
                        case 'e' -> {
                            estado = "ME1";
                            s.lexema = "Me";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }

                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                case "M2" -> {
                    switch (letra) {
                        case 'e' -> {
                            estado = "M3";
                            s.lexema = "Mie";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        case '\t' -> {
                            signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            estado = "A";
                        }
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                case "M3" -> {
                    switch (letra) {
                        case 'n' -> {
                            estado = "M4";
                            s.lexema = "Mien";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                       
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                case "M4" -> {
                    switch (letra) {
                        case 't' -> {
                            estado = "M5";
                            s.lexema = "Mient";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                       
                        case '\t' -> {
                            signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            estado = "A";
                        }
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                case "M5" -> {
                    switch (letra) {
                        case 'r' -> {
                            estado = "M6";
                            s.lexema = "Mientr";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                case "M6" -> {
                    switch (letra) {
                        case 'a' -> {
                            estado = "M7";
                            s.lexema = "Mientra";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                       
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                case "M7" -> {
                    switch (letra) {
                        case 's' -> {
                            estado = "M8";
                            s.lexema = "Mientras";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                
                case "M8" -> {
                    switch (letra) {
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        case ' ' -> {
                            //SimbolosEsp(Simbolo s, String letra,int columna, int linea)
                            SimbolosEsp(s, " ", columna, linea,"Mientras");s = new Simbolo();
                            codigoHtml += "&nbsp;";
                            break;
                        }
                        case '\n' -> {
                             SimbolosEsp(s, "\n", columna, linea,"Mientras");s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        }
                        case '\t' -> {
                            SimbolosEsp(s, "\t", columna, linea,"Mientras");s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        }
                        default -> validarCaracteres = true;
                    }
                    validarEstadosFinales("Mientras", "green", validarCaracteres, letra, linea, columna, s);
                }
                
                case "ME1" -> {
                    switch (letra) {
                        case 't' -> {
                            estado = "ME2";
                            s.lexema = "Met";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        case ' ' -> {
                            signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                            codigoHtml += "&nbsp;";
                            estado = "A";
                        }
                        case '\n' -> {
                            signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            estado = "A";
                        }
                        case '\t' -> {
                            signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            estado = "A";
                        }
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                case "ME2" -> {
                    switch (letra) {
                        case 'o' -> {
                            estado = "ME3";
                            s.lexema = "Meto";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        case ' ' -> {
                            signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                            codigoHtml += "&nbsp;";
                            estado = "A";
                        }
                        case '\n' -> {
                            signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            estado = "A";
                        }
                        case '\t' -> {
                            signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            estado = "A";
                        }
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                case "ME3" -> {
                    switch (letra) {
                        case 'd' -> {
                            estado = "ME4";
                            s.lexema = "Metod";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        case ' ' -> {
                            signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                            codigoHtml += "&nbsp;";
                            estado = "A";
                        }
                        case '\n' -> {
                            signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            estado = "A";
                        }
                        case '\t' -> {
                            signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            estado = "A";
                        }
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                case "ME4" -> {
                    switch (letra) {
                        case 'o' -> {
                            estado = "ME5";
                            s.lexema = "Metodo";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        case ' ' -> {
                            signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                            codigoHtml += "&nbsp;";
                            estado = "A";
                        }
                        case '\n' -> {
                            signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            estado = "A";
                        }
                        case '\t' -> {
                            signosValidacion(s, "blue", s.lexema, s.lexema, "identificador", columna, linea);
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            estado = "A";
                        }
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                
                case "ME5" -> {
                    switch (letra) {
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        case ' ' -> {
                            //SimbolosEsp(Simbolo s, String letra,int columna, int linea)
                            SimbolosEsp(s, " ", columna, linea,"Metodo");s = new Simbolo();
                            codigoHtml += "&nbsp;";
                            break;
                        }
                        case '\n' -> {
                             SimbolosEsp(s, "\n", columna, linea,"Metodo");s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        }
                        case '\t' -> {
                            SimbolosEsp(s, "\t", columna, linea,"Metodo");s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        }
                        default -> validarCaracteres = true;
                    }
                    validarEstadosFinales("Metodo", "green", validarCaracteres, letra, linea, columna, s);
                }
                
                case "N1" -> {
                    switch (letra) {
                        case 'u' -> {
                            estado = "N2";
                            s.lexema = "Nu";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                case "N2" -> {
                    switch (letra) {
                        case 'm' -> {
                            estado = "N3";
                            s.lexema = "Num";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                case "N3" -> {
                    switch (letra) {
                        case 'e' -> {
                            estado = "N4";
                            s.lexema = "Nume";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                case "N4" -> {
                    switch (letra) {
                        case 'r' -> {
                            estado = "N5";
                            s.lexema = "Numer";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                case "N5" -> {
                    switch (letra) {
                        case 'o' -> {
                            estado = "N6";
                            s.lexema = "Numero";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                
                case "N6" -> {
                    switch (letra) {
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                        case ' ' -> {
                            //SimbolosEsp(Simbolo s, String letra,int columna, int linea)
                            SimbolosEsp(s, " ", columna, linea,"Numero");s = new Simbolo();
                            codigoHtml += "&nbsp;";
                            break;
                        }
                        case '\n' -> {
                             SimbolosEsp(s, "\n", columna, linea,"Numero");s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        }
                        case '\t' -> {
                            SimbolosEsp(s, "\t", columna, linea,"Numero");s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        }
                        default -> validarCaracteres = true;
                    }
                    validarEstadosFinales("Numero", "green", validarCaracteres, letra, linea, columna, s);
                }
                
                case "F1" -> {
                    switch (letra) {
                        case 'u' -> {
                            estado = "F2";
                            s.lexema = "Fu";
                    }
                        case 'i' -> {
                            estado = "FI1";
                            s.lexema = "Fi";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                case "F2" -> {
                    switch (letra) {
                        case 'n' -> {
                            estado = "F3";
                            s.lexema = "Fun";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                case "F3" -> {
                    switch (letra) {
                        case 'c' -> {
                            estado = "F4";
                            s.lexema = "Func";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                case "F4" -> {
                    switch (letra) {
                        case 'i' -> {
                            estado = "F5";
                            s.lexema = "Funci";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                case "F5" -> {
                    switch (letra) {
                        case 'o' -> {
                            estado = "F6";
                            s.lexema = "Funcio";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                case "F6" -> {
                    switch (letra) {
                        case 'n' -> {
                            estado = "F7";
                            s.lexema = "Funcion";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                
                case "F7" -> {
                    switch (letra) {
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                        case ' ' -> {
                            //SimbolosEsp(Simbolo s, String letra,int columna, int linea)
                            SimbolosEsp(s, " ", columna, linea,"Funcion");s = new Simbolo();
                            codigoHtml += "&nbsp;";
                            break;
                        }
                        case '\n' -> {
                             SimbolosEsp(s, "\n", columna, linea,"Funcion");s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        }
                        case '\t' -> {
                            SimbolosEsp(s, "\t", columna, linea,"Funcion");s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        }
                        default -> validarCaracteres = true;
                    }
                    validarEstadosFinales("Funcion", "green", validarCaracteres, letra, linea, columna, s);
                }
                
                case "FI1" -> {
                    switch (letra) {
                        case 'n' -> {
                            estado = "FI2";
                            s.lexema = "Fin";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                case "FI2" -> {
                    switch (letra) {
                        case 'S' -> {
                            estado = "FI3";
                            s.lexema = "FinS";
                    }
                        case 'F' -> {
                            estado = "FINF1";
                            s.lexema = "FinF";
                    }
                        case 'M' -> {
                            estado = "FINME1";
                            s.lexema = "FinM";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                case "FI3" -> {
                    switch (letra) {
                        case 'i' -> {
                            estado = "FI4";
                            s.lexema = "FinSi";
                    }
                        case 'e' -> {
                            estado = "FSE1";
                            s.lexema = "FinSe";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                
                case "FI4" -> {
                    switch (letra) {
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                        case ' ' -> {
                            //SimbolosEsp(Simbolo s, String letra,int columna, int linea)
                            SimbolosEsp(s, " ", columna, linea,"FinSi");s = new Simbolo();
                            codigoHtml += "&nbsp;";
                            break;
                        }
                        case '\n' -> {
                             SimbolosEsp(s, "\n", columna, linea,"FinSi");s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        }
                        case '\t' -> {
                            SimbolosEsp(s, "\t", columna, linea,"FinSi");s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        }
                        default -> validarCaracteres = true;
                    }
                    validarEstadosFinales("FinSi", "green", validarCaracteres, letra, linea, columna, s);
                }
                
                case "FSE1" -> {
                    switch (letra) {
                        case 'g' -> {
                            estado = "FSE2";
                            s.lexema = "FinSeg";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                case "FSE2" -> {
                    switch (letra) {
                        case 'u' -> {
                            estado = "FSE3";
                            s.lexema = "FinSegu";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                case "FSE3" -> {
                    switch (letra) {
                        case 'n' -> {
                            estado = "FSE4";
                            s.lexema = "FinSegun";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                
                case "FSE4" -> {
                    switch (letra) {
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                        case ' ' -> {
                            //SimbolosEsp(Simbolo s, String letra,int columna, int linea)
                            SimbolosEsp(s, " ", columna, linea,"FinSegun");s = new Simbolo();
                            codigoHtml += "&nbsp;";
                            break;
                        }
                        case '\n' -> {
                             SimbolosEsp(s, "\n", columna, linea,"FinSegun");s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        }
                        case '\t' -> {
                            SimbolosEsp(s, "\t", columna, linea,"FinSegun");s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        }
                        default -> validarCaracteres = true;
                    }
                    validarEstadosFinales("FinSegun", "green", validarCaracteres, letra, linea, columna, s);
                }
                
                case "FINF1" -> {
                    switch (letra) {
                        case 'u' -> {
                            estado = "FINF2";
                            s.lexema = "FinFu";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                case "FINF2" -> {
                    switch (letra) {
                        case 'n' -> {
                            estado = "FINF3";
                            s.lexema = "FinFun";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                case "FINF3" -> {
                    switch (letra) {
                        case 'c' -> {
                            estado = "FINF4";
                            s.lexema = "FinFunc";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                case "FINF4" -> {
                    switch (letra) {
                        case 'i' -> {
                            estado = "FINF5";
                            s.lexema = "FinFunci";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                case "FINF5" -> {
                    switch (letra) {
                        case 'o' -> {
                            estado = "FINF6";
                            s.lexema = "FinFuncio";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                case "FINF6" -> {
                    switch (letra) {
                        case 'n' -> {
                            estado = "FINF7";
                            s.lexema = "FinFuncion";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                
                case "FINF7" -> {
                    switch (letra) {
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                        case ' ' -> {
                            //SimbolosEsp(Simbolo s, String letra,int columna, int linea)
                            SimbolosEsp(s, " ", columna, linea,"FinFuncion");s = new Simbolo();
                            codigoHtml += "&nbsp;";
                            break;
                        }
                        case '\n' -> {
                             SimbolosEsp(s, "\n", columna, linea,"FinFuncion");s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        }
                        case '\t' -> {
                            SimbolosEsp(s, "\t", columna, linea,"FinFuncion");s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        }
                        default -> validarCaracteres = true;
                    }
                    validarEstadosFinales("FinFuncion", "green", validarCaracteres, letra, linea, columna, s);
                }
                
                case "FINME1" -> {
                    switch (letra) {
                        case 'e' -> {
                            estado = "FINME2";
                            s.lexema = "FinMe";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                case "FINME2" -> {
                    switch (letra) {
                        case 't' -> {
                            estado = "FINME3";
                            s.lexema = "FinMet";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                case "FINME3" -> {
                    switch (letra) {
                        case 'o' -> {
                            estado = "FINME4";
                            s.lexema = "FinMeto";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                case "FINME4" -> {
                    switch (letra) {
                        case 'd' -> {
                            estado = "FINME5";
                            s.lexema = "FinMetod";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                case "FINME5" -> {
                    switch (letra) {
                        case 'o' -> {
                            estado = "FINME6";
                            s.lexema = "FinMetodo";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                
                case "FINME6" -> {
                    switch (letra) {
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                        case ' ' -> {
                            //SimbolosEsp(Simbolo s, String letra,int columna, int linea)
                            SimbolosEsp(s, " ", columna, linea,"FinMetodo");s = new Simbolo();
                            codigoHtml += "&nbsp;";
                            break;
                        }
                        case '\n' -> {
                             SimbolosEsp(s, "\n", columna, linea,"FinMetodo");s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        }
                        case '\t' -> {
                            SimbolosEsp(s, "\t", columna, linea,"FinMetodo");s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        }
                        default -> validarCaracteres = true;
                    }
                    validarEstadosFinales("FinMetodo", "green", validarCaracteres, letra, linea, columna, s);
                }
                
                case "b1" -> {
                    switch (letra) {
                        case 'o' -> {
                            estado = "b2";
                            s.lexema = "bo";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                case "b2" -> {
                    switch (letra) {
                        case 'o' -> {
                            estado = "b3";
                            s.lexema = "boo";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                case "b3" -> {
                    switch (letra) {
                        case 'l' -> {
                            estado = "b4";
                            s.lexema = "bool";
                    }
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                       
                        
                         default -> validarCaracteres = true;
                    }
                    validarNoFinales(validarCaracteres, columna, linea, letra, s);
                }
                
                case "b4" -> {
                    switch (letra) {
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                        case ' ' -> {
                            //SimbolosEsp(Simbolo s, String letra,int columna, int linea)
                            SimbolosEsp2(s, " ", columna, linea,"bool");s = new Simbolo();
                            codigoHtml += "&nbsp;";
                            break;
                        }
                        case '\n' -> {
                             SimbolosEsp2(s, "\n", columna, linea,"bool");s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                            break;
                        }
                        case '\t' -> {
                            SimbolosEsp2(s, "\t", columna, linea,"bool");s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                            break;
                        }
                        default -> validarCaracteres = true;
                    }
                    validarEstadosFinales("bool", "#FFB6C1", validarCaracteres, letra, linea, columna, s);
                }
                
                case "F" -> {
                    switch (letra) {
                        case '+' -> {
                            estado = "A";
                            s.token = "numero";
                            s.expresionRegular = "D+";
                            codigoHtml += "<font size='12' color='orange'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "<font size='12' color='red'>+</font>";
                            s.lexema = "+";
                            s.expresionRegular = "+";
                            s.token = "mas";
                            s.columna = columna;
                            s.linea = linea;
                            tablaLexico.add(s);
                            s = new Simbolo();
                    }
                        case '<' -> {
                            estado = "A";
                            s.token = "numero";
                            s.expresionRegular = "D+";
                            codigoHtml += "<font size='12' color='orange'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "<font size='12' color='red'>&lt;</font>";
                            s.lexema = "<";
                            s.expresionRegular = "<";
                            s.token = "menor";
                            s.columna = columna;
                            s.linea = linea;
                            tablaLexico.add(s);
                            s = new Simbolo();
                    }
                        case '>' -> {
                            estado = "A";
                            s.token = "numero";
                            s.expresionRegular = "D+";
                            codigoHtml += "<font size='12' color='orange'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "<font size='12' color='red'>&gt;</font>";
                            s.lexema = ">";
                            s.expresionRegular = ">";
                            s.token = "mayor";
                            s.columna = columna;
                            s.linea = linea;
                            tablaLexico.add(s);
                            s = new Simbolo();
                    }
                        case '=' -> {
                            estado = "A";
                            s.token = "numero";
                            s.expresionRegular = "D+";
                            codigoHtml += "<font size='12' color='orange'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "<font size='12' color='aqua'>=</font>";
                            s.lexema = "=";
                            s.expresionRegular = "=";
                            s.token = "igual";
                            s.columna = columna;
                            s.linea = linea;
                            tablaLexico.add(s);
                            s = new Simbolo();
                    }
                        case '{' -> {
                            estado = "A";
                            s.token = "numero";
                            s.expresionRegular = "D+";
                            codigoHtml += "<font size='12' color='orange'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "<font size='12' color='purple'>{</font>";
                            s.lexema = "{";
                            s.expresionRegular = "{";
                            s.token = "llave abre";
                            s.columna = columna;
                            s.linea = linea;
                            tablaLexico.add(s);
                            s = new Simbolo();
                    }
                        case '}' -> {
                            estado = "A";
                            s.token = "numero";
                            s.expresionRegular = "D+";
                            codigoHtml += "<font size='12' color='orange'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "<font size='12' color='purple'>}</font>";
                            s.lexema = "}";
                            s.expresionRegular = "}";
                            s.token = "llave cierra";
                            s.columna = columna;
                            s.linea = linea;
                            tablaLexico.add(s);
                            s = new Simbolo();
                    }
                        case '(' -> {
                            estado = "A";
                            s.token = "numero";
                            s.expresionRegular = "D+";
                            codigoHtml += "<font size='12' color='orange'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "<font size='12' color='purple'>(</font>";
                            s.lexema = "(";
                            s.expresionRegular = "(";
                            s.token = "par. abre";
                            s.columna = columna;
                            s.linea = linea;
                            tablaLexico.add(s);
                            s = new Simbolo();
                    }
                        case ')' -> {
                            estado = "A";
                            s.token = "numero";
                            s.expresionRegular = "D+";
                            codigoHtml += "<font size='12' color='orange'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "<font size='12' color='purple'>)</font>";
                            s.lexema = ")";
                            s.expresionRegular = ")";
                            s.token = "par. cierra";
                            s.columna = columna;
                            s.linea = linea;
                            tablaLexico.add(s);
                            s = new Simbolo();
                    }
                        case ';' -> {
                            estado = "A";
                            s.token = "numero";
                            s.expresionRegular = "D+";
                            codigoHtml += "<font size='12' color='orange'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "<font size='12' color='green'>;</font>";
                            s.lexema = ";";
                            s.expresionRegular = ";";
                            s.token = "punto y coma";
                            s.columna = columna;
                            s.linea = linea;
                            tablaLexico.add(s);
                            s = new Simbolo();
                    }
                        case ' ' -> {
                            estado = "A";
                            s.token = "numero";
                            s.expresionRegular = "D+";
                            codigoHtml += "<font size='12' color='orange'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "&nbsp;";
                    }
                        case '\n' -> {
                            estado = "A";
                            s.token = "numero";
                            s.expresionRegular = "D+";
                            codigoHtml += "<font size='12' color='orange'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                    }
                        case '\t' -> {
                            estado = "A";
                            s.token = "numero";
                            s.expresionRegular = "D+";
                            codigoHtml += "<font size='12' color='orange'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                    }
                        default -> validarCaracteres = true;
                    }
                    if (validarCaracteres) {
                        if (letra >= 48 && letra <= 57) {
                            estado = "F";
                            s.lexema += letra;
                            s.columna = columna;
                            s.linea = linea;
                        } else {
                            estado = "A";
                            s.token = "numero";
                            s.expresionRegular = "D+";
                            codigoHtml += "<font size='12' color='orange'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            String error = "Error lexico linea " + linea + ", columna: " + columna + ", caracter no reconocido: "+letra;
                            errorLexico.add(error);
                        }

                    }
                }
                case "E" -> {
                    switch (letra) {
                        case '_' -> {
                            estado = "E";
                            s.lexema += "_";
                    }
                        case '+' -> {
                            estado = "A";
                            s.token = "identificador";
                            s.expresionRegular = "L(L|D|_)*";
                            codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "<font size='12' color='red'>+</font>";
                            s.lexema = "+";
                            s.expresionRegular = "+";
                            s.token = "mas";
                            s.columna = columna;
                            s.linea = linea;
                            tablaLexico.add(s);
                            s = new Simbolo();
                    }
                        case '<' -> {
                            estado = "A";
                            s.token = "identificador";
                            s.expresionRegular = "L(L|D|_)*";
                            codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "<font size='12' color='red'>&lt;</font>";
                            s.lexema = "<";
                            s.expresionRegular = "<";
                            s.token = "menor";
                            s.columna = columna;
                            s.linea = linea;
                            tablaLexico.add(s);
                            s = new Simbolo();
                    }
                        case '>' -> {
                            estado = "A";
                            s.token = "identificador";
                            s.expresionRegular = "L(L|D|_)*";
                            codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "<font size='12' color='red'>&gt;</font>";
                            s.lexema = ">";
                            s.expresionRegular = ">";
                            s.token = "mayor";
                            s.columna = columna;
                            s.linea = linea;
                            tablaLexico.add(s);
                            s = new Simbolo();
                    }
                        case '=' -> {
                            estado = "A";
                            s.token = "identificador";
                            s.expresionRegular = "L(L|D|_)*";
                            codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "<font size='12' color='aqua'>=</font>";
                            s.lexema = "=";
                            s.expresionRegular = "=";
                            s.token = "igual";
                            s.columna = columna;
                            s.linea = linea;
                            tablaLexico.add(s);
                            s = new Simbolo();
                    }
                        case '{' -> {
                            estado = "A";
                            s.token = "identificador";
                            s.expresionRegular = "L(L|D|_)*";
                            codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "<font size='12' color='purple'>{</font>";
                            s.lexema = "{";
                            s.expresionRegular = "{";
                            s.token = "llave abre";
                            s.columna = columna;
                            s.linea = linea;
                            tablaLexico.add(s);
                            s = new Simbolo();
                    }
                        case '}' -> {
                            estado = "A";
                            s.token = "identificador";
                            s.expresionRegular = "L(L|D|_)*";
                            codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "<font size='12' color='purple'>}</font>";
                            s.lexema = "}";
                            s.expresionRegular = "}";
                            s.token = "llave cierra";
                            s.columna = columna;
                            s.linea = linea;
                            tablaLexico.add(s);
                            s = new Simbolo();
                    }
                        case '(' -> {
                            estado = "A";
                            s.token = "identificador";
                            s.expresionRegular = "L(L|D|_)*";
                            codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "<font size='12' color='purple'>(</font>";
                            s.lexema = "(";
                            s.expresionRegular = "(";
                            s.token = "par. abre";
                            s.columna = columna;
                            s.linea = linea;
                            tablaLexico.add(s);
                            s = new Simbolo();
                    }
                        case ')' -> {
                            estado = "A";
                            s.token = "identificador";
                            s.expresionRegular = "L(L|D|_)*";
                            codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "<font size='12' color='purple'>)</font>";
                            s.lexema = ")";
                            s.expresionRegular = ")";
                            s.token = "par. cierra";
                            s.columna = columna;
                            s.linea = linea;
                            tablaLexico.add(s);
                            s = new Simbolo();
                    }
                        case ';' -> {
                            estado = "A";
                            s.token = "identificador";
                            s.expresionRegular = "L(L|D|_)*";
                            codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "<font size='12' color='green'>;</font>";
                            s.lexema = ";";
                            s.expresionRegular = ";";
                            s.token = "punto y coma";
                            s.columna = columna;
                            s.linea = linea;
                            tablaLexico.add(s);
                            s = new Simbolo();
                    }
                        case ' ' -> {
                            estado = "A";
                            s.token = "identificador";
                            s.expresionRegular = "L(L|D|_)*";
                            codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "&nbsp;";
                    }
                        case '\n' -> {
                            estado = "A";
                            s.token = "identificador";
                            s.expresionRegular = "L(L|D|_)*";
                            codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                    }
                        case '\t' -> {
                            estado = "A";
                            s.token = "identificador";
                            s.expresionRegular = "L(L|D|_)*";
                            codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                    }
                        default -> validarCaracteres = true;
                    }
                    if (validarCaracteres) {
                        if ((letra >= 65 && letra <= 90) || (letra >= 97 && letra <= 122)) {
                            estado = "E";
                            s.lexema += letra;
                            s.columna = columna;
                            s.linea = linea;
                        } else {
                            if (letra >= 48 && letra <= 57) {
                                estado = "E";
                                s.lexema += letra;
                                s.columna = columna;
                                s.linea = linea;
                            } else {
                                estado = "A";
                                s.token = "identificador";
                                s.expresionRegular = "L(L|D|_)*";
                                codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                                tablaLexico.add(s);
                                s = new Simbolo();
                                String error = "Error lexico linea " + linea + ", columna: " + columna + ", caracter no reconocido: "+letra;
                                errorLexico.add(error);
                            }
                        }

                    }
                }
            }
                    }

        return codigoHtml;

    }
    
    //OPTIMIZACION
        public void signosValidacion(Simbolo s, String color,String lexema, String simb,String token, int columna, int linea){
                            
                            codigoHtml += "<font size='12' color='" + color + "'>"+simb+ "</font>";
                            s.lexema = lexema;
                            s.expresionRegular = lexema;
                            s.token = token;
                            s.columna = columna;
                            s.linea = linea;
                            tablaLexico.add(s);
                           
                            
    }
        
        public void validarNoFinales(boolean val,int columna, int linea,char letra,Simbolo s){
            
                    if (val) {
                        if ((letra >= 65 && letra <= 90) || (letra >= 97 && letra <= 122)) {
                            estado = "E";
                            s.lexema = letra + "";
                            s.columna = columna;
                            s.linea = linea;
                        } else {
                            if (letra >= 48 && letra <= 57) {
                                estado = "E";
                                s.lexema = letra + "";
                                s.columna = columna;
                                s.linea = linea;
                            } else {
                                estado = "A";
                                s.token = "identificador";
                                s.expresionRegular = "L(L|D|_)*";
                                codigoHtml += "<font size='12' color='blue'>" + s.lexema + "</font>";
                                tablaLexico.add(s);
                                String error = "Error lexico linea " + linea + ", columna: " + columna + ", caracter no reconocido: "+letra;
                                errorLexico.add(error);
                            }
                        }

                    }
        }
        
          
          public void validarEstadosFinales(String token, String color, boolean v, char letra, int linea, int columna, Simbolo s ){
            if (v) {
                        if ((letra >= 65 && letra <= 90) || (letra >= 97 && letra <= 122)) {
                            estado = "E";
                            s.lexema += letra;
                            s.columna = columna;
                            s.linea = linea;
                        } else {
                            if (letra >= 48 && letra <= 57) {
                                estado = "E";
                                s.lexema += letra;
                                s.columna = columna;
                                s.linea = linea;
                            } else {
                                estado = "A";
                                s.token = token;
                                s.expresionRegular = token;
                                codigoHtml += "<font size='12' color='"+color+"'>" + s.lexema + "</font>";
                                tablaLexico.add(s);
                                String error = "Error lexico linea " + linea + ", columna: " + columna + ", caracter no reconocido: "+letra;
                                errorLexico.add(error);
                            }
                        }

                    }
        }
        
        
        public void optimizar(Simbolo s,String lexema,String color, String simb,String Nombretoken, int columna, int linea ){
                            
                            estado = "A";
                            s.token = "identificador";
                            s.expresionRegular = "L(L|D|_)*";
                            codigoHtml += "<font size='12' color='blue'>" + s.lexema +  "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "<font size='12' color='" + color + "'>" + simb +  "</font>";
                            s.lexema = lexema;
                            s.expresionRegular = lexema;
                            s.token = Nombretoken;
                            s.columna = columna;
                            s.linea = linea;
                            tablaLexico.add(s);
                           
        }
        
        public void EstadosFinales(String token,String tokensimbolo, String color,Simbolo s,String simb,String expresion, int columna, int linea){
            
                            estado = "A";
                            s.token = token;
                            s.expresionRegular = token;
                            codigoHtml += "<font size='12' color='green'>" + s.lexema +  "</font>";
                            tablaLexico.add(s);
                            s = new Simbolo();
                            codigoHtml += "<font size='12' color='" + color + "'>" + simb +  "</font>";
                            s.lexema = expresion;
                            s.expresionRegular = expresion;
                            s.token = tokensimbolo;
                            s.columna = columna;
                            s.linea = linea;
                            tablaLexico.add(s);
        }
        
        
        public void SimbolosEsp(Simbolo s, String letra,int columna, int linea,String token){
            switch (letra){
                
            
                        case " " -> {
                            estado = "A";
                            s.token = token;
                            s.expresionRegular = token;
                            codigoHtml += "<font size='12' color='green'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            codigoHtml += "&nbsp;";
                    }
                        case "\n" -> {
                            estado = "A";
                            s.token = token;
                            s.expresionRegular = token;
                            codigoHtml += "<font size='12' color='green'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                    }
                        case "\t" -> {
                            estado = "A";
                            s.token = token;
                            s.expresionRegular = token;
                            codigoHtml += "<font size='12' color='green'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                    }
                        default -> {
                            
                        }
             }
        
        
        }
        
        public void SimbolosEsp2(Simbolo s, String letra,int columna, int linea,String token){
            switch (letra){
                
            
                        case " " -> {
                            estado = "A";
                            s.token = token;
                            s.expresionRegular = token;
                            codigoHtml += "<font size='12' color='#FFB6C1'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            codigoHtml += "&nbsp;";
                    }
                        case "\n" -> {
                            estado = "A";
                            s.token = token;
                            s.expresionRegular = token;
                            codigoHtml += "<font size='12' color='#FFB6C1'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            columna = 0;
                            linea++;
                            codigoHtml += "<br>";
                    }
                        case "\t" -> {
                            estado = "A";
                            s.token = token;
                            s.expresionRegular = token;
                            codigoHtml += "<font size='12' color='#FFB6C1'>" + s.lexema + "</font>";
                            tablaLexico.add(s);
                            codigoHtml += "&nbsp;&nbsp;&nbsp;";
                    }
                        default -> {
                            
                        }
             }
        
        
        }
       

}
