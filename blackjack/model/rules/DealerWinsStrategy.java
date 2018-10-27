package BlackJack.model.rules;

import BlackJack.model.Player;

public class DealerWinsStrategy implements IScoreStrategy {

    @Override
    public boolean returnWinner (Player a_dealer, Player a_player) {
        if (a_player.CalcScore() > 21) {
            return true;
        }

        if (a_dealer.CalcScore() > 21) {
            return false;
        }

        return a_dealer.CalcScore() >= a_player.CalcScore();
    }
}
