package com.ozgeburak.yazlab6;

import static com.ozgeburak.yazlab6.QLearning.n;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import javax.swing.JFrame;
import static com.ozgeburak.yazlab6.QLearning.alan_genislik;
import static com.ozgeburak.yazlab6.QLearning.alan_yukseklik;
import static com.ozgeburak.yazlab6.QLearning.odul_tablosu;
import static com.ozgeburak.yazlab6.QLearning.sonlandiricilar;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class YerlestirmeEkrani extends javax.swing.JPanel implements MouseListener {

    Random random = new Random();

    // Ajanın yerleştirilmesi için gerekli konum değişkenleri
    static int baslangic_x = 0;
    static int baslangic_y = 0;
    static int hedef_x = 1;
    static int hedef_y = 1;

    // Algoritmayla ilgili parametereleri kullanıcı arayüzünden alıyoruz
    static float ayar_alfa;
    static float ayar_gama;
    static float ayar_epsilon;
    static float ayar_epsilonMin;
    static float ayar_epsilonDegisim;
    static int ayar_sonlandirmaAdet;
    static boolean ayar_epsilonBasta0;
    static boolean ayar_caprazHareket;
    static boolean ayar_canliOnizlemeKapat;
    static boolean ayar_episodeSayacKapat;

    // Görsellerin değişkenleri
    static BufferedImage duvarResim = null;
    static BufferedImage ajanResim = null;
    static BufferedImage hedefResim = null;
    static BufferedImage yolResim = null;

    // Başlatma ekranındaki engel oranından engel miktarını hesaplıyoruz
    static int engeller = (int) (n * n / 100.0f * BaslatmaEkrani.matrisEngelOrani);

    public YerlestirmeEkrani() {
        addMouseListener(this);

        //Görsel Yüklemeleri
        try {
            if (duvarResim == null) {
                duvarResim = ImageIO.read(new File("kaynaklar/duvar.png"));
                ajanResim = ImageIO.read(new File("kaynaklar/ajan.png"));
                hedefResim = ImageIO.read(new File("kaynaklar/hedef.png"));
                yolResim = ImageIO.read(new File("kaynaklar/yolDugum.png"));
            }
        } catch (IOException ex) {
            Logger.getLogger(YerlestirmeEkrani.class.getName()).log(Level.SEVERE, null, ex);
        }

        initComponents();

        // Ödül Matrisini başlatma ekranından aldığımız geçiş değerleri ile oluşturuyoruz.
        // Daha sonrasında engel ve hedef değerleri de bu matrise eklenecek.
        QLearning.odul_tablosu = new int[QLearning.n][QLearning.n];
        for (int i = 0; i < QLearning.n; i++) {
            for (int j = 0; j < QLearning.n; j++) {
                QLearning.odul_tablosu[i][j] = BaslatmaEkrani.ayar_gecisOdul;
            }
        }

        //Görseller için hangi spriteın çizileceğini bilgisi için bir dizi oluşturuyoruz.
        QLearning.tipler = new int[n * n];

        //Varsayılan olarak bütün tiğ değişkenlerini 0 yaptık.
        // 0 - Boş
        // 1 - Engel
        for (int i = 0; i < QLearning.tipler.length; i++) {
            QLearning.tipler[i] = 0;
        }

        int i, j;

        // Engellerin rastgele belirli yüzde ile yerleştirilmesi
        // episode bitişinin kontrolü için sonlandirici matrisine eklenmesi
        while (engeller != 0) {
            i = random.nextInt(n); // 0 - n-1
            j = random.nextInt(n);
            if (QLearning.odul_tablosu[i][j] == BaslatmaEkrani.ayar_gecisOdul) {
                odul_tablosu[i][j] = BaslatmaEkrani.ayar_engelOdul;
                engeller -= 1;
                QLearning.tipler[n * i + j] = 1;
                sonlandiricilar.add(n * i + j);
            }
        }

    }

    @Override
    public void paint(Graphics g) {

        //Çizim işlemleri
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        int cw = alan_genislik / n;
        int ch = alan_yukseklik / n;
        int c = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                g.setColor(Color.black);
                g.fillRect(j * cw, i * ch, cw, ch);

                if (QLearning.tipler[c] == 1) {
                    g.drawImage(duvarResim, j * cw + 1, i * ch + 1, cw - 1, ch - 1, null);
                }

                c += 1;

            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtAlfa = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtGama = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtEpsilon = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtMinEpsilon = new javax.swing.JTextField();
        txtEpsilonDegisim = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtSonlandirmaAdeti = new javax.swing.JTextField();
        checkCapraz = new javax.swing.JCheckBox();
        checkEpsilonBasta0 = new javax.swing.JCheckBox();
        checkCanliOnizlemeKapat = new javax.swing.JCheckBox();
        checkEpisodeGosterme = new javax.swing.JCheckBox();

        setPreferredSize(new java.awt.Dimension(1000, 800));

        jLabel1.setText("Başlangıç");

        jTextField1.setText("0");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.setText("0");

        jLabel2.setText("Hedef");

        jLabel3.setText("X");

        jLabel4.setText("Y");

        jLabel5.setText("X");

        jLabel6.setText("Y");

        jTextField3.setText("1");
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jTextField4.setText("1");
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jButton1.setText("Çalıştır");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel10.setText("Alfa(Öğrenme Katsayısı)");

        txtAlfa.setText("0.01");
        txtAlfa.setToolTipText("");

        jLabel11.setText("Gama(İndirim Faktörü)");

        txtGama.setText("0.9");
        txtGama.setToolTipText("");

        jLabel12.setText("Epsilon");

        txtEpsilon.setText("0.25");
        txtEpsilon.setToolTipText("");

        jLabel13.setText("Minimum Epsilon");

        txtMinEpsilon.setText("0.05");
        txtMinEpsilon.setToolTipText("");

        txtEpsilonDegisim.setText("-0.0003");
        txtEpsilonDegisim.setToolTipText("");

        jLabel14.setText("Epsilon Değişim");

        jLabel15.setText("Sonlandırma Adeti");

        txtSonlandirmaAdeti.setText("10");

        checkCapraz.setText("Çapraz Hareket");

        checkEpsilonBasta0.setText("Epsilon 0 Gezinmesi");
        checkEpsilonBasta0.setActionCommand("");

        checkCanliOnizlemeKapat.setText("Canlı Önizlemeyi Kapat");

        checkEpisodeGosterme.setText("Episode Sayacını Kapat");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(810, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkEpisodeGosterme)
                    .addComponent(checkCanliOnizlemeKapat)
                    .addComponent(checkEpsilonBasta0)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel15)
                        .addComponent(jLabel14)
                        .addComponent(jLabel13)
                        .addComponent(jLabel12)
                        .addComponent(jLabel11)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField1)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField3)
                                .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtAlfa)
                        .addComponent(txtGama)
                        .addComponent(txtEpsilon)
                        .addComponent(txtMinEpsilon)
                        .addComponent(txtEpsilonDegisim)
                        .addComponent(txtSonlandirmaAdeti)
                        .addComponent(checkCapraz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAlfa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtGama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEpsilon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMinEpsilon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEpsilonDegisim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSonlandirmaAdeti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(checkCapraz)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkEpsilonBasta0)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkCanliOnizlemeKapat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkEpisodeGosterme)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(0, 247, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Arayüzden gerekli kontrol değişkenlerinin alınması
        baslangic_x = Integer.parseInt(jTextField1.getText());
        baslangic_y = Integer.parseInt(jTextField2.getText());
        hedef_x = Integer.parseInt(jTextField3.getText());
        hedef_y = Integer.parseInt(jTextField4.getText());
        ayar_alfa = Float.parseFloat(txtAlfa.getText());
        ayar_gama = Float.parseFloat(txtGama.getText());
        ayar_epsilon = Float.parseFloat(txtEpsilon.getText());
        ayar_epsilonMin = Float.parseFloat(txtMinEpsilon.getText());
        ayar_epsilonDegisim = Float.parseFloat(txtEpsilonDegisim.getText());
        ayar_sonlandirmaAdet = Integer.parseInt(txtSonlandirmaAdeti.getText());

        if (checkCapraz.isSelected() == true) {
            ayar_caprazHareket = true;
        } else {
            ayar_caprazHareket = false;
        }

        if (checkEpsilonBasta0.isSelected() == true) {
            ayar_epsilonBasta0 = true;
        } else {
            ayar_epsilonBasta0 = false;
        }

        if (checkCanliOnizlemeKapat.isSelected() == true) {
            ayar_canliOnizlemeKapat = true;
        } else {
            ayar_canliOnizlemeKapat = false;
        }
        
        if (checkEpisodeGosterme.isSelected() == true) {
            ayar_episodeSayacKapat = true;
        } else {
            ayar_episodeSayacKapat = false;
        }

        //Ana uygulama penceresinin oluşturulması
        QLearningAyarEkrani ayarEkrani = new QLearningAyarEkrani();
        QLearning visualize = new QLearning();
        visualize.setBounds(0, 0, 800, 800);
        ayarEkrani.add(visualize);
        AnaSinif.pencere.add(ayarEkrani);
        AnaSinif.pencere.pack();
        AnaSinif.pencere.setResizable(false);
        AnaSinif.pencere.setVisible(true);
        AnaSinif.pencere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AnaSinif.pencere.setLocationRelativeTo(null);

        AnaSinif.pencere.remove(this);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkCanliOnizlemeKapat;
    private javax.swing.JCheckBox checkCapraz;
    private javax.swing.JCheckBox checkEpisodeGosterme;
    private javax.swing.JCheckBox checkEpsilonBasta0;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField txtAlfa;
    private javax.swing.JTextField txtEpsilon;
    private javax.swing.JTextField txtEpsilonDegisim;
    private javax.swing.JTextField txtGama;
    private javax.swing.JTextField txtMinEpsilon;
    private javax.swing.JTextField txtSonlandirmaAdeti;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {
        String imlecX;
        String imlecY;
        //Sınırlar içerisinde
        if (e.getX() > 0 && e.getX() < alan_genislik && e.getY() > 0 && e.getY() < alan_yukseklik) {
            //Sol mouse tiki
            if (e.getButton() == MouseEvent.BUTTON1) {
                imlecX = Integer.toString(e.getX() / (alan_genislik / n));
                imlecY = Integer.toString(e.getY() / (alan_yukseklik / n));
                jTextField1.setText(imlecX);
                jTextField2.setText(imlecY);
            }

            //Sag mouse tiki
            if (e.getButton() == MouseEvent.BUTTON3) {
                imlecX = Integer.toString(e.getX() / (alan_genislik / n));
                imlecY = Integer.toString(e.getY() / (alan_yukseklik / n));
                jTextField3.setText(imlecX);
                jTextField4.setText(imlecY);
            }

        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
