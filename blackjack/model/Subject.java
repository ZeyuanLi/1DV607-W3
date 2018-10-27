package BlackJack.model;
import BlackJack.controller.PlayGame;

// My implementation of pattern
public interface Subject {
    public void register (PlayGame game);
    public void unregister (PlayGame game);
    public void notifyObserver();
}
