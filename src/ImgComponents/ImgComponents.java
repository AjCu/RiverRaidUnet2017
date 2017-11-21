package ImgComponents;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
/** Clase que almacena las Imagenes y los Iconos a usar en el PanelPLayer
 *
 */
public class ImgComponents {
    
    private Image L, L1, L2, L3, O, T, title_image, score_image, control_image, border_image1, border_image2, border_image3, win_image, lose_image;
    private ImageIcon l, l1, l2, l3, o , t, title_icon, score_icon, control_icon;
     //Constructor donde instanciamos las imagenes y por tanto buscamos las rutas donde estan ubicados los archivos
   public ImgComponents(){
        
         try{
          
          L=ImageIO.read(new File("src/Images/letrero_vidas.png"));
          L1=ImageIO.read(new File("src/Images/corazon1.png"));
          L2=ImageIO.read(new File("src/Images/corazon2.png"));
          L3=ImageIO.read(new File("src/Images/corazon3.png"));
          O=ImageIO.read(new File("src/Images/gasolina.png"));
          T=ImageIO.read(new File("src/Images/tiempo.png"));
          title_image=ImageIO.read(new File("src/Images/logo1.png"));
          score_image=ImageIO.read(new File("src/Images/puntuacion.png"));
          control_image=ImageIO.read(new File("src/Images/controles23.png"));
          border_image1=ImageIO.read(new File("src/Images/fuelborde.png"));
          border_image2=ImageIO.read(new File("src/Images/scoreborde.png"));
          border_image3=ImageIO.read(new File("src/Images/clockborde.png"));
          win_image=ImageIO.read(new File("src/Images/Ganaste.png"));
          lose_image=ImageIO.read(new File("src/Images/Perdiste.png"));
          
       }catch(IOException ex){
           
           System.out.println("Imagen no encontrada.");
       }
       
       l=new ImageIcon(L);
       l1=new ImageIcon(L1);
       l2=new ImageIcon(L2);
       l3=new ImageIcon(L3);
       o=new ImageIcon(O);
       t=new ImageIcon(T);
       title_icon=new ImageIcon(title_image);
       score_icon=new ImageIcon(score_image);
       control_icon=new ImageIcon(control_image);
      
    }
   //Gets de todas las imagenes
    public Image getImageL(){
        return L;
    }
    
    public Image getImageL1(){
        return L1;
    }
    
    public Image getImageL2(){
        return L2;
    }
    
    public Image getImageL3(){
        return L3;
    }
    
    public Image getImageO(){
        return O;
    }
    
    public Image getImageT(){
        return T;
    }

    public Image getTitle_image() {
        return title_image;
    }

    public Image getScore_image() {
        return score_image;
    }

    public Image getControl_image() {
        return control_image;
    }

    public Image getBorder_image1() {
        return border_image1;
    }

    public Image getBorder_image2() {
        return border_image2;
    }

    public Image getBorder_image3() {
        return border_image3;
    }

    public Image getWin_image() {
        return win_image;
    }

    public Image getLose_image() {
        return lose_image;
    }
    
    public ImageIcon getL() {
        return l;
    }
    
    public ImageIcon getL1() {
        return l1;
    }

    public ImageIcon getL2() {
        return l2;
    }

    public ImageIcon getL3() {
        return l3;
    }

    public ImageIcon getO() {
        return o;
    }

    public ImageIcon getT() {
        return t;
    }

    public ImageIcon getTitle_icon() {
        return title_icon;
    }

    public ImageIcon getScore_icon() {
        return score_icon;
    }

    public ImageIcon getControl_icon() {
        return control_icon;
    }

}