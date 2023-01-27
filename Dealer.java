import greenfoot.*;

/**
 * Dealer class description
 * 
 * @Gytis Gervickas 
 * @Jan 27, 2023
 */

import java.util.ArrayList;

public class Dealer extends Actor
{
    private Deck deck;
    private int triplesRemaining;
    
    public Dealer(int numCardsInDeck)
    {
        deck = new Deck(numCardsInDeck);
        triplesRemaining = numCardsInDeck / 3;
        Scorekeeper.setDeckSize(numCardsInDeck);
    }
    
    public void addedToWorld(World world)
    {
        dealBoard();
    }
    
    public void dealBoard()
    {
        for(int row = 0; row < 5; row++)
        {
            for(col = 0; col < 3; col++)
            {
                getWorld().addObject(deck.topCard(), 80 + 120 * col, 80 + 80 * row);
            }
        }
    }
    
    private void setUI()
    {
        String cardsRemainingText = new Integer(triplesRemaining * 3).toString();
        String scoreText = new Integer(Scorekeeper.getScore()).toString();
        getWorld().showText(cardsRemainingText, 310, 470);
        getWorld().showText(scoreText, 310, 504);  
    
    }
    
    public void checkIfEndGame()
    {
        
    }
    
    public void checkIfTriples(ArrayList<Card> cardsOnBoard, Card[] cardsSelected, ArrayList<Integer> selectedCardsIndex)
    {
        
    }
    
    public void removeAndReplaceTriples(ArrayList<Card> cardsOnBoard, Card[] cardsSelected, ArrayList<Integer> selectedCardsIndex)
    {
        
    }
}
