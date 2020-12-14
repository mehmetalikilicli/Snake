package yilanoyunu;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javafx.scene.shape.Ellipse;
import javax.swing.JLabel;

public class Yem extends JLabel {

    public int mgenislik = 20;

    Yem() {

        setPosition(20, 20);

    }

    @Override
    public void paint(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

        Ellipse2D elipse = new Ellipse2D.Double(1, 1, mgenislik - 2, mgenislik - 2);

        g2.setColor(Color.black);

        g2.setStroke(new BasicStroke(2));

        g2.draw(elipse);

        g2.setColor(Color.red);

        g2.fill(elipse);

    }

    public void setPosition(int PosX, int PosY) {

        setBounds(PosX, PosY, mgenislik, mgenislik);

    }

}
