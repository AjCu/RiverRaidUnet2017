package Enemies;

import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

/** Clase donde se cargan las imaggenes de los Enemigos(Helicoptero Verde,Naranja,Barco y Barril)
 *
 * @author Alberto Cristancho
 */
public class Enemie {

    private int Hx, Hy, Hwidth, Hheight;
    private int Hx2, Hy2, Hwidth2, Hheight2;
    private int Bx, By, Bwidth, Bheight;
    private int Bx2, By2, Bwidth2, Bheight2;
    private Image imgHeli, imgHeli2, imgShip,imgBarril;
    private Random r;
    
 /** Constructor que incluye todas las imagenes, las posiciones y un aleatorio para generar dichas posiciones
 *
 * @author Alberto Cristancho
 */
    
    public Enemie(){
        
        r=new Random();
        try{
            
        //cargamos imagenes del barco, ambos helicopteros y damos valores
        imgHeli=ImageIO.read(new File("src/Images/1.png"));
        imgHeli2=ImageIO.read(new File("src/Images/2.png"));
        imgShip=ImageIO.read(new File("src/Images/barquito.png"));
        imgBarril=ImageIO.read(new File("src/Images/barril.png"));
            
        }catch(IOException ex){
        System.out.println("Imagen no encontrada.");
        }
        //Generamos posiciones aleatorias
        Bx=(r.nextInt(260)+140);
        By=1;
        
        Bx2=(r.nextInt(260)+140);
        By2=1;
        
        Hx=(r.nextInt(260)+140);
        Hy=1;
        
        Hx2=(r.nextInt(260)+140);
        Hy2=1;
        //Cargamos altos y anchos para todas las imagenes
        Bwidth=imgShip.getWidth(null);
        Bheight=imgShip.getHeight(null);
        
        Bwidth2=imgBarril.getWidth(null);
        Bheight2=imgBarril.getHeight(null);
        
        Hwidth=imgHeli.getWidth(null);
        Hheight=imgHeli.getHeight(null);
        
        Hwidth2=imgHeli2.getWidth(null);
        Hheight2=imgHeli2.getHeight(null);
        
    }
    //Metodo sencillo para bajar las posiciones en Y del barco
    public void Down(){
        
        if(By>0 && By<900){
            
            By+=2;
        }
    }
    //Metodo sencillo para bajar las posiciones en Y del Helicoptero Verde
    public void Down2(){
        if(Hy>0 && Hy<900){
            
            Hy+=3;
        }
    }
    //Metodo sencillo para bajar las posiciones en Y del Helicoptero Naranja
    public void Down3(){
        if(Hy2>0 && Hy2<900){
            
            Hy2+=3;
        }
    }
    //Metodo sencillo para bajar las posiciones en Y del Barril
     public void Down4()
    {
        if(By2>0 && By2<900){
            
            By2+=2.5;
        }
    }
    //Metodos Gets y Sets para los atributos de la clase
    public int getBx2() {
        return Bx2;
    }

    public void setBx2(int Bx2) {
        this.Bx2 = Bx2;
    }

    public int getBy2() {
        return By2;
    }

    public void setBy2(int By2) {
        this.By2 = By2;
    }

    public int getBwidth2() {
        return Bwidth2;
    }

    public void setBwidth2(int Bwidth2) {
        this.Bwidth2 = Bwidth2;
    }

    public int getBheight2() {
        return Bheight2;
    }

    public void setBheight2(int Bheight2) {
        this.Bheight2 = Bheight2;
    }

    public Image getImgBarril() {
        return imgBarril;
    }

    public void setImgBarril(Image imgBarril) {
        this.imgBarril = imgBarril;
    }
    
    public int getHx() {
        return Hx;
    }

    public void setHx(int Hx) {
        this.Hx = Hx;
    }

    public int getHy() {
        return Hy;
    }

    public void setHy(int Hy) {
        this.Hy = Hy;
    }

    public int getHwidth() {
        return Hwidth;
    }

    public void setHwidth(int Hwidth) {
        this.Hwidth = Hwidth;
    }

    public int getHheight() {
        return Hheight;
    }

    public void setHheight(int Hheight) {
        this.Hheight = Hheight;
    }

    public int getBx() {
        return Bx;
    }

    public void setBx(int Bx) {
        this.Bx = Bx;
    }

    public int getHx2() {
        return Hx2;
    }

    public void setHx2(int Hx2) {
        this.Hx2 = Hx2;
    }

    public int getHy2() {
        return Hy2;
    }

    public void setHy2(int Hy2) {
        this.Hy2 = Hy2;
    }

    public int getHwidth2() {
        return Hwidth2;
    }

    public void setHwidth2(int Hwidth2) {
        this.Hwidth2 = Hwidth2;
    }

    public int getHheight2() {
        return Hheight2;
    }

    public void setHheight2(int Hheight2) {
        this.Hheight2 = Hheight2;
    }

    public Image getImgHeli2() {
        return imgHeli2;
    }

    public void setImgHeli2(Image imgHeli2) {
        this.imgHeli2 = imgHeli2;
    }
    
    public int getBy() {
        return By;
    }

    public void setBy(int By) {
        this.By = By;
    }

    public int getBwidth() {
        return Bwidth;
    }

    public void setBwidth(int Bwidth) {
        this.Bwidth = Bwidth;
    }

    public int getBheight() {
        return Bheight;
    }

    public void setBheight(int Bheight) {
        this.Bheight = Bheight;
    }

    public Image getImgHeli() {
        return imgHeli;
    }

    public void setImgHeli(Image imgHeli) {
        this.imgHeli = imgHeli;
    }

    public Image getImgShip() {
        return imgShip;
    }

    public void setImgShip(Image imgShip) {
        this.imgShip = imgShip;
    }
    
    //Metodos que devuelven un rectangulo del tamaño del ancho y alto de las imagenes
      public Rectangle2D area(){
        return new Rectangle2D.Double(Bx, By, 128,128);
    }
        public Rectangle2D area2(){
        return new Rectangle2D.Double(Hx, Hy, 128,128);
    }
        public Rectangle2D area3(){
        return new Rectangle2D.Double(Hx2, Hy2, 128,128);
    }
         public Rectangle2D area4(){
        return new Rectangle2D.Double(Bx2, By2, 40,70);
    }
}