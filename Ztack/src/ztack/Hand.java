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
public class Hand {
    protected String[] cardsInHand = new String[7];
    
    public Hand(){
        for(int i = 0; i < 6; i++){
            cardsInHand[i] = "";
        }
    }
    
    public Hand(String[] cardsList){
        System.arraycopy(cardsList, 0, cardsInHand, 0, cardsList.length);
    }
    
    public void draw(Stack<String> deck){ // Drawing from the deck
        this.cardsInHand[Arrays.asList(this.cardsInHand).indexOf("")] = deck.pop();
        Arrays.sort(cardsInHand);
    }
    
    public void get(String card){
        this.cardsInHand[Arrays.asList(this.cardsInHand).indexOf("")] = card;
        Arrays.sort(cardsInHand);
    }
    
    public void sort(){
        Arrays.sort(cardsInHand);
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
            this.cardsInHand[Arrays.asList(this.cardsInHand).indexOf(card)] = "";
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
