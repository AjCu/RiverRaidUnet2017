package FramesGame;

import java.awt.GridLayout;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;

public class PanelGame extends JPanel {
    
/** Clase que contiene el Panel donde se instancia el Panel de Procesos del juego
 *
 */
    private PanelPlay Pp;
    
    //Constructor que instancia el Panel Play y try catch por si no consigue la ruta de audio al invocar ReproducirSonido()
    public PanelGame(){

        try {
            Pp=new PanelPlay();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            Logger.getLogger(PanelGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.setLayout(new GridLayout(1,1));
        super.add(Pp);       
    }
    //Get para obtener el PanelPlay
    public PanelPlay getPp() {
        return Pp;
    }
    
    
   
    
}