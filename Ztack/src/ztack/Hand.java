/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ztack;
import java.util.*;


/**
 *
 * @author henry
 */
public class Hand {

    /**
     *
     */
    protected String[] cardsInHand = new String[6];
    
    public Hand(){
        for(int i = 0; i < 6; i++){
            cardsInHand[i] = "14 X";
        }
    }
    
    public Hand(String[] cardsList){
        System.arraycopy(cardsList, 0, cardsInHand, 0, cardsList.length);
    }
    
    public String[] sort(String[] cardsInHand){
        int[][] sortingArray = new int[6][2];
        for(int i = 0; i < 6; i++){
            sortingArray[i][0] = Integer.parseInt(cardsInHand[i].split(" ")[0]);
            sortingArray[i][1] = Character.getNumericValue(cardsInHand[i].split(" ")[1].charAt(0)) + 55 ;
        }
        Arrays.sort(sortingArray, Comparator.comparingDouble(a -> a[0]));
        for(int i = 0; i < 6; i++){
            cardsInHand[i] = sortingArray[i][0] + " " + Character.toString((char) sortingArray[i][1]);
        }
        return cardsInHand;
    }    
    
    public void draw(Stack<String> deck){ // Drawing from the deck
        this.cardsInHand[Arrays.asList(this.cardsInHand).indexOf("14 X")] = deck.pop();
        cardsInHand = sort(cardsInHand);
    }
    
    public void get(String card){
        cardsInHand[Arrays.asList(cardsInHand).indexOf("14 X")] = card;
        cardsInHand = sort(cardsInHand);
    }
    
    public int points(){
        int pointValue = 0;
        for(int i = 0; i < cardsInHand.length; i++){
            int cardNumber = Integer.parseInt(cardsInHand[i].split(" ")[0]);
            switch (cardNumber) {
                case 11:
                    pointValue+=10;
                    break;
                case 12:
                    pointValue+=10;
                    break;
                case 13:
                    pointValue+=10;
                    break;
                case 14:
                    break;
                default:
                    pointValue+=cardNumber;
                    break;
            }
        }
        return pointValue;
    }
    
    /**
     *
     * @param cardsPlay
     * @param player
     * @param pile
     * @param drawFromPile
     * @param deck
     */
    public void play(Stack<String> cardsPlay, Hand player, Pile pile, Boolean drawFromPile, Stack<String> deck){
        int cardsPlaySize = cardsPlay.size();
        for(int i = 0; i < cardsPlaySize;i++){
            String card = cardsPlay.pop();
            cardsInHand[Arrays.asList(cardsInHand).indexOf(card)] = "14 X";
            pile.play(card, drawFromPile);
        }
        if(drawFromPile == true){
            pile.draw(player);
        }
/*        else{
            player.draw(deck);
        }*/
    }
    
    
}
