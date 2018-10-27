package BlackJack.controller;
import BlackJack.model.*;
import BlackJack.view.IView;

public class PlayGame implements Observer{

  private Game game;
  private IView view;

  public static int welcome = 0;

  public boolean Play(Game a_game, IView a_view) {
    game = a_game;
    view = a_view;

    if(welcome == 0) {
      view.DisplayWelcomeMessage();
      welcome = -1;
    }

    if (a_game.IsGameOver())
    {
        a_view.DisplayGameOver(a_game.IsDealerWinner());
    }

    IView.Event input = a_view.GetInput();
    
    if (input == IView.Event.NEW)
    {
        a_game.NewGame();
    }
    else if (input == IView.Event.HIT)
    {
        a_game.Hit();
    }
    else if (input == IView.Event.STAND)
    {
        a_game.Stand();
    }

    return input != IView.Event.QUIT;
  }
  @Override
  public void update() {
    view.DisplayWelcomeMessage();
    view.DisplayDealerHand(game.GetDealerHand(), game.GetDealerScore());
    view.DisplayPlayerHand(game.GetPlayerHand(), game.GetPlayerScore());
    try {
      Thread.sleep(2000); // pause in miliseconds
    }catch(Exception e){
      System.out.println("Timer interrupted!");
      Thread.currentThread().interrupt();
    }
  }
}