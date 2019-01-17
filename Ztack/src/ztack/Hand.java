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
        this.cardsInHand[Arrays.asList(this.cardsInHand).indexOf("14 X")] = card;
        cardsInHand = sort(cardsInHand);
    }
    
    
    /**
     *
     * @param cardsPlay
     * @param player
     * @param pile
     */
    public void play(Stack<String> cardsPlay, Hand player, Pile pile, Boolean drawFromPile, Stack<String> deck){
        for(int i = 0; i < cardsPlay.size();i++){
            String card = cardsPlay.pop();
            this.cardsInHand[Arrays.asList(this.cardsInHand).indexOf(card)] = "14 X";
            pile.play(card, drawFromPile);
        }
        if(drawFromPile == true){
            pile.draw(player);
        }
        else{
            player.draw(deck);
        }
    }
    
    
}
