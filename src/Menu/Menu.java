package Menu;

import Archivos.Escritor;
import FramesGame.FrameGame;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/** Clase donde se desplega el menu de opciones del juego y se instancia el objeto que lo arranca
 *
 * @author Alberto Cristancho
 */

public class Menu extends JFrame{
    
    private JPanel Panel_menu;
    private JLabel logo, play, top_ten, instruction, credits, exit, label_instrucciones,label_creditos,label_top;
    private Image img_logo, img_play, img_top_ten, img_instruction, img_credits, img_exit, img_controles,img_creditos;
    private ImageIcon logo_icon, play_icon, top_ten_icon, instruction_icon, credits_icon, exit_icon, controls_icon,creditos_icon;
    private JTextField name;
    private FrameGame fg;
    private String name_player;
    private Escritor esc;
    private int[] puntos;
    private String[] jugadores;
    
/* Constructor que le da tamaño al frame del Menu y le da valores a sus atributos
 *
 */
    public Menu() throws FileNotFoundException{
        repaint();
        name_player="";
        Panel_menu=new JPanel();
        Panel_menu.setLayout(null);
        Panel_menu.setOpaque(false);
        esc= new Escritor();
        super.setTitle("RiverRaid");
        super.setBounds(400,150,610,600);
        super.setBackground(Color.GRAY);
        super.setContentPane(Panel_menu);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.initComponents();
        super.setVisible(true);
        
    }
    //Metodo para no hacer tan abultado el contrusctor, donde busco la ruta de las imagenes y les asigno un Label
    public void initComponents() throws FileNotFoundException{
        
        try{
            //Imagenes
            img_logo=ImageIO.read(new File("src/Images/logomenu.png"));
            img_play=ImageIO.read(new File("src/Images/jugar.png"));
            img_top_ten=ImageIO.read(new File("src/Images/top.png"));
            img_instruction=ImageIO.read(new File("src/Images/instrucciones.png"));
            img_credits=ImageIO.read(new File("src/Images/creditos.png"));
            img_exit=ImageIO.read(new File("src/Images/salir.png"));
            img_controles=ImageIO.read(new File("src/Images/controles23.png"));
            img_creditos=ImageIO.read(new File("src/Images/creditoslogo.png"));          
            
        }catch(IOException ex){
            System.out.println("Imagen no encontrada."); 
        }
        //ImageIcon
        logo_icon=new ImageIcon(img_logo);
        play_icon=new ImageIcon(img_play);
        top_ten_icon=new ImageIcon(img_top_ten);
        instruction_icon=new ImageIcon(img_instruction);
        credits_icon=new ImageIcon(img_credits);
        exit_icon=new ImageIcon(img_exit);
        controls_icon=new ImageIcon(img_controles);
        creditos_icon=new ImageIcon(img_creditos);
        //Asignacion a Labels
        logo=new JLabel();
        logo.setBounds(53,0,img_logo.getWidth(this), img_logo.getHeight(this));
        logo.setIcon(logo_icon);
        logo.addMouseListener(escuchador);
        play=new JLabel();
        play.setBounds(200,120,img_play.getWidth(this), img_play.getHeight(this));
        play.setIcon(play_icon);
        play.addMouseListener(escuchador);
        top_ten=new JLabel();
        top_ten.setBounds(195,210,img_top_ten.getWidth(this),img_top_ten.getHeight(this));
        top_ten.setIcon(top_ten_icon);
        top_ten.addMouseListener(escuchador);
        instruction=new JLabel();
        instruction.setBounds(70,295,img_instruction.getWidth(this),img_instruction.getHeight(this));
        instruction.setIcon(instruction_icon);
        instruction.addMouseListener(escuchador);
        credits=new JLabel();
        credits.setBounds(155,385,img_credits.getWidth(this),img_credits.getHeight(this));
        credits.setIcon(credits_icon);
        credits.addMouseListener(escuchador);
        exit=new JLabel();
        exit.setBounds(200,470,img_exit.getWidth(this),img_exit.getHeight(this));
        exit.setIcon(exit_icon);   
        exit.addMouseListener(escuchador);
        label_instrucciones=new JLabel();
        label_instrucciones.setIcon(controls_icon);
        label_instrucciones.setVisible(false);
        label_instrucciones.setBounds(170,300,img_controles.getWidth(fg),img_controles.getHeight(fg));
        label_creditos=new JLabel();
        label_creditos.setIcon(creditos_icon);
        label_creditos.setVisible(false);
        label_creditos.setBounds(190,300,img_creditos.getWidth(fg),img_creditos.getHeight(fg));
        
        //Proceso donde cargo el Top para desplegarlo en Label
        label_top=new JLabel();
        jugadores=esc.DevolverNombres();
        puntos=esc.DevolverPuntos();
        label_top.setBounds(200,200,350,400);
        for(int i=0;i<10;i++)
        {
            label_top.setText(label_top.getText()+"<html><body>"+(i+1)+" : "+jugadores[i]+" ... "+puntos[i]+" Puntos<br>"+(i+2)+" : "+jugadores[i+1]+" ... "+puntos[i+1]+" Puntos<br>"+(i+3)+" : "+jugadores[i+2]+" ... "+puntos[i+2]+" Puntos<br>"+(i+4)+" : "+jugadores[i+3]+" ... "+puntos[i+3]+" Puntos<br>"+(i+5)+" : "+jugadores[i+4]+" ... "+puntos[i+4]+" Puntos<br>"+(i+6)+" : "+jugadores[i+5]+" ... "+puntos[i+5]+" Puntos<br>"+(i+7)+" : "+jugadores[i+6]+" ... "+puntos[i+6]+" Puntos<br>"+(i+8)+" : "+jugadores[i+7]+" ... "+puntos[i+7]+" Puntos<br>"+(i+9)+" : "+jugadores[i+8]+" ... "+puntos[i+8]+" Puntos<br>"+(i+10)+" : "+jugadores[i+9]+" ... "+puntos[i+9]+" Puntos<br>"+"</body></html>");
        }
        label_top.setFont(new java.awt.Font("Impact", 0, 18)); 
        label_top.setForeground(Color.red);
        label_top.setVisible(false);
           
        //TextField donde ingresas el nombre del jugador y al Presionar Enter arranca el juego
        name=new JTextField("Ingrese su nombre y presione Enter: ");
        name.setFont(new java.awt.Font("Impact", 0, 15));
        name.setForeground(Color.red);
        name.setVisible(false);
        name.setBounds(150,220,300,40);
        name.addMouseListener(escuchador);
        name.addKeyListener(KeysMenu);
        //Añadimos todos los componentes al Frame con super
        super.add(logo);
        super.add(play);
        super.add(top_ten);
        super.add(instruction);
        super.add(credits);
        super.add(exit);
        super.add(label_instrucciones);
        super.add(label_creditos);
        super.add(label_top);
        super.add(name);
  
    }

