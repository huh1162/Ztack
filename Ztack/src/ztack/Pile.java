/*
 * Class that controls functions of the pile such as setting the cards, resetting the cards
 * and paying the players or AI's card(s)
 */
package ztack;
import java.util.Arrays;
import java.util.Stack;

/**
 * @author Sangar
 * @author Margaret
 * @author henry
 */
public class Pile {
    
    protected String topCard; // the top card of the pile (which is the one that can be drawn)
    
    protected Stack<String> pileCards = new Stack(); // the pile of cards
    
    protected Stack<String> stackCards = new Stack(); // cards being played but not on the pile yet (to allow for drawing cards)
    
    /**
     * initializes the pile at the beginning of the game
     * @param deck the deck to draw cards from
     */
    public Pile(Stack<String> deck){
        topCard = deck.pop();
        pileCards.push(topCard);
    }
    
    /**
     * handles the pile while the card is being played and after
     * @param card the card being played onto the pile
     * @param draw whether to draw a card or not
     */
    public void play(String card, Boolean draw){
        topCard = card;
        if(draw == true){
            stackCards.push(topCard);
        }
        else{
            pileCards.push(topCard);
        }
    }
    
    /**
     * Drawing a card from the pile
     * @param player the hand that is drawing a card from the pile
     */
    public void draw(Hand player){
        player.get(pileCards.pop());
        stackCards.pop();
        for(int i = 0; i < stackCards.size(); i++){
            pileCards.push(stackCards.pop());        
        }
        pileCards.push(topCard);
    }
}
