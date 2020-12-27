package consolelog.accademy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class MessageGeneratorImpl implements MessageGenerator {
    private final static Logger log = LoggerFactory.getLogger(MessageGeneratorImpl.class);
    @Autowired
    private Game game;

    @Autowired
    @GuessCount
    private int guessCount;

    @PostConstruct
    private void init() {
        log.info("init method called={}", game);
    }

    @Override
    public String getMainMessage() {
        return "Number is in between "
                + game.getSmallest() +
                " and "
                + game.getBiggest() +
                ". Can you guess it?" ;
    }

    @Override
    public String getResultMassage() {
        if (game.isGameWon()) {
            return "You have won the game and the number is " + game.getNumber();
        } else if (game.isGameLost()) {
            return "You have lost the game and the number was " + game.getNumber();
        } else if (!game.isValidNumber()) {
            return "Invalid Number !";
        } else if (game.getRemainingGuess() == guessCount) {
            return "What is your first guess?";
        } else {
            String direction = "Lower";
            if (game.getGuess() < game.getNumber()) {
                direction = "Higher";
            }
            return direction + "! You have " + game.getRemainingGuess() + " left.";
        }
    }
}
