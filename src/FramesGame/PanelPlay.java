package FramesGame;

import Archivos.Escritor;
import Enemies.Enemie;
import Maps.Map;
import ImgComponents.*;
import Planes.Plane;
import Sonido.Reproductor;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Random;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class PanelPlay extends JPanel{
/*Corazon del programa, Clase que contiene el Panel donde interacturan la mayoria de clases,
   se pintan los componentes, generan colisiones y se instancia la mayoria de Clases.
 */    
private Plane plane;
private Enemie enemie;
Reproductor musicplayer;
Escritor esc;
private Map map;
private JLabel Life, Life2, Life3, Oil, Time, Life_letter, title_game, score_label, control_label, fuel_label, points_label, clock_label;
private ImgComponents LI;
javax.swing.Timer t, t2, t3, tf, tclock;
private int nlives=6, clock=90, fuel=100, points=0, espacio=0, iterador1=0, iterador2=-800,iterador3=-1600;
private boolean win=false, lose=false;

//Timer del juego, si llega el tiempo a ser cero(0) se activa el ganar y se llama el metodo GameOver
    ActionListener timegame= new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            
         clock_label.setText(""+clock);
            if(clock==0){
               win=true;
            }else{
          clock--;
          }

         GameOver();
           
        }
        
      };  
    //Timer de la gasolina que decrece en 5, cada vez que llega a cero quita medio corazon(una vida) 
    ActionListener fuellevel = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent ae) {
            
            fuel-=5;
            fuel_label.setText(fuel+"%");
            if(fuel==0){
                
                nlives--;
                quitarvida();
                fuel=100;
            }
        }
    };
    
    /*Escuchador del disparo del misil del avion, si llega al fondo del Panel su Timer se para
      hasta que el usuario presione ESPACIO nuevamente y setea sus Coordenadas con las del avion
    */
    
    ActionListener disparo = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent ae){
         
                plane.setShot(true);
                plane.Shot();
                 
                  if(plane.getPosys()<1){
                      
                    t.stop();
                    plane.setPosys(plane.getPosy()-plane.getsHeight());
                    plane.setPosxs(plane.getPosx()+(plane.getWidth()/2)-(plane.getsWidth()/2));
                    plane.setShot(false);
                }
                
                repaint();
        }
    };
     //Timer de los enemigos y barril para que bajen, se activan los metodos Down de cada enemigos que se ubican en la clase Enemie
    ActionListener enemigos = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent ai){
            
            //Enemigos bajando por el mapa
            
            enemie.Down2();
                //espacio para que no salgan los enemigos al mismo tiempo
                    if(espacio>60){
                          enemie.Down();
                   
                    }     
                    
                    if(espacio>150){
                          enemie.Down3();
                    }
                    //Espacio de 15 seg aprox para que salga el barril
                    if(espacio>300 && espacio<730 || espacio>2000 && espacio<2430 || espacio>3700 && espacio<4130 || espacio>5400 && espacio<5830 || espacio>7100 && espacio<7530 ){
                          enemie.Down4();
                     }
             //chequeo de choques
                    choques(); 
               //Si los enemigos llegan sin colisionar se setean sus posiciones para que salgan nuevamente arriba del Panel     
                    if(enemie.getHy()>850){
                        Random r1 = new Random();
                        enemie.setHy(1);
                        enemie.setHx(r1.nextInt(260)+140);
                    } 
                    
                    if(enemie.getHy2()>850){
                        Random r1 = new Random();
                        enemie.setHy2(1);
                        enemie.setHx2(r1.nextInt(260)+140);
                    }
                    
                    if(enemie.getBy()>850){
                        Random r1 = new Random();
                        enemie.setBy(1);
                        enemie.setBx(r1.nextInt(260)+140);
                    }
                    
                    if(enemie.getBy2()>850){
                        Random r1 = new Random();
                        enemie.setBy2(1);
                        enemie.setBx2(r1.nextInt(260)+140);
                    }
                    
                repaint();
                espacio+=1;
        }
     };  
    //Timer que baja las 3 imagenes del nivel del juego
     ActionListener movermapa = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent ao) {
     //Iterador para cada mapa, cada uno inicializado 800 pixeles mas arriba que otro
            iterador1+=1;
            iterador2+=1;
            iterador3+=1;   
        if(iterador1==800){
                iterador1=-800;
        }
        
        if(iterador2==800){
                iterador2=-800;
        }
        if(iterador3==800){
                iterador3=-800;
        }
            repaint();
        }
    };
    //Constructor del PanelPlay donde se instancias las clases y se empiezan los Timers
     public PanelPlay() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        plane=new Plane();
        map=new Map();
        musicplayer= new Reproductor();
        enemie=new Enemie();
        LI=new ImgComponents();
        super.addKeyListener(Keys);
        super.setLayout(null);
        super.setFocusable(true);
        super.setOpaque(false);
        //Timers
        tclock = new javax.swing.Timer(1000, timegame);
        t = new javax.swing.Timer(10, disparo);
        t2= new javax.swing.Timer(20, enemigos);
        t3= new javax.swing.Timer(10, movermapa);
        tf= new javax.swing.Timer(1400, fuellevel);
        
         
        initLabels();
        tclock.start();
        t2.start();    
        t3.start();
        tf.start();
}
     //Metodo para inicializar gran cantidad de atributos para no hacer aun mas grande el constructor
     public void initLabels(){
       
       //LI=new ImgComponents();
       Life_letter=new JLabel();
       Life=new JLabel();
       Life2=new JLabel();
       Life3=new JLabel();
       Oil=new JLabel();
       Time=new JLabel();
      //Inicializando label Ubicados a la derecha del juego, Labels para las estadisticas
   
       Life_letter.setBounds(720, 400, LI.getImageL().getWidth(this), LI.getImageL().getHeight(this));
       Life_letter.setIcon(LI.getL());
       
       Life.setBounds(1000, 400 , LI.getImageL1().getWidth(this), LI.getImageL1().getHeight(this));
       Life.setIcon(LI.getL1());
       Life2.setBounds(1100, 400, LI.getImageL2().getWidth(this), LI.getImageL2().getHeight(this));
       Life2.setIcon(LI.getL2());
       Life3.setBounds(1200, 400, LI.getImageL3().getWidth(this), LI.getImageL3().getHeight(this));
       Life3.setIcon(LI.getL3());
       
       Oil.setOpaque(true);
       Oil.setLocation(720,500);
       Oil.setSize(LI.getImageO().getWidth(this),LI.getImageO().getHeight(this));
       Oil.setIcon(LI.getO());
       
       Time.setBounds(720,600,LI.getImageT().getWidth(this),LI.getImageT().getHeight(this));
       Time.setIcon(LI.getT());
       
       title_game=new JLabel();
       title_game.setBounds(725,-60,LI.getTitle_image().getWidth(this), LI.getTitle_image().getHeight(Oil));
       title_game.setIcon(LI.getTitle_icon());
       
       score_label=new JLabel();
       score_label.setBounds(720,120,LI.getScore_image().getWidth(this),LI.getScore_image().getHeight(this));
       score_label.setIcon(LI.getScore_icon());
       
       control_label=new JLabel();
       control_label.setBounds(1070,130,LI.getControl_image().getWidth(this),LI.getControl_image().getHeight(this));
       control_label.setIcon(LI.getControl_icon());
       
       fuel_label=new JLabel();
       fuel_label.setBounds(1107,514,LI.getBorder_image1().getWidth(this)-30,LI.getBorder_image1().getHeight(this)-30);
       fuel_label.setBackground(Color.white);
       fuel_label.setOpaque(true);
       fuel_label.setFont(new java.awt.Font("Impact", 0, 35)); 
       fuel_label.setForeground(Color.red);
       fuel_label.setText(fuel+"%");
       
       points_label=new JLabel();
       points_label.setBounds(805,230,LI.getBorder_image2().getWidth(this)-30,LI.getBorder_image2().getHeight(this)-30);
       points_label.setOpaque(true);
       points_label.setBackground(Color.white);
       points_label.setFont(new java.awt.Font("Impact", 0, 35)); 
       points_label.setForeground(Color.red);
       points_label.setText(""+points);
       
       clock_label=new JLabel();
       clock_label.setBounds(1014,616,LI.getBorder_image3().getWidth(this)-30,LI.getBorder_image3().getHeight(this)-30);
       clock_label.setOpaque(true);
       clock_label.setBackground(Color.white);
       clock_label.setFont(new java.awt.Font("Impact", 0, 35));
       clock_label.setForeground(Color.red);
       clock_label.setText(""+clock);
       
       //Añadimos todos los Labels al PanelPlay usando super
       super.add(Life_letter);
       super.add(Life);
       super.add(Life2);
       super.add(Life3);
       super.add(Oil);
       super.add(Time);
       super.add(title_game);
       super.add(score_label);
       super.add(control_label);
       super.add(fuel_label);
       super.add(points_label);
       super.add(clock_label);  
    
}
   //Metodo que comprueba si algun rectangulo de la clase Plane a colisionado con alguno de la clase Enemie
     public void choques(){
         //choque del avion con la orilla
         if(plane.getPosx()<130 || plane.getPosx()>460)
         {
             plane.setPosx(340-plane.getHeight());
             plane.setPosy(750-plane.getWidth());
             plane.setPosxs(plane.getPosx()+(plane.getWidth()/2)-(plane.getsWidth()/2));
             //Descontar vidas
             nlives=nlives-1;
             musicplayer.ReproducirSonido("src/Audios/Explosion.wav");
         }
        //Choque del disparo con Barco
        if(enemie.area().contains(plane.getPosxs(), plane.getPosys()+100) && plane.getShot()==true){
            enemie.setBy(1000);
            
             t.stop();
             plane.setPosys(plane.getPosy()-plane.getsHeight());
             plane.setPosxs(plane.getPosx()+(plane.getWidth()/2)-(plane.getsWidth()/2));
             plane.setShot(false);
             points+=30;
              musicplayer.ReproducirSonido("src/Audios/Explosion.wav");
        }
         //Choque del disparo con Barril
        if(enemie.area4().contains(plane.getPosxs(), plane.getPosys()+100) && plane.getShot()==true){
            enemie.setBy2(1000);
            espacio+=430;
             t.stop();
             plane.setPosys(plane.getPosy()-plane.getsHeight());
             plane.setPosxs(plane.getPosx()+(plane.getWidth()/2)-(plane.getsWidth()/2));
             plane.setShot(false);
             points-=100;
            musicplayer.ReproducirSonido("src/Audios/Explosion.wav");
        }
        //choque del disparo con Helicoptero Verde
         if(enemie.area2().contains(plane.getPosxs(), plane.getPosys()+100)&& plane.getShot()==true){
            enemie.setHy(1000);
             t.stop();
             plane.setPosys(plane.getPosy()-plane.getsHeight());
             plane.setPosxs(plane.getPosx()+(plane.getWidth()/2)-(plane.getsWidth()/2));
             plane.setShot(false);
             points+=40;
             musicplayer.ReproducirSonido("src/Audios/Explosion.wav");
        }
         //choque del disparo con Helicoptero Marron
         if(enemie.area3().contains(plane.getPosxs(), plane.getPosys()+100)&& plane.getShot()==true){
            enemie.setHy2(1000);
             t.stop();
             plane.setPosys(plane.getPosy()-plane.getsHeight());
             plane.setPosxs(plane.getPosx()+(plane.getWidth()/2)-(plane.getsWidth()/2));
             plane.setShot(false);
             points+=40;
              musicplayer.ReproducirSonido("src/Audios/Explosion.wav");
        }
         //choque del avion con Barco
         if(enemie.area().contains(plane.getPosx()+38,plane.getPosy()+145) || enemie.area().contains(plane.getPosx(),plane.getPosy()+145) || enemie.area().contains(plane.getPosx()+75,plane.getPosy()+145)){   nlives=nlives-1;
             enemie.setBy(1000);
             plane.setPosx(340-plane.getHeight());
             plane.setPosy(750-plane.getWidth());
             plane.setPosys(plane.getPosy()-plane.getsHeight());
             plane.setPosxs(plane.getPosx()+(plane.getWidth()/2)-(plane.getsWidth()/2));
          musicplayer.ReproducirSonido("src/Audios/Explosion.wav");
         }
         //choque del avion con Barril
         if(enemie.area4().contains(plane.getPosx()+38,plane.getPosy()+118) || enemie.area4().contains(plane.getPosx(),plane.getPosy()+120) || enemie.area4().contains(plane.getPosx()+75,plane.getPosy()+120)){
             enemie.setBy2(1000);
             espacio+=430;
             musicplayer.ReproducirSonido("src/Audios/GasUp.wav");
             fuel=100;
         }
         //choque del Avion con el Helicoptero Verde
           if(enemie.area2().contains(plane.getPosx()+38,plane.getPosy()+145) || enemie.area2().contains(plane.getPosx(),plane.getPosy()+145) || enemie.area2().contains(plane.getPosx()+75,plane.getPosy()+145)){
             nlives=nlives-1;
             enemie.setHy(1000);
             plane.setPosx(340-plane.getHeight());
             plane.setPosy(750-plane.getWidth());
             plane.setPosys(plane.getPosy()-plane.getsHeight());
             plane.setPosxs(plane.getPosx()+(plane.getWidth()/2)-(plane.getsWidth()/2));
             musicplayer.ReproducirSonido("src/Audios/Explosion.wav");
         }
            //choque del Avion con el Helicoptero Naranja
           if(enemie.area3().contains(plane.getPosx()+38,plane.getPosy()+145) || enemie.area3().contains(plane.getPosx(),plane.getPosy()+145) || enemie.area3().contains(plane.getPosx()+75,plane.getPosy()+145)){   nlives=nlives-1;
             enemie.setHy2(1000);
             plane.setPosx(340-plane.getHeight());
             plane.setPosy(750-plane.getWidth());
             plane.setPosys(plane.getPosy()-plane.getsHeight());
             plane.setPosxs(plane.getPosx()+(plane.getWidth()/2)-(plane.getsWidth()/2));
             musicplayer.ReproducirSonido("src/Audios/Explosion.wav");
         }
           points_label.setText(""+points);
           quitarvida();
    }
     
    /*Metodo que indica cuants vidas le restan al jugador
     *Si el contador de vidas es cero, o sea nlives==0 entonces se da valor
     a los booleanos win y lose que determinan si el jugador ganó o no
     */
    public void quitarvida(){
              
              if(nlives==6){
                 Life.setIcon(LI.getL1());
                 Life2.setIcon(LI.getL1());
                 Life3.setIcon(LI.getL1());
                }
              
              if(nlives==5){
                 Life.setIcon(LI.getL1());
                 Life2.setIcon(LI.getL1());
                 Life3.setIcon(LI.getL2());
                }
              
              if(nlives==4){
                 Life.setIcon(LI.getL1());
                 Life2.setIcon(LI.getL1());
                 Life3.setIcon(LI.getL3());
                }
              
              if(nlives==3){
                 Life.setIcon(LI.getL1());
                 Life2.setIcon(LI.getL2());
                 Life3.setIcon(LI.getL3());
                }
              
              if(nlives==2){
                 Life.setIcon(LI.getL1());
                 Life2.setIcon(LI.getL3());
                 Life3.setIcon(LI.getL3());
                }
              
              if(nlives==1){
                 Life.setIcon(LI.getL2());
                 Life2.setIcon(LI.getL3());
                 Life3.setIcon(LI.getL3());
                }
              if(nlives==0){
                 Life.setIcon(LI.getL3());
                 Life2.setIcon(LI.getL3());
                 Life3.setIcon(LI.getL3());
                 win=false;
                 lose=true;
 
                 GameOver();
                
              }
                
    }
    
    /*Escuchador de teclas para que el avion se mueva, acelere o dispare dependiendo de la tecla
     * Tambien si el usuario se le permite presionar ESCAPE(Esc) se instancia el Escritor de archivos
        para que ordene el puntaje y lo clasifique en el TOP si es el caso
     */
    KeyListener Keys=new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e){  
           
        }

        @Override
        public void keyPressed(KeyEvent e){
            
            if(e.getKeyCode()==KeyEvent.VK_LEFT){
                plane.Fly(1);
                repaint();
            } 
              if(e.getKeyCode()==KeyEvent.VK_UP){
                 t2.setDelay(5);
                 musicplayer.ReproducirSonido("src/Audios/Acelerar.wav");
                repaint();
            } 
              
            if(e.getKeyCode()==KeyEvent.VK_RIGHT){
                plane.Fly(2);
                repaint();
            }
            if(e.getKeyCode()==KeyEvent.VK_SPACE){         
                    t.start();
                    musicplayer.ReproducirSonido("src/Audios/Missile2.wav");
             }
            if(e.getKeyCode()==KeyEvent.VK_ESCAPE && (win==true || lose==true))
            {         
                //Instancia del escritor
                     esc = new Escritor();
                try {
                    esc.AñadirPuntuacion(points);
                    esc.OrdenarTop();
                } catch (IOException ex) {
                    System.out.println("No se pudo crear el archivo");
                }
                    System.exit(0);
            }
        }

        @Override
        public void keyReleased(KeyEvent e){
            //Acelerar el avion
           if(e.getKeyCode()==KeyEvent.VK_UP){
                t2.setDelay(20);
                musicplayer.ReproducirSonido("src/Audios/Acelerar.wav");
                repaint();
            } 
        }
    };
    
    //Metodo que comprueba si el juego está terminado, detiene todos los timers
    public void GameOver(){
        
       
        //Caso donde se Gana
        if(win){
        musicplayer.ReproducirSonido("src/Audios/ganar.wav");
        tclock.stop();
        t2.stop();    
        t3.stop();
        tf.stop();
        repaint();
        
        }
        //Caso donde se pierde
        if(lose){
        musicplayer.ReproducirSonido("src/Audios/ExplosionAvion.wav");
        tclock.stop();
        t2.stop();    
        t3.stop();
        tf.stop();
        repaint();
        
    }
      
        
}
    
