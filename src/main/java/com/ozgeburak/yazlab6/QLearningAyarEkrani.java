
package com.ozgeburak.yazlab6;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class QLearningAyarEkrani extends javax.swing.JPanel {

    
    public QLearningAyarEkrani() {
        initComponents();
        jSlider1.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
              //System.out.println("Slider1: " + slider1.getValue());
              QLearning.donguHizi = jSlider1.getValue();
            }
          });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSlider1 = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        lblEpisode = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1000, 800));

        jSlider1.setMaximum(1000);
        jSlider1.setValue(500);

        jLabel1.setText("Döngü Hızı(0-1000ms)");

        lblEpisode.setText("Episode Bilgisi Devre Dışı Bırakıldı");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(810, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEpisode)
                    .addComponent(jLabel1)
                    .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblEpisode)
                .addContainerGap(709, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSlider jSlider1;
    public static javax.swing.JLabel lblEpisode;
    // End of variables declaration//GEN-END:variables
}