    public String getName_player() {
        return name_player;
    }
    
    
//Escuchador del mouse para las opciones del menu
    MouseListener escuchador = new MouseListener()
    {
        @Override
        public void mouseClicked(MouseEvent me) {
            //Si presiona PLAY desplega el TextField y esconde las demas opciones
            if(me.getSource()==play){
               
               name.setVisible(true);
               top_ten.setVisible(false);
               instruction.setVisible(false);
               credits.setVisible(false);
               exit.setVisible(false);
               
            }
            //Si presionas EXIT cierra el juego
            if(me.getSource()==exit){
                System.exit(0);
            }
            if(me.getSource()==name){
                name.setText("");
            }
        }

        @Override
        public void mousePressed(MouseEvent me) {
           //Desplega la imagen de controles y esconde las demas opciones
            if(me.getSource()==instruction){
                instruction.setVisible(false);
                credits.setVisible(false);
                exit.setVisible(false);
                label_instrucciones.setVisible(true);
            }
            if(me.getSource()==credits)
            {
                //Desplega la Imagen de los creditos y esconde las demas opciones
                instruction.setVisible(false);
                credits.setVisible(false);
                exit.setVisible(false);
                label_creditos.setVisible(true);
            }
            if(me.getSource()==top_ten)
            {
                //Desplega el Label con el TOP 10 
                instruction.setVisible(false);
                credits.setVisible(false);
                exit.setVisible(false);
                label_top.setVisible(true);
            }
        }
            //Cuando suelte el click el menu vuelve a su estado original
        @Override
        public void mouseReleased(MouseEvent me) {
            
                if(me.getSource()==instruction){
                label_instrucciones.setVisible(false);
                instruction.setVisible(true);
                credits.setVisible(true);
                exit.setVisible(true);
                }
                 if(me.getSource()==credits){
                label_creditos.setVisible(false);
                instruction.setVisible(true);
                credits.setVisible(true);
                exit.setVisible(true);
                }
                   if(me.getSource()==top_ten)
                { 
                label_top.setVisible(false);
                instruction.setVisible(true);
                credits.setVisible(true);
                exit.setVisible(true);
               
                }
        }

        @Override
        public void mouseEntered(MouseEvent me) {
           
        }

        @Override
        public void mouseExited(MouseEvent me) {
            
        }
        
    };
    //Si presiona enter guarda el nombre del jugador y lo envia al metodo que lo añade al Archivo
    //Igualmente inicia el juego
    KeyListener KeysMenu = new KeyListener(){
        @Override
        public void keyTyped(KeyEvent ke) {
            
        }

        @Override
        public void keyPressed(KeyEvent ke) {
            
            if(ke.getSource()==name){
                
                if(ke.getKeyCode()==KeyEvent.VK_ENTER){
                name_player=name.getText();
                    try {
                        esc.AñadirNombre(name_player);
                    } catch (IOException ex) {
                        System.out.println("No se pudo añadir el nombre");
                    }
                fg=new FrameGame();
                name.setVisible(false);
                name.setText("Ingrese su nombre y presione Enter: ");
                top_ten.setVisible(true);
                instruction.setVisible(true);
                credits.setVisible(true);
                exit.setVisible(true);
                
            }
        }
        }
        @Override
        public void keyReleased(KeyEvent ke) {
          
        }
    
        
};    
   public FrameGame getFg() {
        return fg;
    }

   //Instancia del menu
public static void main(String[] args) throws FileNotFoundException{
        
        Menu obj1 = new Menu();
     

    }
}