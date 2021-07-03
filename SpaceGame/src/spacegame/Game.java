
package spacegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;//topu ve uzay mekiğini hareket ettirmek için 
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;//bir tuşa bastığımız zaman gerekli metotlari çağırır.
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
class Ates{
   
    private  int x;
    private  int y;

    public Ates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
}
public class Game extends JPanel implements  KeyListener,ActionListener{
    Timer timer =new Timer(5,this);
    private int gecenSure=0;
    private  int harcanAtes=0;
    
    private BufferedImage image;
    private ArrayList<Ates> atesler= new ArrayList<Ates>();
    
    private int atestirY=1 ;
    private int topX =0;
    private int toptirX=5;
    private int uzayGemisiX=0;
    private int dirUzayX=20;
    public boolean kontrolEt(){
        for(Ates ates:atesler){
            if(new Rectangle(ates.getX(),ates.getY(),10,20).intersects(new Rectangle(topX,0,20,20))){
                return true;
            }
        }
        return  false;
    }
    
    public Game (){
        try {
            image = ImageIO.read(new FileImageInputStream(new File("SpaceShip.png")));
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        setBackground(Color.black);
        timer.start();
        
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        gecenSure +=5;
        g.setColor(Color.red);
        g.fillOval(topX, 0,20,20);
        g.drawImage(image,uzayGemisiX,490,image.getWidth()/18,image.getHeight()/18,this);
        
        for(Ates ates:atesler){
            if(ates.getY()<0){
                atesler.remove(ates);
            }
        }
        g.setColor(Color.BLUE);
         for(Ates ates:atesler){
          g.fillRect(ates.getX(),ates.getY(),10, 20);
        }
        if(kontrolEt()){
            timer.stop();
            String message ="Kazandınız!\n"+
                           "Harcanan ates :"+harcanAtes+"\n"+
                           "Geçen Süre: "+gecenSure/1000.0+"saniye";
            JOptionPane.showMessageDialog(this, message);
                    System.exit(0);
        }
    }

    @Override
    public void repaint() {
        super.repaint(); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        if(c ==KeyEvent.VK_LEFT){
            if(uzayGemisiX<=0){
                uzayGemisiX=0;
            }else{
                uzayGemisiX -=dirUzayX;
            }
        }
        else if(c ==KeyEvent.VK_RIGHT){
            if(uzayGemisiX >= 728){
                uzayGemisiX=728;
            }else{
                uzayGemisiX +=dirUzayX;
            }
        }
        else if(c == KeyEvent.VK_CONTROL){
            atesler.add(new Ates(uzayGemisiX+20,470));
            harcanAtes++;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
      
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(Ates ates:atesler){
            ates.setY(ates.getY()-atestirY);
        }
        
       topX += toptirX;
       if(topX>= 750){
           toptirX =-toptirX;
       }
       if(topX <= 0){
           toptirX =-toptirX;
       }
       repaint();
    }
    
}