/*Metodo para pintar todos los componentes de clases Plane, Map y Enemie
 * comprueba los estado de los booleanos para mostrar tambien la imagen de ganar o perder y el disparo en su caso
 */

    @Override
    protected void paintComponent(Graphics G){
       
        super.paintComponent(G);
        Graphics2D G2 =(Graphics2D)G;
        G2.drawImage(map.getImagen(), 0, 0+iterador1, map.getWidth(), map.getHeight(), this);
        G2.drawImage(map.getImagen2(), 0, 0+iterador2, map.getWidth(), map.getHeight(), this);
        G2.drawImage(map.getImagen3(), 0, 0+iterador3, map.getWidth(), map.getHeight(), this);
        G2.drawImage(map.getFondo(), 680, 0, map.getFw(), map.getFh(), this);
        G2.drawImage(map.getBarra(), 660, 0, map.getBw(), map.getBh(), this);
        G2.drawImage(plane.getImagen(), plane.getPosx(), plane.getPosy(), this);
        G2.drawImage(enemie.getImgShip(), enemie.getBx(), enemie.getBy()-100, this);
        G2.drawImage(enemie.getImgBarril(), enemie.getBx2(), enemie.getBy2()-100, this);
        G2.drawImage(enemie.getImgHeli(), enemie.getHx(), enemie.getHy()-100, this);
        G2.drawImage(enemie.getImgHeli2(), enemie.getHx2(), enemie.getHy2()-100, this);
        if(plane.getShot()==true){
        G2.drawImage(plane.getimgShot(), plane.getPosxs(),plane.getPosys(), this);
        }
        G2.drawImage(LI.getBorder_image1(),1093,498,LI.getBorder_image1().getWidth(this),LI.getBorder_image1().getHeight(this),this);
        G2.drawImage(LI.getBorder_image2(),790,218,LI.getBorder_image2().getWidth(this),LI.getBorder_image2().getHeight(this),this);
        G2.drawImage(LI.getBorder_image3(),1000,600,LI.getBorder_image3().getWidth(this),LI.getBorder_image3().getHeight(this),this);
        if(win){
          G2.drawImage(LI.getWin_image(),25,300,LI.getWin_image().getWidth(this),LI.getWin_image().getHeight(this),this);  
        }
        if(lose){
          G2.drawImage(LI.getLose_image(),25,300,LI.getLose_image().getWidth(this),LI.getLose_image().getHeight(this),this);  
        }
    }

    public int getPoints() {
        return points;
    }

    public Map getMap() {
        return map;
    }
}