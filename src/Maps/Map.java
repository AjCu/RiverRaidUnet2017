package Maps;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Map {
    
/*Clase que obtiene las rutas de los 3 mapas y el fondo de la derecha del juego
 * y los coloca en Imagenes para luego ser pintadas en PanelPlay
 */
 
    private Image Imagen,Imagen2,Imagen3, fondo,barra;
    private int Height, Width,Height2, Width2,Height3, Width3, fh, fw, bh, bw;
    
    public Map(){
        
        try{
            fondo=ImageIO.read(new File("src/Images/fondo.jpg"));
            Imagen=ImageIO.read(new File("src/Images/mapa2.png"));
            Imagen2=ImageIO.read(new File("src/Images/mapa1.png"));
            Imagen3=ImageIO.read(new File("src/Images/mapa3.png"));
            barra=ImageIO.read(new File("src/Images/barra.png"));
            
        
    }catch(IOException ex){
        
        System.out.println("Imagen no encontrada.");
    }
        Height=Imagen.getHeight(null);
        Width=Imagen.getWidth(null);
        Height2=Imagen2.getHeight(null);
        Width2=Imagen2.getWidth(null);
        Height3=Imagen3.getHeight(null);
        Width3=Imagen3.getWidth(null);
        fh=fondo.getHeight(null);
        fw=fondo.getWidth(null);
        bh=barra.getHeight(null);
        bw=barra.getWidth(null);
    }

    public Image getBarra() {
        return barra;
    }

    public int getBh() {
        return bh;
    }

    public int getBw() {
        return bw;
    }

    public Image getImagen2() {
        return Imagen2;
    }

    public Image getImagen3() {
        return Imagen3;
    }

    public int getHeight2() {
        return Height2;
    }

    public int getWidth2() {
        return Width2;
    }

    public int getHeight3() {
        return Height3;
    }

    public int getWidth3() {
        return Width3;
    }
    
    public Image getFondo() {
        return fondo;
    }

    public int getFh() {
        return fh;
    }

    public int getFw() {
        return fw;
    }
    

    public Image getImagen() {
        return Imagen;
    }

    public int getHeight() {
        return Height;
    }

    public int getWidth() {
        return Width;
    }
    
}  