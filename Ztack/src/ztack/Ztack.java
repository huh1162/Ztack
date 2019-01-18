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
    public static Boolean playState = false;
    public static int playerPoints = 0;
    public static int AIPoints = 0;
    
    public static int O1State = 0;
    public static int O2State = 0;
    public static int O3State = 0;
    public static int O4State = 0;
    public static int O5State = 0;
    public static int O6State = 0;
    public static int PileState = 0;
    public static int DeckState = 0;
    public static int HandState = 0;

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
        deckButton.setSelected(false);
        viewAIHandButton.setText("View  AI  Hand");
        
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
        selected = new Stack();
        HandState = 0;
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
        
        pileButton.setEnabled(false);
        
        deckButton.setEnabled(false);
        playButton.setEnabled(true);
        drawButton.setEnabled(true);
        ZTackButton.setEnabled(true);
        
        viewAIHandButton.setEnabled(false);
        endGame.setEnabled(true);
    }
    public void disableButtons(){
        cardOne1.setEnabled(false);
        cardOne2.setEnabled(false);
        cardOne3.setEnabled(false);
        cardOne4.setEnabled(false);
        cardOne5.setEnabled(false);
        cardOne6.setEnabled(false);
        
        cardTwo1.setEnabled(false);
        cardTwo2.setEnabled(false);
        cardTwo3.setEnabled(false);
        cardTwo4.setEnabled(false);
        cardTwo5.setEnabled(false);
        cardTwo6.setEnabled(false);
        
        pileButton.setEnabled(true);
        
        deckButton.setEnabled(true);
        playButton.setEnabled(false);
        drawButton.setEnabled(false);
        ZTackButton.setEnabled(false);
        
        endGame.setEnabled(true);
    }
    public void displayPile(Pile pile){
        pileButton.setIcon(new ImageIcon("src/" + cardPNG(pile.topCard, false, false)));
        deckButton.setIcon(new ImageIcon("src/cardBack_full.png"));
    }
    public Boolean checkPlay(){
        Stack<String> temporary = new Stack();
        int number = 14;
        String middleMan;
        int selectedSize = selected.size();
        if(selectedSize == 0){
            warningLabel.setText("You have to play a card or draw a card.");
            return false;
        }
        for(int i = 0; i < selectedSize; i++){
            middleMan = selected.pop();
            temporary.push(middleMan);
            if(i == 0){
                number = Integer.parseInt(middleMan.split(" ")[0]);
                if(number == 14){
                    warningLabel.setText("You cannot play non-cards.");
                    return false;
                }
            }
            else{
                if(number != Integer.parseInt(middleMan.split(" ")[0])){
                    resetButtons();
                    resetState();
                    displayHand(playerHand, false);
                    displayPile(pile);
                    warningLabel.setText("Please play numbers of the same value.");
                    return false;
                }
            }
        }
        resetButtons();
        resetState();
        displayHand(playerHand, false);
        displayPile(pile);
        selected = temporary;
        warningLabel.setText("Draw from the pile or the deck?");
        return true;
    }
    public void winCheck(){
        if(playerPoints > 100 || AIPoints > 100){
            if (AIPoints < playerPoints) {
                result.setText("You lose...");
            } else if (AIPoints > playerPoints) {
                result.setText("You win!");
            } else {
                result.setText("You drew.");
            }
            startButton.setEnabled(false);
            endGame.setEnabled(true);
        }
    }
    public void AIZtack(){
        if (AIHand.points() < playerHand.points()) {
            result.setText("You got Ztacked...");
            playerPoints += playerHand.points();
            AIPoints += AIHand.points();
        } else if (AIHand.points() >= playerHand.points()) {
            result.setText("You Ztacked him!");
            playerPoints += playerHand.points();
            AIPoints += AIHand.points() + 27;
        }
        playerPoint.setText(Integer.toString(playerPoints));
        AIPoint.setText(Integer.toString(AIPoints));
        disableButtons();
        endGame.setEnabled(false);
        startButton.setEnabled(true);
        pileButton.setEnabled(false);
        deckButton.setEnabled(false);
        newGameButton.setEnabled(false);
        viewAIHandButton.setEnabled(true);
        warningLabel.setText("");
        winCheck();
    }
    public void AIPlay(){
        
        System.out.println(AIHand.points());
        if(AIHand.points() <= 6 && playerHand.cardsInHand.length > 3){
            AIZtack();
        }
        else if(AIHand.points() <= 4 && playerHand.cardsInHand.length > 2){
            AIZtack();
        }
        else if(AIHand.points() <= 3 && playerHand.cardsInHand.length > 1){
            AIZtack();
        }
        else{
            int[] cardValues = new int[6], pairs = new int[6], playValue = new int[6];
            Stack<String> cardsToPlay = new Stack();

            for(int i = 0; i < 6; i++){
                cardValues[i] = Integer.parseInt(AIHand.cardsInHand[i].split(" ")[0]);
                if(cardValues[i] == 14){
                    cardValues[i] = -100;
                }
            }

            for(int i = 0; i < 6; i++){
                pairs[i] = 1;
                int j = i;
                while((j <= 4) && cardValues[j] == cardValues[j+1]){
                    pairs[i]++;
                    if(j < 4){
                        j++;
                    }
                    else{
                        break;
                    }
                }
            }

            int maxValue = 0, locationStart = 0;

            for(int i = 0; i < 6; i++){
                playValue[i] = ((pairs[i]+2)*(cardValues[i]+7));
                if(maxValue < playValue[i]){
                    maxValue = playValue[i];
                    locationStart = i;
                }
            }

            for(int i = 0; i < pairs[locationStart]; i++){
                cardsToPlay.push(AIHand.cardsInHand[i+locationStart]);
            }

            String pileCard = pile.topCard;
            int pileCardValue = Integer.parseInt(pileCard.split(" ")[0]);

            System.out.println(cardsToPlay);
            try{
                if(Arrays.asList(cardValues).indexOf(pileCardValue) == -1){
                    throw new Exception();
                }
                System.out.print("Duplicate FOUND!");
                if(Integer.parseInt(AIHand.cardsInHand[locationStart].split(" ")[0]) == pileCardValue){
                    cardsToPlay = new Stack();
                    if(locationStart >=1){
                        cardsToPlay.push(AIHand.cardsInHand[locationStart - 1]);
                    }
                    else if (cardValues[pairs[locationStart] + locationStart - 1] != 0){
                        cardsToPlay.push(AIHand.cardsInHand[pairs[locationStart] + locationStart - 1]);
                    }
                    else{
                        for (int i = 0; i < pairs[locationStart]; i++) {
                            cardsToPlay.push(AIHand.cardsInHand[i + locationStart]);
                        }
                    }
                }
                AIHand.play(cardsToPlay, AIHand, pile, true, deck);
            }
            catch(Exception e){
                if(pileCardValue <= Math.round(AIHand.points()/5.0) && pileCardValue < 7){
                    AIHand.play(cardsToPlay, AIHand, pile, true, deck);
                }
                else{
                    AIHand.play(cardsToPlay, AIHand, pile, false, deck);
                    AIHand.draw(deck);
                }
            }

            displayHand(AIHand, true);
            System.out.println(Arrays.toString(AIHand.cardsInHand));
        }
        
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
        pileLabel = new javax.swing.JLabel();
        pileButton = new javax.swing.JToggleButton();
        deckButton = new javax.swing.JToggleButton();
        playButton = new javax.swing.JButton();
        warningLabel = new javax.swing.JLabel();
        drawButton = new javax.swing.JButton();
        totalLabel = new javax.swing.JLabel();
        playerNameLabel = new javax.swing.JLabel();
        AINameLabel = new javax.swing.JLabel();
        playerPoint = new javax.swing.JLabel();
        AIPoint = new javax.swing.JLabel();
        result = new javax.swing.JLabel();
        newGameButton = new javax.swing.JButton();
        ZTackButton = new javax.swing.JButton();
        viewAIHandButton = new javax.swing.JButton();

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

        points.setText("Hand Points: 0");

        endGame.setText("End Game");
        endGame.setEnabled(false);
        endGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endGameActionPerformed(evt);
            }
        });

        startButton.setText("Start Round");
        startButton.setEnabled(false);
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        pileLabel.setText("Pile");

        pileButton.setEnabled(false);
        pileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pileButtonActionPerformed(evt);
            }
        });

        deckButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cardBack_full.png"))); // NOI18N
        deckButton.setEnabled(false);
        deckButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deckButtonActionPerformed(evt);
            }
        });

        playButton.setText("Play");
        playButton.setEnabled(false);
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playButtonActionPerformed(evt);
            }
        });

        drawButton.setText("Draw");
        drawButton.setEnabled(false);
        drawButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drawButtonActionPerformed(evt);
            }
        });

        totalLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        totalLabel.setText("Total");

        playerNameLabel.setText("Player");

        AINameLabel.setText("AI");

        playerPoint.setText("0");

        AIPoint.setText("0");

        result.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        result.setForeground(new java.awt.Color(150, 50, 50));
        result.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        newGameButton.setText("New Game");
        newGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGameButtonActionPerformed(evt);
            }
        });

        ZTackButton.setText("ZTack!");
        ZTackButton.setEnabled(false);
        ZTackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ZTackButtonActionPerformed(evt);
            }
        });

        viewAIHandButton.setText("View  AI  Hand");
        viewAIHandButton.setEnabled(false);
        viewAIHandButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewAIHandButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cardOne1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cardOne2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cardOne3, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pileLabel)
                                .addGap(128, 128, 128)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(drawButton)
                                .addGap(171, 171, 171)
                                .addComponent(viewAIHandButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ZTackButton))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cardOne4, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cardOne5, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cardOne6, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(248, 248, 248)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cardTwo1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cardTwo2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cardTwo3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cardTwo4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cardTwo5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(result, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(playButton)
                                .addGap(18, 18, 18)
                                .addComponent(deckButton, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cardTwo6, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 215, Short.MAX_VALUE))
                            .addComponent(warningLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(startButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(endGame))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(playerPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(playerNameLabel)
                                            .addGap(59, 59, 59))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(totalLabel)
                                            .addGap(17, 17, 17)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(AINameLabel)
                                        .addComponent(AIPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(points, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(newGameButton)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cardTwo6, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cardTwo1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cardTwo2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cardTwo3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cardTwo4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cardTwo5, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addComponent(result, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(94, 94, 94)
                                        .addComponent(playButton))
                                    .addComponent(deckButton, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(warningLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(136, 136, 136)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(ZTackButton)
                                .addComponent(viewAIHandButton))
                            .addComponent(drawButton)
                            .addComponent(pileLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cardOne3, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cardOne4, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cardOne2, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cardOne1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cardOne6, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cardOne5, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(445, 445, 445)
                        .addComponent(totalLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(playerNameLabel)
                            .addComponent(AINameLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AIPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(playerPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(57, 57, 57)
                        .addComponent(points)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(endGame)
                            .addComponent(startButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(newGameButton)
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
        playerPoints += playerHand.points();
        AIPoints += AIHand.points();
        playerPoint.setText(Integer.toString(playerPoints));
        AIPoint.setText(Integer.toString(AIPoints));
        if(AIPoints < playerPoints){
            result.setText("You lose...");
        }
        else if(AIPoints > playerPoints){
            result.setText("You win!");
        }
        else{
            result.setText("You drew.");
        }
        disableButtons();
        endGame.setEnabled(false);
        pileButton.setEnabled(false);
        deckButton.setEnabled(false);
        newGameButton.setEnabled(true);
        
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
        deck = shuffleDeck();
        pile = new Pile(deck);
        AIHand = new Hand();
        playerHand = new Hand();
        for(int i = 0; i < 6; i++){
            AIHand.draw(deck);
            playerHand.draw(deck);
        }
        
        
        displayHand(AIHand, true);
        displayHand(playerHand, false);
        displayPile(pile);
        resetButtons();
        resetState();
        enableButtons();
        points.setText("Hand Points: " + playerHand.points());

        
        startButton.setEnabled(false);
        result.setText("");
        warningLabel.setText("");
    }//GEN-LAST:event_startButtonActionPerformed

    private void cardOne6StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cardOne6StateChanged
        
    }//GEN-LAST:event_cardOne6StateChanged

    private void pileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pileButtonActionPerformed
        playerHand.play(selected, playerHand, pile, true, deck);
        displayHand(playerHand, false);
        enableButtons();
        AIPlay();
        displayPile(pile);
        points.setText("Hand Points: " + playerHand.points());
    }//GEN-LAST:event_pileButtonActionPerformed

    private void deckButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deckButtonActionPerformed
        if(playState = true){
            playerHand.play(selected, playerHand, pile, false, deck);
            displayPile(pile);
        }
        playerHand.draw(deck);
        displayHand(playerHand, false);
        enableButtons();
        AIPlay();
        displayPile(pile);
        points.setText("Hand Points: " + playerHand.points());
    }//GEN-LAST:event_deckButtonActionPerformed

    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playButtonActionPerformed
        Boolean validPlay = checkPlay();
        if(validPlay == true){
            disableButtons();
            playState = true;
        }
        playState = false;
    }//GEN-LAST:event_playButtonActionPerformed

    private void drawButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drawButtonActionPerformed
        if("14".equals(playerHand.cardsInHand[5].split(" ")[0])){
            deckButton.setEnabled(true);
            disableButtons();
            pileButton.setEnabled(false);
            warningLabel.setText("Draw a card.");
        }
        else{
            warningLabel.setText("You cannot draw over your maximum hand size.");
        }
    }//GEN-LAST:event_drawButtonActionPerformed

    private void newGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGameButtonActionPerformed
        playerPoints = 0;
        AIPoints = 0;
        selected = new Stack();
        playState = false;
        resetState();
        cardOne1.setIcon(null);
        cardOne2.setIcon(null);
        cardOne3.setIcon(null);
        cardOne4.setIcon(null);
        cardOne5.setIcon(null);
        cardOne6.setIcon(null);
        cardTwo1.setIcon(null);
        cardTwo2.setIcon(null);
        cardTwo3.setIcon(null);
        cardTwo4.setIcon(null);
        cardTwo5.setIcon(null);
        cardTwo6.setIcon(null);
        pileButton.setIcon(null);
        
        startButton.setEnabled(true);
        newGameButton.setEnabled(false);
        result.setText("");
        playerPoint.setText("0");
        AIPoint.setText("0");
        points.setText("Hand Points: 0");
        warningLabel.setText("");
        
    }//GEN-LAST:event_newGameButtonActionPerformed

    private void ZTackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ZTackButtonActionPerformed
        if(playerHand.points() <=6 ){
            if (AIHand.points() <= playerHand.points()) {
                result.setText("You got Ztacked...");
                playerPoints += playerHand.points() + 27;
                AIPoints += AIHand.points();
            } else if (AIHand.points() > playerHand.points()) {
                result.setText("You Ztacked him!");
                playerPoints += playerHand.points();
                AIPoints += AIHand.points();
            }
            playerPoint.setText(Integer.toString(playerPoints));
            AIPoint.setText(Integer.toString(AIPoints));
        }
        else{
            warningLabel.setText("You can only ZTack when your hand point is below 6.");
        }
        
        disableButtons();
        endGame.setEnabled(false);
        startButton.setEnabled(true);
        pileButton.setEnabled(false);
        deckButton.setEnabled(false);
        newGameButton.setEnabled(false);
        viewAIHandButton.setEnabled(true);
        warningLabel.setText("");

        winCheck();
    }//GEN-LAST:event_ZTackButtonActionPerformed

    private void viewAIHandButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewAIHandButtonActionPerformed
        HandState++;
        if(HandState%2 == 1){
            displayHand(AIHand, false);
            viewAIHandButton.setText("View Your Hand");
        }
        else{
            displayHand(playerHand, false);
            viewAIHandButton.setText("View  AI  Hand");
        }
    }//GEN-LAST:event_viewAIHandButtonActionPerformed
            
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
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AINameLabel;
    private javax.swing.JLabel AIPoint;
    private javax.swing.JButton ZTackButton;
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
    private javax.swing.JToggleButton deckButton;
    private javax.swing.JButton drawButton;
    private javax.swing.JButton endGame;
    private javax.swing.JButton newGameButton;
    private javax.swing.JToggleButton pileButton;
    private javax.swing.JLabel pileLabel;
    private javax.swing.JButton playButton;
    private javax.swing.JLabel playerNameLabel;
    private javax.swing.JLabel playerPoint;
    private javax.swing.JLabel points;
    private javax.swing.JLabel result;
    private javax.swing.JButton startButton;
    private javax.swing.JLabel totalLabel;
    private javax.swing.JButton viewAIHandButton;
    private javax.swing.JLabel warningLabel;
    // End of variables declaration//GEN-END:variables
}
