package yilanoyunu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class AnaPencere extends JFrame {

    //ekran genişlik ve yuksekliği

    private int mGenislik = 600;
    private int mYukseklik = 600;
    
    AnaPencere() {
        //pencereyi kapatınca program sonlanır
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        SetDimension(mGenislik, mYukseklik);//ekrani tam ortada acar
        //pencerenin boyutu değişmez

        setResizable(false);
        
        //setBounds(0, 0, mGenislik, mYukseklik);

        Yilan Y = new Yilan();
        
        add(Y);
        
    }

    //hangi ekranda olursa olsun ekranın tam ortasında 
    //cıkmasi icin
    public void SetDimension(int genislik, int yukseklik) {
        //ekran pikselini alır
        Dimension Dim = Toolkit.getDefaultToolkit().getScreenSize();
        
        int PosX = 0;
        int PosY = 0;
        System.out.println(Dim.width);
        System.out.println(Dim.height);

        //boyutlarin ekrandan buyuk olması durumunda boyutları kucultur
        if (mGenislik + 100 > Dim.width) {
            genislik = Dim.width - 100;
        }
        if (mYukseklik + 100 > Dim.height) {
            mYukseklik = Dim.height - 100;
        }
        
        PosX = (Dim.width - mGenislik) / 2;
        PosY = (Dim.height - mYukseklik) / 2;
        
        setBounds(PosX, PosY, mGenislik, mYukseklik);
        
    }
    
}
