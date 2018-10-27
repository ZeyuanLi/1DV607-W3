package BlackJack.model;

import BlackJack.controller.PlayGame;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

public class Player implements Subject{

  public List<Card> m_hand;
  protected final int g_maxScore = 21;
    public static ArrayList<PlayGame> games = new ArrayList <PlayGame>(); // subscribers


    public Player()
  {
    m_hand = new LinkedList<Card>();
    System.out.println("Hello List World");
  }

  public void DealCard(Card a_addToHand) {
    m_hand.add(a_addToHand);
      notifyObserver();
  }
  
  public Iterable<Card> GetHand()
  {
    return m_hand;
  }
  
  public void ClearHand()
  {
    m_hand.clear();
  }
  
  public void ShowHand()
  {
    for(Card c : m_hand)
    {
      c.Show(true);
    }
  }
  
  public int CalcScore()
  {
    // the number of scores is dependant on the number of scorable values
    // as it seems there is no way to do this check at compile time in java ?!
    // cardScores[13] = {...};
    int cardScores[] = {
        2, 3, 4, 5, 6, 7, 8, 9, 10, 10 ,10 ,10, 11
    };
    assert (cardScores.length == Card.Value.Count.ordinal()) : "Card Scores array size does not match number of card values";
  
    
    int score = 0;

    for(Card c : GetHand()) {
        if (c.GetValue() != Card.Value.Hidden)
        {
            score += cardScores[c.GetValue().ordinal()];
        }
    }

    if (score > g_maxScore)
    {
        for(Card c : GetHand())
        {
            if (c.GetValue() == Card.Value.Ace && score > g_maxScore)
            {
                score -= 10;
            }
        }
    }

    return score;
  }

    // My implementation of pattern
    @Override
    public void register(PlayGame game) {
        games.add(game);
    }

    @Override
    public void unregister(PlayGame game) {
        int index = games.indexOf(game);
        games.remove(index);
    }

    @Override
    public void notifyObserver() {
        for (PlayGame game: games){
            game.update();
        }
    }
}
