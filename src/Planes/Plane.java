package Planes;

import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Plane {
/* Clase que obtiene los atributos del avion, las imagenes del avion y el disparo
 * igualmente sus posiciones
 */
    
    private Image Imagen, imgshot;
    private int posx, posy, posxs, posys, Height, Width, sHeight, sWidth;
    
    private boolean shot;
    //cargamos imagenes del avion y del disparo
    public Plane(){
        shot=false;
        try{
            Imagen=ImageIO.read(new File("src/Images/A2.png"));
            imgshot=ImageIO.read(new File("src/Images/S1.png"));
            
        }catch(IOException ex){
            
            System.out.println("Imagen no encontrada.");
            
        }
        //Obtenemos ancho y lato
        Height=Imagen.getHeight(null);
        Width=Imagen.getWidth(null);
        
        sHeight=imgshot.getHeight(null);
        sWidth=imgshot.getWidth(null);
        
        posx=340-this.Height;
        posy=750-this.Width;
        
        posxs=posx+(Width/2)-(sWidth/2);
        posys=posy-sHeight;
    }  

     public void setPosxs(int posxs) {
        this.posxs = posxs;
    }
     
    public Image getImagen() {
        return Imagen;
    }

    public int getPosx() {
        return posx;
    }

    public int getPosy() {
        return posy;
    }

    public Image getimgShot() {
        return imgshot;
    }

    public int getPosxs() {
        return posxs;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }
    

    public int getPosys() {
        return posys;
    }

    public int getsHeight() {
        return sHeight;
    }

    public int getsWidth() {
        return sWidth;
    }
    //Metodo para mover la posicion del avion a la Izquierda o Derecha 
    public void Fly(int m){
       
        if(m==1){
            
            if(posx > 0){
            posx-=10;
            if(shot==false){
            posxs-=10;
            }
          }
        }
        if(m==2){
            
            if(posx < 1080-this.Width){
            posx+=10;
            if(shot==false){
            posxs+=10;
            }
          }
        }
    }
    //Metodo para subir el disparo, casi exactamente igual al Down() de la clase Enemie
    public void Shot(){
        
        if(posys>0){
            posys-=5;
        }
       
    }

    public void setPosys(int posys) {
        this.posys = posys;
    }
    
    //Metodo para obtener el estado del disparo, true si esta activo o false para inactivo
    public boolean getShot() {
        return shot;
    }

    public void setShot(boolean shot) {
        this.shot = shot;
    }
   
    public int getHeight() {
        return Height;
    }
    
    public int getWidth() {
        return Width;
    }

    /**
     *
     * Metodo que devuelve el rectangulo del ancho y alto del avion para usarlo en colisiones
     */
    public Rectangle2D area(){
        return new Rectangle2D.Double(posx, posy, 90,100);
    }
      
}