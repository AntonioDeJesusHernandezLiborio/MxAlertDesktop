package modelo;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Imagen extends javax.swing.JPanel{
    String ruta;
    
    public Imagen(int x, int y, String ruta){
        this.setSize(x,y);
        this.ruta = ruta;
    }
    public void paint(Graphics g){
        Dimension height = getSize();
        Image imgExterno= new ImageIcon(ruta).getImage();
        g.drawImage(imgExterno,0,0, height.width, height.width, null);
        setOpaque(false);
        super.paintComponent(g);
    }
}
