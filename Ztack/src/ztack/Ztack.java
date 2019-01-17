/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ztack;
import java.util.*;
import javax.swing.*;
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
    
    public static Stack<String> deck;
    public static Pile pile;
    public static Hand AIHand;
    public static Hand playerHand;
    public static Stack<String> selected = new Stack();
    
    public static int O1State = 0;
    public static int O2State = 0;
    public static int O3State = 0;
    public static int O4State = 0;
    public static int O5State = 0;
    public static int O6State = 0;
    public static int PileState = 0;
    public static int DeckState = 0;

    public static Stack shuffleDeck(){
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
        
        return deck;
    }
    public static String cardPNG(String card, Boolean AI, Boolean grey){
        
        if("14 X".equals(card)){
            return "blank.png";
        }
        
        String output = "";
        
        if(AI == true){
            output = "cardBack";
        }
        else{
            String[] temp = card.split(" ");
            int number = Integer.parseInt(temp[0]);
            char suit = temp[1].charAt(0);
        
            switch (number) {
                case 1:
                    output+="ace";
                    break;
                case 11:
                    output+="jack";
                    break;
                case 12:
                    output+="queen";
                    break;
                case 13:
                    output+="king";
                    break;
                default:
                    output+=number;
                    break;
            }
        
            output+="_of_";
            
            switch (suit) {
                case 'S':
                    output+="spades";
                    break;
                case 'C':
                    output+="clubs";
                    break;
                case 'H':
                    output+="hearts";
                    break;
                case 'D':
                    output+="diamonds";
                    break;
                default:
                    break;
            }
        }
        if(grey == true){
            output += "_grey";
        }
        output+=".png";
        
        return output;
    }
    public void displayHand(Hand hand, Boolean AI){
        if(AI == false){
            cardOne1.setIcon(new ImageIcon("src/"+cardPNG(hand.cardsInHand[0], AI, false)));
            cardOne2.setIcon(new ImageIcon("src/"+cardPNG(hand.cardsInHand[1], AI, false)));
            cardOne3.setIcon(new ImageIcon("src/"+cardPNG(hand.cardsInHand[2], AI, false)));
            cardOne4.setIcon(new ImageIcon("src/"+cardPNG(hand.cardsInHand[3], AI, false)));
            cardOne5.setIcon(new ImageIcon("src/"+cardPNG(hand.cardsInHand[4], AI, false)));
            cardOne6.setIcon(new ImageIcon("src/"+cardPNG(hand.cardsInHand[5], AI, false)));
        }
        else{
            cardTwo1.setIcon(new ImageIcon("src/"+cardPNG(hand.cardsInHand[0], AI, false)));
            cardTwo2.setIcon(new ImageIcon("src/"+cardPNG(hand.cardsInHand[1], AI, false)));
            cardTwo3.setIcon(new ImageIcon("src/"+cardPNG(hand.cardsInHand[2], AI, false)));
            cardTwo4.setIcon(new ImageIcon("src/"+cardPNG(hand.cardsInHand[3], AI, false)));
            cardTwo5.setIcon(new ImageIcon("src/"+cardPNG(hand.cardsInHand[4], AI, false)));
            cardTwo6.setIcon(new ImageIcon("src/"+cardPNG(hand.cardsInHand[5], AI, false)));
        }
    }
    public void resetButtons(){
        cardOne1.setSelected(false);
        cardOne2.setSelected(false);
        cardOne3.setSelected(false);
        cardOne4.setSelected(false);
        cardOne5.setSelected(false);
        cardOne6.setSelected(false);
        pileButton.setSelected(false);
        deckDrawButton.setSelected(false);
        
    }
    public void resetState(){
        O1State = 0;
        O2State = 0;
        O3State = 0;
        O4State = 0;
        O5State = 0;
        O6State = 0;
        PileState = 0;
        DeckState = 0;
    }
    public void enableButtons(){
        cardOne1.setEnabled(true);
        cardOne2.setEnabled(true);
        cardOne3.setEnabled(true);
        cardOne4.setEnabled(true);
        cardOne5.setEnabled(true);
        cardOne6.setEnabled(true);
        
        cardTwo1.setEnabled(true);
        cardTwo2.setEnabled(true);
        cardTwo3.setEnabled(true);
        cardTwo4.setEnabled(true);
        cardTwo5.setEnabled(true);
        cardTwo6.setEnabled(true);
        
        pileButton.setEnabled(true);
        
        deckDrawButton.setEnabled(true);
        playButton.setEnabled(true);
        
        endGame.setEnabled(true);
    }
    public void displayPile(Pile pile){
        pileButton.setIcon(new ImageIcon("src/" + cardPNG(pile.topCard, false, false)));
        deckDrawButton.setIcon(new ImageIcon("src/cardBack_full.png"));
    }
    public void checkPlay(){
        
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
        points = new javax.swing.JLabel();
        endGame = new javax.swing.JButton();
        startButton = new javax.swing.JButton();
        drawLabel = new javax.swing.JLabel();
        pileLabel = new javax.swing.JLabel();
        pileButton = new javax.swing.JToggleButton();
        deckDrawButton = new javax.swing.JToggleButton();
        playButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cardOne1.setEnabled(false);
        cardOne1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardOne1ActionPerformed(evt);
            }
        });

        cardOne2.setEnabled(false);
        cardOne2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardOne2ActionPerformed(evt);
            }
        });

        cardOne3.setEnabled(false);
        cardOne3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardOne3ActionPerformed(evt);
            }
        });

        cardOne4.setEnabled(false);
        cardOne4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardOne4ActionPerformed(evt);
            }
        });

        cardOne5.setEnabled(false);
        cardOne5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardOne5ActionPerformed(evt);
            }
        });

        cardOne6.setEnabled(false);
        cardOne6.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cardOne6StateChanged(evt);
            }
        });
        cardOne6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardOne6ActionPerformed(evt);
            }
        });

        cardTwo1.setEnabled(false);
        cardTwo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardTwo1ActionPerformed(evt);
            }
        });

        cardTwo2.setEnabled(false);
        cardTwo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardTwo2ActionPerformed(evt);
            }
        });

        cardTwo3.setEnabled(false);
        cardTwo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardTwo3ActionPerformed(evt);
            }
        });

        cardTwo4.setEnabled(false);
        cardTwo4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardTwo4ActionPerformed(evt);
            }
        });

        cardTwo5.setEnabled(false);
        cardTwo5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardTwo5ActionPerformed(evt);
            }
        });

        cardTwo6.setEnabled(false);
        cardTwo6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardTwo6ActionPerformed(evt);
            }
        });

        points.setText("Points: 0");

        endGame.setText("End Round");
        endGame.setEnabled(false);
        endGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endGameActionPerformed(evt);
            }
        });

        startButton.setText("Start");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        drawLabel.setText("Draw");

        pileLabel.setText("Pile");

        pileButton.setEnabled(false);
        pileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pileButtonActionPerformed(evt);
            }
        });

        deckDrawButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cardBack_full.png"))); // NOI18N
        deckDrawButton.setEnabled(false);
        deckDrawButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deckDrawButtonActionPerformed(evt);
            }
        });

        playButton.setText("Play");
        playButton.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cardOne1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cardOne2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cardOne3, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(pileLabel)
                                        .addGap(248, 248, 248)
                                        .addComponent(drawLabel))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cardOne4, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cardOne5, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cardOne6, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(endGame)
                                            .addComponent(points, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(startButton)
                                        .addGap(25, 25, 25))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(pileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(109, 109, 109)
                                        .addComponent(deckDrawButton, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(231, 231, 231)))))
                        .addGap(27, 27, 27))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(playButton)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cardTwo1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cardTwo2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cardTwo3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cardTwo4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cardTwo5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cardTwo6, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                .addGap(6, 6, 6)
                .addComponent(startButton)
                .addGap(101, 101, 101)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addComponent(playButton)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(drawLabel)
                            .addComponent(pileLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cardOne3, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cardOne4, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cardOne2, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cardOne1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(points)
                                .addGap(31, 31, 31)
                                .addComponent(endGame))
                            .addComponent(cardOne5, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cardOne6, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(deckDrawButton, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
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
        O1State++;
        if(O1State%2 == 1){
            cardOne1.setIcon(new ImageIcon("src/"+cardPNG(playerHand.cardsInHand[0], false, true)));
            selected.push(playerHand.cardsInHand[0]);
        } else{
            cardOne1.setIcon(new ImageIcon("src/"+cardPNG(playerHand.cardsInHand[0], false, false)));
            resetButtons();
            resetState();
            displayHand(playerHand, false);
            displayPile(pile);
        }
    }//GEN-LAST:event_cardOne1ActionPerformed

    private void cardOne2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardOne2ActionPerformed
        O2State++;
        if (O2State % 2 == 1) {
            cardOne2.setIcon(new ImageIcon("src/" + cardPNG(playerHand.cardsInHand[1], false, true)));
            selected.push(playerHand.cardsInHand[1]);
        } else {
            cardOne2.setIcon(new ImageIcon("src/" + cardPNG(playerHand.cardsInHand[1], false, false)));
            resetButtons();
            resetState();
            displayHand(playerHand, false);
            displayPile(pile);
        }
    }//GEN-LAST:event_cardOne2ActionPerformed

    private void cardOne6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardOne6ActionPerformed
        O6State++;
        if (O6State % 2 == 1) {
            cardOne6.setIcon(new ImageIcon("src/" + cardPNG(playerHand.cardsInHand[5], false, true)));
            selected.push(playerHand.cardsInHand[5]);
        } else {
            cardOne6.setIcon(new ImageIcon("src/" + cardPNG(playerHand.cardsInHand[5], false, false)));
            resetButtons();
            resetState();
            displayHand(playerHand, false);
            displayPile(pile);
        }
    }//GEN-LAST:event_cardOne6ActionPerformed

    private void cardOne5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardOne5ActionPerformed
        O5State++;
        if (O5State % 2 == 1) {
            cardOne5.setIcon(new ImageIcon("src/" + cardPNG(playerHand.cardsInHand[4], false, true)));
            selected.push(playerHand.cardsInHand[4]);
        } else {
            cardOne5.setIcon(new ImageIcon("src/" + cardPNG(playerHand.cardsInHand[4], false, false)));
            resetButtons();
            resetState();
            displayHand(playerHand, false);
            displayPile(pile);
        }
    }//GEN-LAST:event_cardOne5ActionPerformed

    private void cardOne4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardOne4ActionPerformed
        O4State++;
        if (O4State % 2 == 1) {
            cardOne4.setIcon(new ImageIcon("src/" + cardPNG(playerHand.cardsInHand[3], false, true)));
            selected.push(playerHand.cardsInHand[3]);
        } else {
            cardOne4.setIcon(new ImageIcon("src/" + cardPNG(playerHand.cardsInHand[3], false, false)));
            resetButtons();
            resetState();
            displayHand(playerHand, false);
            displayPile(pile);
        }
    }//GEN-LAST:event_cardOne4ActionPerformed

    private void cardOne3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardOne3ActionPerformed
        O3State++;
        if (O3State % 2 == 1) {
            cardOne3.setIcon(new ImageIcon("src/" + cardPNG(playerHand.cardsInHand[2], false, true)));
            selected.push(playerHand.cardsInHand[2]);
        } else {
            cardOne3.setIcon(new ImageIcon("src/" + cardPNG(playerHand.cardsInHand[2], false, false)));
            resetButtons();
            resetState();
            displayHand(playerHand, false);
            displayPile(pile);
        }
    }//GEN-LAST:event_cardOne3ActionPerformed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        displayHand(AIHand, true);
        displayHand(playerHand, false);
        displayPile(pile);
        resetButtons();
        resetState();
        enableButtons();
        
        
        startButton.setEnabled(false);
    }//GEN-LAST:event_startButtonActionPerformed

    private void cardOne6StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cardOne6StateChanged
        
    }//GEN-LAST:event_cardOne6StateChanged

    private void pileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pileButtonActionPerformed
        PileState++;
        if (PileState % 2 == 1) {
            pileButton.setIcon(new ImageIcon("src/" + cardPNG(pile.topCard, false, true)));
        } else {
            pileButton.setIcon(new ImageIcon("src/" + cardPNG(pile.topCard, false, false)));
        }
    }//GEN-LAST:event_pileButtonActionPerformed

    private void deckDrawButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deckDrawButtonActionPerformed
        DeckState++;
        if (DeckState % 2 == 1) {
            deckDrawButton.setIcon(new ImageIcon("src/cardBack_full_grey.png"));
        } else {
            deckDrawButton.setIcon(new ImageIcon("src/cardBack_full.png"));
        }
    }//GEN-LAST:event_deckDrawButtonActionPerformed
            
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        deck = shuffleDeck();
        pile = new Pile(deck);
        AIHand = new Hand();
        playerHand = new Hand();
        for(int i = 0; i < 6; i++){
            AIHand.draw(deck);
            playerHand.draw(deck);
        }
       
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
    private javax.swing.JToggleButton deckDrawButton;
    private javax.swing.JLabel drawLabel;
    private javax.swing.JButton endGame;
    private javax.swing.JToggleButton pileButton;
    private javax.swing.JLabel pileLabel;
    private javax.swing.JButton playButton;
    private javax.swing.JLabel points;
    private javax.swing.JButton startButton;
    // End of variables declaration//GEN-END:variables
}
