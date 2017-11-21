package FramesGame;

import javax.swing.JFrame;

public class FrameGame extends JFrame{

/* Frame principal que contiene el PanelGame o lienzo donde se hará el juego
 *
 */
    private PanelGame Pg;
   
    public FrameGame(){
        //Contructor que le da nombre y tamaño al frame, donde se instancia PanelGame
        Pg=new PanelGame();

        super.setTitle("RiverRaid");
        super.setContentPane(Pg);
        super.setBounds(0,0,2000,1000);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setVisible(true);
    }
    //Get para obtener el Panel
    public PanelGame getPg() {
        return Pg;
    }
    
    
    
}