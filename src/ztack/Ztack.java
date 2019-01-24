/*
 * This will be a simple but fun card game where the idea is to be the first
 * player to have 6 or less points in their hand. It is only a one player game
 * but also uses a computer player.
 */
package ztack;
import java.util.*;
import javax.swing.*;
import java.io.FileReader;
import java.awt.Color;
/**
 * @author Sangar
 * @author Margaret
 * @author Henry
 */
public class Ztack extends javax.swing.JFrame {

    /**
     * Creates new form Ztack
     */
    public Ztack() {
        initComponents();
        getContentPane().setBackground(new Color(0,153,0));
    }
    
    public static Stack<String> deck; // creates the deck
    public static Pile pile; // creates the pile
    public static Hand AIHand; // creates the hand for the AI
    public static Hand playerHand; // creates the hand for the player
    public static Stack<String> selected = new Stack(); // creates the stack of cards that are selected on the GUI
    public static Boolean playState = false; // indicates that the play is valid
    public static int playerPoints = 0; // the total number of points of the player across rounds
    public static int AIPoints = 0; // the total number of points of the AI across rounds
    
    public static int O1State = 0; // keeps track of the state to help switch between grey and white version of icons
    public static int O2State = 0; // to help show which cards or states are clicked/on
    public static int O3State = 0;
    public static int O4State = 0;
    public static int O5State = 0;
    public static int O6State = 0;
    public static int PileState = 0;
    public static int DeckState = 0;
    public static int HandState = 0;
    public static int InsState = 0;
    public static int RoundCounter = 0;
    
    /**
     * A method that shuffles all the cards
     * @return a shuffled deck of standard cards
     */
    public static Stack shuffleDeck(){
        String[] sortedDeck = new String[54];
        Stack<String> deck = new Stack();
        
        for(int i = 0; i < 13; i++){ // initializes the sorted deck
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
        
        sortedDeck[52] = "0 B";
        sortedDeck[53] = "0 R";
        
        int random;
        for(int i = 0; i < 54; i++){ // creates the randomized stack of cards
            random = (int) (Math.random() * 54);
            while ("0".equals(sortedDeck[random])){
                random++;
                random = random%54;
            }
            
            deck.push(sortedDeck[random]);
            sortedDeck[random] = "0";
        }
        
        return deck;
    } // shuffles and returns the deck

    /**
     * Method that gets the name of the image for cards and checks if the cards 
     * are the AI's and if they are greyed out
     * @param card the card needing a png
     * @param AI whether the player or AI owns the card
     * @param grey whether the card is greyed (clicked)
     * @return white or greyed out cards
     */
    public static String cardPNG(String card, Boolean AI, Boolean grey){
        
        if("14 X".equals(card)){ // "14 X" is the symbol for blank space
            return "blank.png";
        }
        
        String output = "";
        
        if(AI == true){ // the AI's cards should not be seen by the player
            output = "cardBack";
        }
        else{ // if it is the player's cards
            String[] temp = card.split(" ");
            int number = Integer.parseInt(temp[0]);
            char suit = temp[1].charAt(0);
        
            switch (number) {
                case 0:
                    output+="joker";
                    break;
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
                case 'R':
                    output+="red";
                    break;
                case 'B':
                    output+="black";
                    break;
                default:
                    break;
            }
        }
        if(grey == true){ // checks if it is a greyed card
            output += "_grey";
        }
        output+=".png";
        
        return output;
    }

    /**
     * Setting the hand and images for each player
     * @param hand the hand being displayed
     * @param AI whether it is an AI hand
     */
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

    /**
     * resets most buttons to the original state
     */
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
        viewAIHandButton.setVisible(false);
    }

    /**
     * resets the states that keep track of variables
     */
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

