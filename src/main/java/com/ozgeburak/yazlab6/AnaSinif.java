package com.ozgeburak.yazlab6;

import com.bulenkov.darcula.DarculaLaf;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class AnaSinif {

    static JFrame pencere;

    public static void main(String[] args) {
        // DarkLeaf Temasının Etkinleştirilmesi
        
        try {
            //UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
            UIManager.setLookAndFeel(new DarculaLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Program boyunca kullanılacak ana pencere oluşturuluyor
        // Başlatma Ekrani pencereye ekleniyor
        AnaSinif.pencere = new JFrame("Yazılım Laboratuvarı 2-3 - QLearning");
        BaslatmaEkrani baslatmaEkrani = new BaslatmaEkrani();
        pencere.add(baslatmaEkrani);
        pencere.pack();
        pencere.setResizable(false);
        pencere.setVisible(true);
        pencere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pencere.setLocationRelativeTo(null);
    }

}
