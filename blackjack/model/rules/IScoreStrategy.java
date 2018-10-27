package BlackJack.model.rules;

import BlackJack.model.Player;

public interface IScoreStrategy {
    boolean returnWinner (Player a_dealer, Player a_player);
}
