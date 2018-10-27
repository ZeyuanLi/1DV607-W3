package BlackJack.model;

import BlackJack.model.rules.*;

import java.util.Iterator;
import java.util.List;

public class Dealer extends Player {

  private Deck m_deck;
  private INewGameStrategy m_newGameRule;
  private IHitStrategy m_hitRule;

  public Dealer(RulesFactory a_rulesFactory) {
  
    m_newGameRule = a_rulesFactory.GetNewGameRule();
    m_hitRule = a_rulesFactory.GetHitRule();
    
    /*for(Card c : m_deck.GetCards()) {
      c.Show(true);
      System.out.println("" + c.GetValue() + " of " + c.GetColor());
    }    */
  }

  public boolean NewGame(Player a_player) {
    if (m_deck == null || IsGameOver()) {
      m_deck = new Deck();
      ClearHand();
      a_player.ClearHand();
      return m_newGameRule.NewGame(m_deck, this, a_player);
    }
    return false;
  }

  public boolean Hit(Player a_player) {
    if (m_deck != null && a_player.CalcScore() < g_maxScore && !IsGameOver()) {
      dealNewCard(a_player, true);
      return true;
    }
    return false;
  }

  public boolean IsDealerWinner(Player a_player) {
    return new PlayerWinsStrategy().returnWinner(this, a_player);
  }

  public boolean IsGameOver() {
    if (m_deck != null && m_hitRule.DoHit(this) != true) {
        return true;
    }
    return false;
  }

  public void Stand(){
    if(m_deck != null){
      this.ShowHand();

      while(m_hitRule.DoHit(this)){
        dealNewCard(this, true);
      }
    }
  }

  // My implementation to solve task
  public void dealNewCard(Player a_player, boolean boo)
  {
    Card c = m_deck.GetCard();
    c.Show(boo);
    a_player.DealCard(c);
  }

}