package com.ozgeburak.yazlab6;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Random;
import javax.swing.JPanel;
import org.jfree.ui.RefineryUtilities;
import static com.ozgeburak.yazlab6.YerlestirmeEkrani.hedef_x;
import static com.ozgeburak.yazlab6.YerlestirmeEkrani.hedef_y;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JSlider;

public class QLearning extends JPanel {

    // Pencere boyutları
    public static int PENCERE_GENISLIK = 800;
    public static int PENCERE_YUKSEKLIK = 800;

    // Odul değerlerini ayar değişkenlerinden çekiyoruz.
    int engelOdul = BaslatmaEkrani.ayar_engelOdul; // -5
    int yolOdul = BaslatmaEkrani.ayar_gecisOdul; // 0
    int hedefOdul = BaslatmaEkrani.ayar_hedefOdul; // 5

    static int n = BaslatmaEkrani.matrisBoyut;
    static int alan_genislik = 800;
    static int alan_yukseklik = 800;
    static int[][] odul_tablosu;
    double Q[][];

    static int[] tipler;
    static ArrayList<Integer> sonlandiricilar = new ArrayList<>();
    Random random = new Random();
    Thread anaDongu;
    int[][] durumlar;
    static int donguHizi = 500;

    // parametre değerleri
    float alfa = YerlestirmeEkrani.ayar_alfa; // 0.01f
    float gama = YerlestirmeEkrani.ayar_gama; // 0.9f
    float epsilon = YerlestirmeEkrani.ayar_epsilon; // 0.25f
    float minEpsilon = YerlestirmeEkrani.ayar_epsilonMin; // 0.05f
    float epsilonDegisim = YerlestirmeEkrani.ayar_epsilonDegisim; // 0.0003

    static int mevcutPozisyonX = YerlestirmeEkrani.baslangic_x;
    static int mevcutPozisyonY = YerlestirmeEkrani.baslangic_y;
    //static int[] mevcutPozisyon = {YerlestirmeEkrani.baslangic_y, YerlestirmeEkrani.baslangic_x};

    // Sonlandirma değişkenleri
    int sonlandirma = 0;
    int sonlandirmaAdeti = YerlestirmeEkrani.ayar_sonlandirmaAdet;
    static boolean sonlandi = false;
    static double oncekiSkor = 0;

    ArrayList<YolDugum> yol = new ArrayList<YolDugum>();

    //Grafik değişkenleri
    int iterasyondakiAdim = 0;
    float iterasyondakiMaaliyet = 0f;
    static ArrayList<Integer> adimlar = new ArrayList<Integer>();
    static ArrayList<Float> maaliyetler = new ArrayList<Float>();

    HashMap<String, Integer> hareketler = new HashMap<String, Integer>();
    boolean carprazHareket = YerlestirmeEkrani.ayar_caprazHareket;
    boolean epsilonBasta0 = YerlestirmeEkrani.ayar_epsilonBasta0;
    boolean canliOnizlemeKapat = YerlestirmeEkrani.ayar_canliOnizlemeKapat;
    boolean episodeSayacKapat = YerlestirmeEkrani.ayar_episodeSayacKapat;
    
    static int episodeSayisi = 0;
    int oncekiAdim = -1;
    
    static FileWriter fwMatris;
    static BufferedWriter writerMatris;

    public QLearning() {
        this.setPreferredSize(new Dimension(PENCERE_GENISLIK, PENCERE_YUKSEKLIK));
        
        try {
            fwMatris = new FileWriter("matris_cikti.txt");
        } catch (IOException ex) {
            System.out.println("io hatasi");
        }
        
        this.anaDongu = new Thread() {
            @Override
            public void run() {
                setup();
                repaint();

                while (true) {
                    if (sonlandi != true) {
                        guncelle();
                    }
                    
                    if(canliOnizlemeKapat == false){
                        repaint();
                    }

                    try {
                        if (donguHizi > 0) {
                            Thread.sleep(donguHizi);
                        }
                    } catch (InterruptedException ex) {

                    }

                }
            }
        };

        this.anaDongu.start();
    }

