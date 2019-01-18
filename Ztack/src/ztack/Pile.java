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
public class Pile {
    
    protected String topCard;
    
    protected Stack<String> pileCards = new Stack();
    
    protected Stack<String> stackCards = new Stack(); // Cards being played but not on the pile yet
    
    public Pile(Stack<String> deck){
        topCard = deck.pop();
        pileCards.push(topCard);
    }
    
    public void play(String card, Boolean draw){
        topCard = card;
        if(draw == true){
            stackCards.push(topCard);
        }
        else{
            pileCards.push(topCard);
        }
    }
    
    public void get(String card) {
        topCard = card;
        pileCards.push(card);
    }
    
    public void draw(Hand player){
        player.get(pileCards.pop());
        stackCards.pop();
        for(int i = 0; i < stackCards.size(); i++){
            pileCards.push(stackCards.pop());        
        }
        pileCards.push(topCard);
    }
}
