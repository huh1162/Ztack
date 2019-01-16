/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ztack;
import java.util.Arrays;
import java.util.Stack;
/**
 *
 * @author henry
 */
public class Ztack extends javax.swing.JFrame {

    /**
     * Creates new form Ztack
     */
    public Ztack() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cardOne1 = new javax.swing.JToggleButton();
        cardOne2 = new javax.swing.JToggleButton();
        cardOne3 = new javax.swing.JToggleButton();
        cardOne4 = new javax.swing.JToggleButton();
        cardOne5 = new javax.swing.JToggleButton();
        cardOne6 = new javax.swing.JToggleButton();
        cardTwo1 = new javax.swing.JButton();
        cardTwo2 = new javax.swing.JButton();
        cardTwo3 = new javax.swing.JButton();
        cardTwo4 = new javax.swing.JButton();
        cardTwo5 = new javax.swing.JButton();
        cardTwo6 = new javax.swing.JButton();
        deck = new javax.swing.JButton();
        pile = new javax.swing.JButton();
        points = new javax.swing.JLabel();
        endGame = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cardOne1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardOne1ActionPerformed(evt);
            }
        });

        cardOne2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardOne2ActionPerformed(evt);
            }
        });

        cardOne3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardOne3ActionPerformed(evt);
            }
        });

        cardOne4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardOne4ActionPerformed(evt);
            }
        });

        cardOne5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardOne5ActionPerformed(evt);
            }
        });

        cardOne6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardOne6ActionPerformed(evt);
            }
        });

        cardTwo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardTwo1ActionPerformed(evt);
            }
        });

        cardTwo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardTwo2ActionPerformed(evt);
            }
        });

        cardTwo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardTwo3ActionPerformed(evt);
            }
        });

        cardTwo4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardTwo4ActionPerformed(evt);
            }
        });

        cardTwo5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardTwo5ActionPerformed(evt);
            }
        });

        cardTwo6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardTwo6ActionPerformed(evt);
            }
        });

        deck.setText("Draw");

        pile.setText("Pile");

        points.setText("Points: 0");

        endGame.setText("End Round");
        endGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endGameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cardOne1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(cardTwo1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cardTwo2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cardTwo3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cardTwo4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cardTwo5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cardTwo6, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cardOne2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cardOne6, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cardOne5, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cardOne4, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(pile, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(134, 134, 134)
                                .addComponent(deck, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cardOne3, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(endGame)
                            .addComponent(points, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cardTwo1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardTwo2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardTwo3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardTwo4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardTwo5, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardTwo6, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(99, 99, 99)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deck, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pile, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cardOne3, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardOne4, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardOne5, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardOne6, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardOne2, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardOne1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(points)
                        .addGap(31, 31, 31)
                        .addComponent(endGame)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cardTwo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardTwo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cardTwo1ActionPerformed

    private void cardTwo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardTwo2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cardTwo2ActionPerformed

    private void cardTwo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardTwo3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cardTwo3ActionPerformed

    private void cardTwo4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardTwo4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cardTwo4ActionPerformed

    private void cardTwo5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardTwo5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cardTwo5ActionPerformed

    private void cardTwo6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardTwo6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cardTwo6ActionPerformed

    private void endGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endGameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_endGameActionPerformed

    private void cardOne1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardOne1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cardOne1ActionPerformed

    private void cardOne2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardOne2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cardOne2ActionPerformed

    private void cardOne6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardOne6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cardOne6ActionPerformed

    private void cardOne5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardOne5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cardOne5ActionPerformed

    private void cardOne4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardOne4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cardOne4ActionPerformed

    private void cardOne3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardOne3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cardOne3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ztack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ztack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ztack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ztack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ztack().setVisible(true);
            }
        });
        
        String[] sortedDeck = new String[52];
        Stack<String> deck = new Stack();
        
        for(int i = 0; i < 13; i++){ // Initializes the sorted deck
            for(int j = 0; j < 4; j++){
                switch (j) {
                    case 0:
                        sortedDeck[j*13+i] = (i+1) + " C";
                        break;
                    case 1:
                        sortedDeck[j*13+i] = (i+1) + " D";
                        break;
                    case 2:
                        sortedDeck[j*13+i] = (i+1) + " H";
                        break;
                    default:
                        sortedDeck[j*13+i] = (i+1) + " S";
                        break;
                }
            }
        }
        
        int random;
        for(int i = 0; i < 52; i++){ // Creates the randomized stack of cards
            random = (int) (Math.random() * 52);
            while (sortedDeck[random] == "0"){
                random++;
                random = random%52;
            }
            
            deck.push(sortedDeck[random]);
            sortedDeck[random] = "0";
        }
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton cardOne1;
    private javax.swing.JToggleButton cardOne2;
    private javax.swing.JToggleButton cardOne3;
    private javax.swing.JToggleButton cardOne4;
    private javax.swing.JToggleButton cardOne5;
    private javax.swing.JToggleButton cardOne6;
    private javax.swing.JButton cardTwo1;
    private javax.swing.JButton cardTwo2;
    private javax.swing.JButton cardTwo3;
    private javax.swing.JButton cardTwo4;
    private javax.swing.JButton cardTwo5;
    private javax.swing.JButton cardTwo6;
    private javax.swing.JButton deck;
    private javax.swing.JButton endGame;
    private javax.swing.JButton pile;
    private javax.swing.JLabel points;
    // End of variables declaration//GEN-END:variables
}
