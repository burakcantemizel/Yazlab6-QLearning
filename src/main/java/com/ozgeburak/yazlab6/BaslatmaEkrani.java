package com.ozgeburak.yazlab6;

import javax.swing.JFrame;


public class BaslatmaEkrani extends javax.swing.JPanel {
    
    // Başlatma ekranında kullanıcıdan alınacak değeler
    static int matrisBoyut = 50;
    static int matris_sutun = 50;
    static int matrisEngelOrani = 30;
    static int ayar_engelOdul = -5;
    static int ayar_hedefOdul = 5;
    static int ayar_gecisOdul = 0;

    
    public BaslatmaEkrani() {
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField_boyut = new javax.swing.JTextField();
        jTextField_engel = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton_onizle = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtHedefOdul = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtGecisOdul = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtEngelOdul = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(320, 200));

        jLabel1.setText("Matris Boyutu (n x n) :");

        jTextField_boyut.setText("25");

        jTextField_engel.setText("30");

        jLabel3.setText("Engel Oranı (%)");

        jButton_onizle.setText("Matrisi Önizle");
        jButton_onizle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_onizleActionPerformed(evt);
            }
        });

        jLabel2.setText("Hedef Ödül(Pozitif)");

        txtHedefOdul.setText("5");

        jLabel4.setText("Geçiş Ödül(0 Optimum)");

        txtGecisOdul.setText("0");

        jLabel5.setText("Engel Ödül(Negatif)");

        txtEngelOdul.setText("-5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_onizle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEngelOdul)
                            .addComponent(jTextField_engel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                            .addComponent(jTextField_boyut, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtHedefOdul)
                            .addComponent(txtGecisOdul, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField_boyut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField_engel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtHedefOdul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtGecisOdul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtEngelOdul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_onizle)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_onizleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_onizleActionPerformed
        
        // Değerler arayüzden alınıp ilgili değişkenlere aktarılıyor.
        matrisBoyut = Integer.parseInt(jTextField_boyut.getText());
        matrisEngelOrani = Integer.parseInt(jTextField_engel.getText());
        ayar_engelOdul = Integer.parseInt(txtEngelOdul.getText());
        ayar_hedefOdul = Integer.parseInt(txtHedefOdul.getText());
        ayar_gecisOdul = Integer.parseInt(txtGecisOdul.getText());

        
        // Başlangıç ekranından sonra yerleştirmenin yapılacağı ekranı
        // ana pencereye ekliyoruz 
        YerlestirmeEkrani yerlestirmeEkrani = new YerlestirmeEkrani();
        AnaSinif.pencere.add(yerlestirmeEkrani);
        AnaSinif.pencere.pack();
        AnaSinif.pencere.setResizable(false);
        AnaSinif.pencere.setVisible(true);
        AnaSinif.pencere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AnaSinif.pencere.setLocationRelativeTo(null);
        
        AnaSinif.pencere.remove(this);
    }//GEN-LAST:event_jButton_onizleActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_onizle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField jTextField_boyut;
    private javax.swing.JTextField jTextField_engel;
    private javax.swing.JTextField txtEngelOdul;
    private javax.swing.JTextField txtGecisOdul;
    private javax.swing.JTextField txtHedefOdul;
    // End of variables declaration//GEN-END:variables
}
