package Sonido;

import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
/** Clase que reproduce los archivos .wav en ubicados en la carpeta Audios
 *
 * @author Alberto Cristancho
 */
public class Reproductor {
        //Metodo que recibe una ruta en String donde se ubica el archivo .wav a reproducir
     public void ReproducirSonido(String nombreSonido) 
     { 
         try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(nombreSonido).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
               System.out.println("Error al reproducir el sonido.");
            }
     }
  
}