    /**
     * enables the buttons that are used when selecting cards, etc.
     */
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
        ZTackButton.setEnabled(true);

        
        viewAIHandButton.setEnabled(false);
        endGame.setEnabled(true);
    }

    /**
     * disables the buttons and enables those that are needed when drawing cards
     */
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
        ZTackButton.setEnabled(false);
        
        endGame.setEnabled(true);
    }

    /**
     * sets the image for the pile
     * @param pile the pile being displayed
     */
    public void displayPile(Pile pile){
        pileButton.setIcon(new ImageIcon("src/" + cardPNG(pile.topCard, false, false)));
        deckButton.setIcon(new ImageIcon("src/cardBack_full.png"));
    } // displays the pile
    
    /**
     * has the AI play if it is its turn to go first
     */
    public void goesFirst(){
        RoundCounter++;
        if(RoundCounter%2 == 0){
            AIPlay();
        }
        displayPile(pile);
    }

    /**
     * method that checks whether the card being played is valid
     * @return whether the play is a valid one or not
     */
    public Boolean checkPlay(){
        Stack<String> temporary = new Stack();
        int number = 14;
        String middleMan; // a middle storage to help make sure the stack stayed intact
        int selectedSize = selected.size();
        
        // makes sure a card is played
        if(selectedSize == 0){ 
            warningLabel.setText("You have to play a card or draw a card.");
            return false;
        }
        
        for(int i = 0; i < selectedSize; i++){
            middleMan = selected.pop();
            temporary.push(middleMan);
            if(i == 0){
                number = Integer.parseInt(middleMan.split(" ")[0]); // checks for blanks
                if(number == 14){
                    warningLabel.setText("You cannot play non-cards.");
                    return false;
                }
            }
            else{ // makes sure the cards played are the same value
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
        warningLabel.setText("Draw from the pile or the deck.");
        return true;
    }

    /**
     * checks if one side or the other won the game
     */
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

    /**
     * checks if player can ZTack
     */
    public void ZTackCheck(){
        if(playerHand.points() <= 6){
            ZTackButton.setEnabled(true);
        }
        else{
            ZTackButton.setEnabled(false);
        }
    }
    
    /**
     * ends the game if there are no more cards in the game
     */
    public void deckCheck(){
        if(deck.isEmpty()){
            playerPoints+=playerHand.points();
            AIPoints+=AIHand.points();
            disableButtons();
            endGame.setEnabled(false);
            startButton.setEnabled(true);
            pileButton.setEnabled(false);
            deckButton.setEnabled(false);
            newGameButton.setEnabled(false);
            viewAIHandButton.setEnabled(true);
            warningLabel.setText("The deck is empty.");
        }
    }
       
    /**
     * checks if player can draw
     */
    public void drawButtonCheck(){
        if("14".equals(playerHand.cardsInHand[5].split(" ")[0])){
            drawButton.setEnabled(true);
        }
    }
    
    /**
     * resolves the AI declaring ZTack (win condition) & keeps track of the score
     */
    public void AIZtack(){
        if (AIHand.points() < playerHand.points()) {
            result.setText("You got Ztacked...");
            playerPoints += playerHand.points();
        } else if (AIHand.points() >= playerHand.points()) {
            result.setText("You Ztacked him!");
            AIPoints += AIHand.points() + 27;
        }
        playerPoint.setText(Integer.toString(playerPoints));
        AIPoint.setText(Integer.toString(AIPoints));
        disableButtons(); // resets for the next round
        endGame.setEnabled(false);
        startButton.setEnabled(true);
        pileButton.setEnabled(false);
        deckButton.setEnabled(false);
        newGameButton.setEnabled(false);
        viewAIHandButton.setVisible(true);
        viewAIHandButton.setEnabled(true);   
        warningLabel.setText("");
        drawButton.setEnabled(false);
        winCheck();
    }

    /**
     * the code that runs the AI
     */
    public void AIPlay(){
        
        if(AIHand.points() <= 6 && playerHand.cardsInHand.length > 3){ // checks if the AI should declare ZTack
            AIZtack();
        }
        else if(AIHand.points() <= 4 && playerHand.cardsInHand.length > 2){
            AIZtack();
        }
        else if(AIHand.points() <= 3 && playerHand.cardsInHand.length > 1){
            AIZtack();
        }
        else{ // if it does not declare ZTack
            int[] cardValues = new int[6], pairs = new int[6], playValue = new int[6];
            Stack<String> cardsToPlay = new Stack();

            for(int i = 0; i < 6; i++){ // makes a list of the values of the cards
                cardValues[i] = Integer.parseInt(AIHand.cardsInHand[i].split(" ")[0]);
                if(cardValues[i] == 14){
                    cardValues[i] = -100;
                }
            }   

            // finds all the pairs and triples etc.
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

            // finds the ideal play through a value calculation
            for(int i = 0; i < 6; i++){ 
                playValue[i] = ((int) Math.pow((pairs[i]),3)*(cardValues[i]));
                if(maxValue < playValue[i]){
                    maxValue = playValue[i];
                    locationStart = i;
                }
            }

            // creates the cards to play
            for(int i = 0; i < pairs[locationStart]; i++){ 
                cardsToPlay.push(AIHand.cardsInHand[i+locationStart]);
            }

            String pileCard = pile.topCard;
            int pileCardValue = Integer.parseInt(pileCard.split(" ")[0]); // the value of the top card on the pile

            // checks if the card on the pile makes a pair with a card in the hand
            try{ 
                if(Arrays.asList(cardValues).indexOf(pileCardValue) == -1){
                    throw new Exception();
                }
                System.out.print("Duplicate FOUND!");
                if(Integer.parseInt(AIHand.cardsInHand[locationStart].split(" ")[0]) == pileCardValue){ // makes sure the duplicate isn't played
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
            // if there is not duplicate, consider whether to draw a random card or draw from the pile 
            catch(Exception e){ 
                if(pileCardValue <= Math.round(AIHand.points()/5.0) && pileCardValue < 7){
                    AIHand.play(cardsToPlay, AIHand, pile, true, deck);
                }
                else{
                    AIHand.play(cardsToPlay, AIHand, pile, false, deck);
                    AIHand.draw(deck);
                }
            }

            displayHand(AIHand, true); // displays the hand (the number of cards)
        }
        
        deckCheck();
        
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
        instructionsButton = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        instructionsLabel = new javax.swing.JTextArea();
        pileLabel1 = new javax.swing.JLabel();
        Title = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 0));
        setResizable(false);

        cardOne1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cardBack_full.png"))); // NOI18N
        cardOne1.setEnabled(false);
        cardOne1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardOne1ActionPerformed(evt);
            }
        });

        cardOne2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cardBack_full.png"))); // NOI18N
        cardOne2.setEnabled(false);
        cardOne2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardOne2ActionPerformed(evt);
            }
        });

        cardOne3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cardBack_full.png"))); // NOI18N
        cardOne3.setEnabled(false);
        cardOne3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardOne3ActionPerformed(evt);
            }
        });

        cardOne4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cardBack_full.png"))); // NOI18N
        cardOne4.setEnabled(false);
        cardOne4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardOne4ActionPerformed(evt);
            }
        });

        cardOne5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cardBack_full.png"))); // NOI18N
        cardOne5.setEnabled(false);
        cardOne5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardOne5ActionPerformed(evt);
            }
        });

        cardOne6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cardBack_full.png"))); // NOI18N
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

        cardTwo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cardBack.png"))); // NOI18N
        cardTwo1.setEnabled(false);
        cardTwo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardTwo1ActionPerformed(evt);
            }
        });

        cardTwo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cardBack.png"))); // NOI18N
        cardTwo2.setEnabled(false);
        cardTwo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardTwo2ActionPerformed(evt);
            }
        });

        cardTwo3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cardBack.png"))); // NOI18N
        cardTwo3.setEnabled(false);
        cardTwo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardTwo3ActionPerformed(evt);
            }
        });

        cardTwo4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cardBack.png"))); // NOI18N
        cardTwo4.setEnabled(false);
        cardTwo4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardTwo4ActionPerformed(evt);
            }
        });

        cardTwo5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cardBack.png"))); // NOI18N
        cardTwo5.setEnabled(false);
        cardTwo5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardTwo5ActionPerformed(evt);
            }
        });

        cardTwo6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cardBack.png"))); // NOI18N
        cardTwo6.setEnabled(false);
        cardTwo6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardTwo6ActionPerformed(evt);
            }
        });

        points.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        points.setText("Hand Points: 0");

        endGame.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        endGame.setText("End Game");
        endGame.setEnabled(false);
        endGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endGameActionPerformed(evt);
            }
        });

        startButton.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        startButton.setText("Start Round");
        startButton.setEnabled(false);
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        pileLabel.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        pileLabel.setText("Pile");

        pileButton.setBackground(new java.awt.Color(0, 0, 0));
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

        playButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        playButton.setText("Play");
        playButton.setEnabled(false);
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playButtonActionPerformed(evt);
            }
        });

        drawButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        drawButton.setText("Draw");
        drawButton.setEnabled(false);
        drawButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drawButtonActionPerformed(evt);
            }
        });

        totalLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        totalLabel.setText("Total");

        playerNameLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        playerNameLabel.setText("Player");

        AINameLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        AINameLabel.setText("AI");

        playerPoint.setText("0");

        AIPoint.setText("0");

        result.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        result.setForeground(new java.awt.Color(150, 50, 50));
        result.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        newGameButton.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        newGameButton.setText("New Game");
        newGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGameButtonActionPerformed(evt);
            }
        });

        ZTackButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ZTackButton.setText("ZTack!");
        ZTackButton.setEnabled(false);
        ZTackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ZTackButtonActionPerformed(evt);
            }
        });

        viewAIHandButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        viewAIHandButton.setText("View  AI  Hand");
        viewAIHandButton.setEnabled(false);
        viewAIHandButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewAIHandButtonActionPerformed(evt);
            }
        });

        instructionsButton.setText("View Instructions");
        instructionsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                instructionsButtonActionPerformed(evt);
            }
        });

        instructionsLabel.setEditable(false);
        instructionsLabel.setBackground(new java.awt.Color(240, 240, 240));
        instructionsLabel.setColumns(20);
        instructionsLabel.setLineWrap(true);
        instructionsLabel.setRows(5);
        instructionsLabel.setWrapStyleWord(true);
        instructionsLabel.setFocusable(false);
        jScrollPane1.setViewportView(instructionsLabel);

        pileLabel1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        pileLabel1.setText("Deck");

        Title.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        Title.setText("ZTack!");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(248, 248, 248)
                                .addComponent(cardTwo1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cardTwo2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cardTwo3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cardTwo4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cardTwo5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(pileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(playButton)
                                .addGap(22, 22, 22)
                                .addComponent(deckButton, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(warningLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cardTwo6, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(result, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(drawButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(381, 381, 381)
                        .addComponent(pileLabel)
                        .addGap(221, 221, 221)
                        .addComponent(pileLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(cardOne1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cardOne2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cardOne3, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cardOne4, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cardOne5, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cardOne6, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(viewAIHandButton)
                                .addGap(18, 18, 18)
                                .addComponent(ZTackButton)
                                .addGap(121, 121, 121)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 3, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(AIPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(AINameLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(startButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(endGame)))
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(playerNameLabel)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(instructionsButton)
                                        .addGap(107, 107, 107))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(newGameButton)
                                        .addGap(79, 79, 79)))
                                .addComponent(playerPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Title))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(totalLabel)
                                .addGap(121, 121, 121))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(points, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(78, 78, 78))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Title)
                        .addGap(22, 22, 22)
                        .addComponent(instructionsButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cardTwo6, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cardTwo1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cardTwo2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cardTwo3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cardTwo4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cardTwo5, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(101, 101, 101)
                                        .addComponent(playButton))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(result, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(32, 32, 32)
                                        .addComponent(drawButton)
                                        .addGap(31, 31, 31)
                                        .addComponent(warningLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(deckButton, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pileLabel)
                            .addComponent(pileLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(viewAIHandButton, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ZTackButton))
                        .addGap(8, 8, 8)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(cardOne6, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cardOne2, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cardOne3, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cardOne4, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cardOne5, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(80, 80, 80))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(cardOne1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(93, 93, 93)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(totalLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(playerNameLabel)
                            .addComponent(AINameLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(playerPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AIPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(points)
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(startButton)
                            .addComponent(endGame))
                        .addGap(18, 18, 18)
                        .addComponent(newGameButton)
                        .addGap(126, 126, 126))))
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

    /*
    * ends the game and checks if the AI or player won
    */
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

    // makes the icon change depending on which state the button is in
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

    // starts a new round
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
        drawButton.setEnabled(false);
        ZTackCheck();

        goesFirst();
    }//GEN-LAST:event_startButtonActionPerformed

    private void cardOne6StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cardOne6StateChanged
        
    }//GEN-LAST:event_cardOne6StateChanged

    /*
    * allows the player to draw a card
    */
    private void pileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pileButtonActionPerformed
        playerHand.play(selected, playerHand, pile, true, deck); 
        displayHand(playerHand, false);
        enableButtons();
        AIPlay();
        displayPile(pile);
        points.setText("Hand Points: " + playerHand.points());
        deckCheck();
        drawButtonCheck();
        ZTackCheck();
    }//GEN-LAST:event_pileButtonActionPerformed

    private void deckButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deckButtonActionPerformed
        if(playState = true){ // play a card...
            playerHand.play(selected, playerHand, pile, false, deck);
            displayPile(pile);
        }
        playerHand.draw(deck); //... and draw a card
        displayHand(playerHand, false);
        enableButtons();
        AIPlay();
        displayPile(pile);
        points.setText("Hand Points: " + playerHand.points());
        deckCheck();
        drawButtonCheck();
        ZTackCheck();
    }//GEN-LAST:event_deckButtonActionPerformed

    // plays the users selected card and gives the ability to draw a card
    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playButtonActionPerformed
        Boolean validPlay = checkPlay(); 
        if(validPlay == true){
            disableButtons();
            playState = true;
        }
        playState = false;
    }//GEN-LAST:event_playButtonActionPerformed

    // draw a card
    private void drawButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drawButtonActionPerformed
        if("14".equals(playerHand.cardsInHand[5].split(" ")[0])){ 
            deckButton.setEnabled(true);
            disableButtons();
            pileButton.setEnabled(false);
            warningLabel.setText("Draw a card.");
            drawButton.setEnabled(false);
        }
        else{
            warningLabel.setText("You cannot draw over your maximum hand size.");
        }
    }//GEN-LAST:event_drawButtonActionPerformed

    //resets everything and makes a new game
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
        RoundCounter = 0;
        
        startButton.setEnabled(true);
        newGameButton.setEnabled(false);
        result.setText("");
        playerPoint.setText("0");
        AIPoint.setText("0");
        points.setText("Hand Points: 0");
        warningLabel.setText("");
        
    }//GEN-LAST:event_newGameButtonActionPerformed

    // checks how the ZTack ends up
    private void ZTackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ZTackButtonActionPerformed
        if(playerHand.points() <=6 ){ 
            if (AIHand.points() <= playerHand.points()) {
                result.setText("You got Ztacked...");
                playerPoints += playerHand.points() + 27;
            } else if (AIHand.points() > playerHand.points()) {
                result.setText("You Ztacked him!");
                AIPoints += AIHand.points();   
            }
            playerPoint.setText(Integer.toString(playerPoints));
            AIPoint.setText(Integer.toString(AIPoints));
            
            disableButtons();
            endGame.setEnabled(false);
            startButton.setEnabled(true);
            pileButton.setEnabled(false);
            deckButton.setEnabled(false);
            newGameButton.setEnabled(false);
            viewAIHandButton.setVisible(true);
            viewAIHandButton.setEnabled(true);
            warningLabel.setText("");
            drawButton.setEnabled(false);
            
            winCheck();
        }
        else{
            warningLabel.setText("You can only ZTack when your hand point is below 6.");
        }
        
    }//GEN-LAST:event_ZTackButtonActionPerformed

    // when the round is over, you can see the hands
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

    // displays and hides the instructions
    private void instructionsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_instructionsButtonActionPerformed
        InsState++; 
        if(InsState%2 == 1){
            instructionsButton.setText("Hide Instructions");
            instructionsLabel.setVisible(true);
            try (FileReader reader = new FileReader("src/instructions.txt")) {
                instructionsLabel.read(reader, null);
            }
            catch(Exception e){
            }
        }
        else{
            instructionsButton.setText("View Instructions");
            instructionsLabel.setText("");
            instructionsLabel.setVisible(false);
        }
        
    }//GEN-LAST:event_instructionsButtonActionPerformed
            
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
    private javax.swing.JLabel Title;
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
    private javax.swing.JToggleButton instructionsButton;
    private javax.swing.JTextArea instructionsLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton newGameButton;
    private javax.swing.JToggleButton pileButton;
    private javax.swing.JLabel pileLabel;
    private javax.swing.JLabel pileLabel1;
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
