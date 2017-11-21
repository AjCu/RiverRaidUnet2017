/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Archivos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/** Clase que ordena nombres,puntajes y top jugadores del juego
 *
 * @author Alberto Cristancho
 */
public class Escritor {
   
 /**  Recibe el String con el nombre del jugador y la añade a un registro (archivo que creamos llamado top.txt)
 */
       public void AñadirNombre(String nombre) throws IOException
       {
                    
                         FileWriter fw;
                         String arch = "top.txt";
                         fw = new FileWriter (arch,true);
                         BufferedWriter bw = new BufferedWriter (fw);
                         PrintWriter pw = new PrintWriter (bw);
                         pw.print(nombre);
                         System.out.println("Se ha añadido el nombre del jugador al archivo...");
                         pw.close();
       } 
        /**  Recibe un entero con el puntaje del jugador y la añade a un registro (archivo que creamos llamado top.txt)
        */
        public void AñadirPuntuacion(int punt) throws IOException
       {
                    
                        FileWriter fw;
                         String arch = "top.txt";
                         fw = new FileWriter (arch,true);
                         BufferedWriter bw = new BufferedWriter (fw);
                         PrintWriter pw = new PrintWriter (bw);
                         pw.println(" "+punt);
                         System.out.println("Se ha añadido el puntaje del jugador al archivo..");
                         pw.close();
       } 
       /** Metodo que lee el archivo topt.txt, lo separa en 2 vectores y luego por metodo de la burbuja
        * lo añade a top2.txt que será el archivo a desplegar en el menu(registro ordenado
        */
        public void OrdenarTop() throws FileNotFoundException, IOException
        {
            Scanner lector,lector2;
            int njugadores = 0;
            String jugador;

            lector = new Scanner(new File("top.txt"));
            while(lector.hasNextLine())
            {
                jugador= lector.nextLine();
                njugadores=njugadores+1;
            }
            lector.close();
             String[] nombres;
            nombres = new String[30];
            int[] puntajes;
            puntajes = new int[30];

            lector2 = new Scanner(new File("top.txt"));

            for(int i=0;i<njugadores;i++)
            {

                jugador = lector2.nextLine();
                String [] token = jugador.split(" ");
                nombres[i] = token[0];
                puntajes[i] = Integer.parseInt(token[1]);          
            }
             for(int i=0;i<njugadores;i++)
            {   //metodo de la burbuja
                     for(int j=0;j<njugadores;j++)
                     {
                                  if (puntajes[j] < puntajes[j + 1])
                                 {
                                     int aux1 = puntajes[j+1];
                                     puntajes[j+1] = puntajes[j];
                                     puntajes[j] = aux1;

                                     String aux2 = nombres[j+1];
                                     nombres[j+1] = nombres[j];
                                     nombres[j] = aux2;
                                 }

                     }
            }
                              FileWriter fw;
                              String arch = "top2.txt";
                              fw = new FileWriter (arch);
                              BufferedWriter bw = new BufferedWriter (fw);
                              PrintWriter pw = new PrintWriter (bw);

                for(int i=0;i<njugadores;i++)
            {


                              pw.println(nombres[i]+" "+puntajes[i]);

            }
                  pw.close();
              System.out.println("Se ha ordenado el Top...");
        }
          // Metodo que devuelve el vector de nombres del top2.txt para el Top 10 del Menu
        public String[] DevolverNombres() throws FileNotFoundException
        {  
            Scanner lector,lector2;
            int njugadores = 0;

            String jugador;

            lector = new Scanner(new File("top2.txt"));
            while(lector.hasNextLine())
            {
                jugador= lector.nextLine();
                njugadores=njugadores+1;
            } 
            String[] nombres;
            nombres = new String[30];
            lector.close();

            lector2 = new Scanner(new File("top2.txt"));

            for(int i=0;i<njugadores;i++)
            {

                jugador = lector2.nextLine();
                String [] token = jugador.split(" ");
                nombres[i] = token[0];
            }

                 return nombres;
        }
        // Metodo que devuelve el vector de puntajes del top2.txt para el Top 10 del Menu
        public int[] DevolverPuntos() throws FileNotFoundException
        {  
            Scanner lector,lector2;
            int njugadores = 0;
            String jugador;
            lector = new Scanner(new File("top2.txt"));
            while(lector.hasNextLine())
            {
                jugador= lector.nextLine();
                njugadores=njugadores+1;
            }
            lector.close();
            int[] puntajes;
            puntajes = new int[30];
            lector2 = new Scanner(new File("top2.txt"));

            for(int i=0;i<njugadores;i++)
            {

                jugador = lector2.nextLine();
                String [] token = jugador.split(" ");
                puntajes[i] = Integer.parseInt(token[1]);
            }

                 return puntajes;
        }
        
}


  
