package consolelog.accademy;

public interface Game {
    int getNumber();
    int getGuess();
    void setGuess(int guess);
    int getSmallest();
    int getBiggest();
    int getRemainingGuess();
    void reset();
    void check();
    Boolean isValidNumber();
    Boolean isGameWon();
    Boolean isGameLost();
}
