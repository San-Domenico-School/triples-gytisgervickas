 /**
 * Player class implementation.
 * 
 * @Gytis Gervickas 
 * @January 29, 2023 
 */
import greenfoot.*;
import java.util.ArrayList;

public class Player extends Actor 
{
    private Dealer dealer;
    private Card[] cardsSelected;
    private ArrayList<Card> cardsOnBoard = new ArrayList<Card>();
    private ArrayList<Integer> selectedCardsIndex = new ArrayList<Integer>();
    
    public Player(Dealer dealer) 
    {
        this.dealer = dealer;
        this.cardsSelected = new Card[3];
        this.cardsOnBoard = new ArrayList<Card>();
        this.selectedCardsIndex = new ArrayList<Integer>();
    }
    
    public void addedToWorld(World world) 
    {
        cardsOnBoard = (ArrayList) getWorld().getObjects(Card.class);
    }

    public void act() 
    {
        selectCards();
        
        if (threeCardsSelected()) 
        {
            dealer.checkIfTriples(cardsOnBoard, cardsSelected, selectedCardsIndex);
            resetCardsSelected();
        }
    }

    private void selectCards() 
    {
    {
           for(int i = 0; i < cardsOnBoard.size(); i++)
           {
                  if(Greenfoot.mouseClicked(cardsOnBoard.get(i)))
                 {
                         if(cardsOnBoard.get(i).getIsSelected())
                         {
                              cardsOnBoard.get(i).setIsSelected(false);
                              cardsOnBoard.get(i).setImage(cardsOnBoard.get(i).getCardImage());
                              selectedCardsIndex.remove(new Integer(i));
                         }
                         else
                         {
                               cardsOnBoard.get(i).setIsSelected(true);
                               cardsOnBoard.get(i).setImage(cardsOnBoard.get(i).getSelectedCardImage());
                               selectedCardsIndex.add(new Integer(i));
                         }
                  }
            }
    }
    }

    private void resetCardsSelected() 
    {
    {
          for(int i = 0; i < cardsOnBoard.size(); i++)
          {
                cardsOnBoard.get(i).setImage(cardsOnBoard.get(i).getCardImage());
                cardsOnBoard.get(i).setIsSelected(false);
          }
          selectedCardsIndex.clear();
    }    
    }

    private boolean threeCardsSelected() 
    {
        {
         if(selectedCardsIndex.size() == 3)
         {
               cardsSelected[0] = cardsOnBoard.get(selectedCardsIndex.get(0));
               cardsSelected[1] = cardsOnBoard.get(selectedCardsIndex.get(1)); 
               cardsSelected[2] = cardsOnBoard.get(selectedCardsIndex.get(2));
               return true;
         }
         else
         {
              return false;
         }
    }    
    }
}


