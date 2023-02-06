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
            for(int collumn = 0; collumn < 3; collumn++)
            {
                getWorld().addObject(deck.getTopCard(),70 + collumn * 130, 50 + row * 80);
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
        if(triplesRemaining == 0)
        {
            Greenfoot.stop();
        }
    }
    
    public void checkIfTriples(ArrayList<Card> cardsOnBoard, Card[] cardsSelected, ArrayList<Integer> selectedCardsIndex)
    {
        int shapes = cardsSelected[0].getShape().ordinal() + 
            cardsSelected[1].getShape().ordinal() + 
            cardsSelected[2].getShape().ordinal();
        
        int numberOfShapes = cardsSelected[0].getNumberOfShapes() + 
            cardsSelected[1].getNumberOfShapes() + 
            cardsSelected[2].getNumberOfShapes();
        
        int shading = cardsSelected[0].getShading() +
            cardsSelected[1].getShading() +
            cardsSelected[2].getShading();
            
        int colors = cardsSelected[0].getColor().ordinal() +
            cardsSelected[1].getColor().ordinal() +
            cardsSelected[2].getColor().ordinal();
            
        if(shapes % 3 == 0 && numberOfShapes % 3 == 0 && shading % 3 == 0 && colors % 3 == 0)
        {
            removeAndReplaceTriples(cardsOnBoard, cardsSelected, selectedCardsIndex);
        }
    }
    
    public void removeAndReplaceTriples(ArrayList<Card> cardsOnBoard, Card[] cardsSelected, ArrayList<Integer> selectedCardsIndex)
    {
        int [][] cardsXYCoordinates = new int[3][2];
        for(int card = 0; card < 3; card++)
        {
            cardsXYCoordinates[card][0] = cardsSelected[card].getX();
            cardsXYCoordinates[card][1] = cardsSelected[card].getY();
        }
        
        Animations.slideAndTurn(cardsSelected);
        
        for(int card = 0; card < 3; card++)
        {
            getWorld().removeObject(cardsSelected[card]);
            if(deck.getNumCardsInDeck() > 0)
            {
                cardsOnBoard.set(selectedCardsIndex.get(card), deck.getTopCard());
                getWorld().addObject(cardsOnBoard.get(selectedCardsIndex.get(card)), 
                cardsXYCoordinates[card][0], cardsXYCoordinates[card][1]);
            }
        }
        
        //UI
        triplesRemaining--;
        Scorekeeper.updateScore();
        setUI();
        checkIfEndGame();
    }
}