    void setup(){
        durumlar = new int[n][n];

        // Durumlara göre q matrisini oluşturuyoruz
        if (carprazHareket == true) {
            Q = new double[n * n][8];
        } else {
            Q = new double[n * n][4];
        }

        mevcutPozisyonY = YerlestirmeEkrani.baslangic_y;
        mevcutPozisyonX = YerlestirmeEkrani.baslangic_x;

        int i, j;

        // Hedefi yerleştiriyoruz
        odul_tablosu[hedef_y][hedef_x] = BaslatmaEkrani.ayar_hedefOdul;
        tipler[n * hedef_y + hedef_x] = 3;
        sonlandiricilar.add(n * hedef_y + hedef_x);
        
        
        try {
            // Matris dosyasının yazdırılması
            fwMatris.write("( X, Y, Tip, Ödül Puanı ) \n" );
        } catch (IOException ex) {
            Logger.getLogger(QLearning.class.getName()).log(Level.SEVERE, null, ex);
        }
       for(i = 0; i < n; i++){
        for(j = 0; j < n; j++){
            try {
                String tip = "";
                if(odul_tablosu[i][j] == engelOdul){
                    tip = "Engel,";
                }else if(odul_tablosu[i][j] == hedefOdul){
                    tip = "Hedef,";
                }else if(odul_tablosu[i][j] == yolOdul){
                    tip = "Yol,";
                }
                fwMatris.write("( " + i + ", " + j + ", " + tip + " " + odul_tablosu[i][j] + " )\n" );
            } catch (IOException ex) {
                System.out.println("io hatasi");
            }
        }
       }
        try {
            fwMatris.close();
        } catch (IOException ex) {
            System.out.println("io hatasi");
        }
       

        // Hashmap içerisinde hareketler ve indislerinin tanımlanması
        hareketler.put("yukari", 0);
        hareketler.put("asagi", 1);
        hareketler.put("sol", 2);
        hareketler.put("sag", 3);
        if (carprazHareket == true) {
            hareketler.put("yukari-sol", 4);
            hareketler.put("yukari-sag", 5);
            hareketler.put("asagi-sol", 6);
            hareketler.put("asagi-sag", 7);
        }

        // Hücrelere idlerinin atanması durum kontrolü burdan yapılacak
        int k = 0;
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                durumlar[i][j] = k;
                k += 1;
            }
        }

    }

    void guncelle() {
        int mevcut_durum = durumlar[mevcutPozisyonY][mevcutPozisyonX];

        // Hareket seçme fonksiyonu ile yapılacak hareketi seçiyoruz.
        int hareketID = hareket_sec(mevcut_durum);

        // Hareketi seçtikten sonra ajanı yürütüyoruz.
        if (hareketID == 0) {
            mevcutPozisyonY -= 1;
        } else if (hareketID == 1) {
            mevcutPozisyonY += 1;
        } else if (hareketID == 2) {
            mevcutPozisyonX -= 1;
        } else if (hareketID == 3) {
            mevcutPozisyonX += 1;
        } else if (hareketID == 4) {
            mevcutPozisyonY -= 1;
            mevcutPozisyonX -= 1;
        } else if (hareketID == 5) {
            mevcutPozisyonY -= 1;
            mevcutPozisyonX += 1;
        } else if (hareketID == 6) {
            mevcutPozisyonY += 1;
            mevcutPozisyonX -= 1;
        } else if (hareketID == 7) {
            mevcutPozisyonY += 1;
            mevcutPozisyonX += 1;
        }

        // Hareketten sonra ajan yeni bir duruma geçmiş oluyor
        int yeni_durum = durumlar[mevcutPozisyonY][mevcutPozisyonX];

        // Hareketten sonra grafik çizimleri için adim ve maaliyet değerlerini tutuyoruz.
        iterasyondakiAdim++;
        iterasyondakiMaaliyet += odul_tablosu[mevcutPozisyonY][mevcutPozisyonX];

        // Hareket edilen düğümü yola ekliyoruz
        yol.add(new YolDugum(mevcutPozisyonX, mevcutPozisyonY));

        // Eğer hareket ettiğimiz hücre episode bitirmiyor ise
        if (!sonlandiricilar.contains(yeni_durum)) {
          
            // Q matrisinde güncelleme yapacağız
            Q[mevcut_durum][hareketID] += alfa * (odul_tablosu[mevcutPozisyonY][mevcutPozisyonX] + gama * (maksimumBul(Q[yeni_durum])) - Q[mevcut_durum][hareketID]);

        } else {
            
            // Eğer hareket ettiğimiz hücre episode bitiriyor ise
            episodeSayisi++;
            if(episodeSayacKapat == false){
                QLearningAyarEkrani.lblEpisode.setText("Episode : " + episodeSayisi);
            }
            
            
            
            
            //Optimizasyon
            //Sonlandırıcılardan sonra herhangi bir yöne hareket olmayacağı için maxq yu eklemiyoruz çünkü o hareketi gerçekleştirmiyoruz.
            //Normal formül de kullanılabilir burada algoritma yakınsadığı için sonucu etkilemiyor sadece sonuca varış hızını etkiliyor
            double skor = alfa * (odul_tablosu[mevcutPozisyonY][mevcutPozisyonX] - Q[mevcut_durum][hareketID]);
            Q[mevcut_durum][hareketID] += skor;

            if (mevcutPozisyonY == YerlestirmeEkrani.hedef_y && mevcutPozisyonX == YerlestirmeEkrani.hedef_x) {
                //Hedefe vardık mı diye bakıyoruz
                //System.out.println(skor);
                //episodeSayisi;
                System.out.println(episodeSayisi);
                
                epsilonBasta0 = false;
                    
                /*
                if (Float.compare((float)skor, (float)oncekiSkor) == 0) {
                    sonlandirma++;
                }
                */
                if(iterasyondakiAdim == oncekiAdim){
                    sonlandirma++;
                }

                if (sonlandirma > sonlandirmaAdeti) {
                    //System.out.println(sonlandirma + " " + sonlandirmaAdeti);
                    sonlandi = true;
                    yol.remove(yol.get(yol.size() - 1));
                    
                    grafikleriCizdir();
                    canliOnizlemeKapat = false;
                }
            }
            
            oncekiSkor = skor;
            oncekiAdim = iterasyondakiAdim;
            adimlar.add(iterasyondakiAdim);
            maaliyetler.add(iterasyondakiMaaliyet);

            mevcutPozisyonY = YerlestirmeEkrani.baslangic_y;
            mevcutPozisyonX = YerlestirmeEkrani.baslangic_x;

            iterasyondakiAdim = 0;
            iterasyondakiMaaliyet = 0;

            if (sonlandi != true) {
                yol.removeAll(yol);
            }
        }

        //Her episode sonu
        if (sonlandirma < sonlandirmaAdeti - 1) {
            if (epsilon > minEpsilon) {
                // Her episode sonunda epsilonu 0.0003 learning rate olmak üzere 
                // azaltıyoruz. Ama minimum Epsilonun altına düşmüyoruz.
                // sadece yol elde etmek için son gezintide minimum epsilonu da
                // kaldırıp 0 epsilon değeri ile gezineceğiz.
                epsilon += epsilonDegisim; // 0.0003f
            }
        } else {
            //Son gezintiyi epsilon 0 şeklinde sadece matrisi okuyarak yapıyoruz
            epsilon = 0;
        }
        
        if(epsilonBasta0 == true){
            epsilon = 0;
        }

    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, alan_genislik, alan_yukseklik);

        int cw = alan_genislik / n;
        int ch = alan_yukseklik / n;
        int c = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                g.setColor(new Color(71, 45, 60));
                g.fillRect(j * cw, i * ch, cw, ch);

                if (QLearning.tipler[c] == 1) {

                    g.drawImage(YerlestirmeEkrani.duvarResim, j * cw + 1, i * ch + 1, cw - 1, ch - 1, null);
                } else if (QLearning.tipler[c] == 3) {
                    g.drawImage(YerlestirmeEkrani.hedefResim, j * cw + 1, i * ch + 1, cw - 1, ch - 1, null);
                }

                c += 1;
                g.setColor(Color.blue);
               
                g.drawImage(YerlestirmeEkrani.ajanResim, mevcutPozisyonX * cw, mevcutPozisyonY * ch, cw, ch, null);
            }
        }

        if (sonlandi == true) {
            for (YolDugum node : yol) {
                g.drawImage(YerlestirmeEkrani.yolResim, node.x * cw, node.y * ch, cw, ch, null); 
            }
        }

    }

    int hareket_sec(int mevcut_durum) {
        //System.out.println(mevcutPozisyonX + " " + mevcutPozisyonY);
        float rnd = random.nextFloat();
        //System.out.println("random: " + rnd);
        //System.out.println("epsilon: " + epsilon);
        if (rnd <= epsilon) {
            // Epsilondan küçük değerlerde rastgele hareket yapıyoruz.
            //System.out.println("rastgele hareket: " + rnd);
            ArrayList<String> yapilabilecek_hareketler = new ArrayList<String>();
            
            if (mevcutPozisyonY != 0) {
                yapilabilecek_hareketler.add("yukari");
            }
            if (mevcutPozisyonY != n - 1) {
                yapilabilecek_hareketler.add("asagi");
            }

            if (mevcutPozisyonX != 0) {
                yapilabilecek_hareketler.add("sol");
            }

            if (mevcutPozisyonX != n - 1) {
                yapilabilecek_hareketler.add("sag");
            }
            
            if (carprazHareket == true) {
                if (mevcutPozisyonY != 0 && mevcutPozisyonX != 0) {
                    yapilabilecek_hareketler.add("yukari-sol");
                }

                if (mevcutPozisyonY != 0 && mevcutPozisyonX != n - 1) {
                    yapilabilecek_hareketler.add("yukari-sag");
                }

                if (mevcutPozisyonY != n - 1 && mevcutPozisyonX != 0) {
                    yapilabilecek_hareketler.add("asagi-sol");
                }

                if (mevcutPozisyonY != n - 1 && mevcutPozisyonX != n - 1) {
                    yapilabilecek_hareketler.add("asagi-sag");
                }
            }

            int hareket = hareketler.get(yapilabilecek_hareketler.get(random.nextInt(yapilabilecek_hareketler.size() - 1)));
            
            return hareket;
        } else {
            
            //Epsilondan büyük değerlerde Q Matrisinden faydalanarak hareket ediyoruz.
            
            ArrayList<Double> yapilabilecek_hareketler = new ArrayList<Double>();
            
            double minimumDurum = minimumBul(Q[mevcut_durum]);
            int minimizeDegeri = 100;
            
            //Q Matrisindeki maksimum durumu bulmalıyız.
            //Eğer geçerli bir hareketse durumu yapılabilecek bir hareket olarak ekliyoruz.
            //Eğer geçersiz bir hareketse indiste atlama olmaması için değerini azaltıp
            // Listeye ekliyoruz.
            if (mevcutPozisyonY != 0) {
                yapilabilecek_hareketler.add(Q[mevcut_durum][0]);
            } else {
                yapilabilecek_hareketler.add(minimumDurum - minimizeDegeri);
            }
            if (mevcutPozisyonY != n - 1) {
                yapilabilecek_hareketler.add(Q[mevcut_durum][1]);
            } else {
                yapilabilecek_hareketler.add(minimumDurum - minimizeDegeri);
            }

            if (mevcutPozisyonX != 0) {
                yapilabilecek_hareketler.add(Q[mevcut_durum][2]);
            } else {
                yapilabilecek_hareketler.add(minimumDurum - minimizeDegeri);
            }

            if (mevcutPozisyonX != n - 1) {
                yapilabilecek_hareketler.add(Q[mevcut_durum][3]);
            } else {
                yapilabilecek_hareketler.add(minimumDurum - minimizeDegeri);
            }

            if (carprazHareket == true) {
                if (mevcutPozisyonY != 0 && mevcutPozisyonX != 0) {
                    yapilabilecek_hareketler.add(Q[mevcut_durum][4]);
                } else {
                    yapilabilecek_hareketler.add(minimumDurum - minimizeDegeri);
                }

                if (mevcutPozisyonY != 0 && mevcutPozisyonX != n - 1) {
                    yapilabilecek_hareketler.add(Q[mevcut_durum][5]);
                } else {
                    yapilabilecek_hareketler.add(minimumDurum - minimizeDegeri);
                }

                if (mevcutPozisyonY != n - 1 && mevcutPozisyonX != 0) {
                    yapilabilecek_hareketler.add(Q[mevcut_durum][6]);
                } else {
                    yapilabilecek_hareketler.add(minimumDurum - minimizeDegeri);
                }

                if (mevcutPozisyonY != n - 1 && mevcutPozisyonX != n - 1) {
                    yapilabilecek_hareketler.add(Q[mevcut_durum][7]);
                } else {
                    yapilabilecek_hareketler.add(minimumDurum - minimizeDegeri);
                }
            }

            ArrayList<Integer> rastgeleHareket = new ArrayList<Integer>();
            ListIterator<Double> it = yapilabilecek_hareketler.listIterator();
            while (it.hasNext()) {
                int index = it.nextIndex();
                double hareket = it.next();
                //burda diziyi geziyoruz
                //it.nextIndex(); // i
                //it.next(); // a

                if (Double.compare(hareket, Collections.max(yapilabilecek_hareketler)) == 0) {
                    rastgeleHareket.add(index);
                }

            }
            
            //Maksimumlardan yine rastgele seçiyoruz.
            int hareket = rastgeleHareket.get(random.nextInt(rastgeleHareket.size()));
            return hareket;

        }

    }

    public static void grafikleriCizdir() {
        Grafik chart = new Grafik(
                "Episode via steps",
                "Episode via steps", "steps");

        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);

        Grafik chart2 = new Grafik(
                "Episode via costs",
                "Episode via costs", "costs");

        chart2.pack();
        RefineryUtilities.centerFrameOnScreen(chart2);
        chart2.setVisible(true);
    }

    public static int minimumBul(int[] dizi) {
        int minimum = dizi[0];
        for (int i = 1; i < dizi.length; i++) {
            if (dizi[i] < minimum) {
                minimum = dizi[i];
            }
        }
        return minimum;
    }

    public static double minimumBul(double[] dizi) {
        double minimum = dizi[0];
        for (int i = 1; i < dizi.length; i++) {
            if (dizi[i] < minimum) {
                minimum = dizi[i];
            }
        }
        return minimum;
    }

    public static double maksimumBul(double[] dizi) {
        double maksimum = dizi[0];
        for (int i = 1; i < dizi.length; i++) {
            if (dizi[i] > maksimum) {
                maksimum = dizi[i];
            }
        }
        return maksimum;
    }

}
