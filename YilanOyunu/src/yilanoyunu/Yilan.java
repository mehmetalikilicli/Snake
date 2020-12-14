package yilanoyunu;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.Timer;

public class Yilan extends JLabel {

    public Kutu mHead = new Kutu();

    public Timer mTimer = null;

    public Yem mYem = new Yem();
    public Random mRandom = null;

    public ArrayList<Kutu> Liste = new ArrayList<Kutu>();

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;

        Rectangle2D rect = new Rectangle2D.Double(0, 0, getWidth(), getHeight());

        g2.setColor(Color.red);

        g2.setStroke(new BasicStroke(10));

        g2.draw(rect);

        //g2.setBackground(Color.yellow);
    }

    Yilan() {

        mRandom = new Random(System.currentTimeMillis());
        setFocusable(true);
        addKeyListener(new KlavyeKontrol());
        mTimer = new Timer(100, new TimerKontrol());
        mTimer.start();

        Liste.add(mHead);
        for (int i = 1; i < 10; i++) {
            KuyrukEkle();
        }

//        Kutu a = mHead.KutuOlustur();
//        Kutu b = a.KutuOlustur();
//        Kutu d = b.KutuOlustur();
//
//        add(a);
//        add(b);
//        add(d);
        add(mYem);
        add(mHead);
    }

    public void KuyrukEkle() {

        Kutu K = Liste.get(Liste.size() - 1).KutuOlustur();

        Liste.add(K);
        add(K);

    }

    public void YemEkle() {

        int width = getWidth() - 30 - mYem.mgenislik;
        int height = getHeight() - 30 - mYem.mgenislik;

        int PosX = 20 + Math.abs(mRandom.nextInt()) % width;
        int PosY = 20 + Math.abs(mRandom.nextInt()) % height;

        PosX = PosX - PosX % 20;
        PosY = PosY - PosY % 20;

        for (int i = 0; i < Liste.size(); i++) {
            if (PosX == Liste.get(i).getX() && PosY == Liste.get(i).getY()) {
                YemEkle();
                return;
            }
        }

        mYem.setPosition(PosX, PosY);

    }

    public void HepsiniYurut() {
        for (int i = Liste.size() - 1; i > 0; i--) {

            Kutu Onceki = Liste.get(i - 1);

            Kutu Sonraki = Liste.get(i);

            Liste.get(i).Hareket();

            Sonraki.mYon = Onceki.mYon;
        }
        mHead.Hareket();
    }

    public boolean CarpismaVarmi() {

        int Kalem = 10;
        int genislik = getWidth();
        int yukseklik = getHeight();

        if (mHead.getX() <= 10) {
            return true;
        }
        if (mHead.getX() + mHead.mGenislik >= genislik - Kalem) {
            return true;
        }

        if (mHead.getY() <= 10) {
            return true;
        }
        if (mHead.getY() + mHead.mGenislik >= yukseklik - Kalem) {
            return true;
        }

        for (int i = 1; i < Liste.size(); i++) {
            int x = Liste.get(i).getX();
            int y = Liste.get(i).getY();

            if (x == mHead.getX() && y == mHead.getY()) {
                return true;

            }
        }
        if (mYem.getX() == mHead.getX() && mYem.getY() == mHead.getY()) {

            KuyrukEkle();
            YemEkle();

        }
        {
            return false;
        }
    }

    class KlavyeKontrol implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {

            if (e.getKeyCode() == KeyEvent.VK_LEFT) {

                if (mHead.mYon != YON.SAG) {
                    mHead.mYon = YON.SOL;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

                if (mHead.mYon != YON.SOL) {
                    mHead.mYon = YON.SAG;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (mHead.mYon != YON.ASAGI) {
                    mHead.mYon = YON.YUKARİ;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (mHead.mYon != YON.YUKARİ) {
                    mHead.mYon = YON.ASAGI;
                }
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

    }

    class TimerKontrol implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            HepsiniYurut();
            if (CarpismaVarmi()) {
                mTimer.stop();
                JOptionPane.showMessageDialog(null, "Oyun Bitti :D", "Yılan Oyunu", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

}
