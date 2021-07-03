
package spacegame;

import java.awt.HeadlessException;
import javax.swing.JFrame;

public class OyunEkrani extends JFrame {

    public OyunEkrani(String title) throws HeadlessException {
        super(title);
    }
    
   public static void main(String[] args) {
       OyunEkrani ekran =new OyunEkrani("Space Game");
       
       ekran.setResizable(false);
       ekran.setFocusable(false);
       ekran.setSize(800,600);
       ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Pencerenin köşesindeki kapatma tuşuna tıkladığımızda programımızın sonlanması için JFrame'in setDefaultCloseOperation metodunu, yine JFrame'de tanımlı EXIT_ON_CLOSE sabitiyle çağırıyoruz.
       
       Game game=new Game();
       game.requestFocus();
       game.addKeyListener(game);//Klavyeden işlemlerimizi almamızı sağlar
       
       game.setFocusable(true);
       game.setFocusTraversalKeysEnabled(false);//klavye işlemleri için
       
       ekran.add(game);
       ekran.setVisible(true);//true parametresi ile pencereyi görünür kılar. 
       

   }
}
