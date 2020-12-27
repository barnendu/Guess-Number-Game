package consolelog.accademy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


public class GameImpl implements Game {
    //===constant===//
    private final static Logger log = LoggerFactory.getLogger(GameImpl.class);
    //=== field decleration ===//
    @Autowired
    private NumberGenerator numberGenerator;
    private int number;

    @Autowired
    @GuessCount
    private int guessCount;

    private int guess;
    private int smallest;
    private int biggest;
    private int remainingGuess;
    private Boolean validNumberRange = true;
    //=== constructor based DI ===//
//    @Autowired
//    public GameImpl(NumberGenerator numberGenerator) {
//        this.numberGenerator = numberGenerator;
//    }
    //=== setter based DI ===//
//
//    public void setNumberGenerator(NumberGenerator numberGenerator) {
//        this.numberGenerator = numberGenerator;
//    }

    //=== public methods==//
    @PostConstruct
    @Override
    public void reset() {
        guess = 0;
        smallest = 0;
        biggest = numberGenerator.getMaxNumber();
        remainingGuess = guessCount;
        number = numberGenerator.next();
        log.info("the next nuber={}", number);
    }

    @PreDestroy
    public void preDestroy() {
        log.info("Pre destroy method called..");
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public int getGuess() {
        return guess;
    }

    @Override
    public void setGuess(int guess) {
        this.guess = guess;

    }

    @Override
    public int getSmallest() {
        return smallest;
    }

    @Override
    public int getBiggest() {
        return biggest;
    }

    @Override
    public int getRemainingGuess() {
        return remainingGuess;
    }


    @Override
    public void check() {
        checkValidNumberRange();
        if (validNumberRange) {
            if (guess > number) {
                biggest = guess - 1;
            }
            if (guess < number) {
                smallest = guess + 1;
            }
        }
        remainingGuess--;
    }

    @Override
    public Boolean isValidNumber() {
        return validNumberRange;
    }

    @Override
    public Boolean isGameWon() {
        return number == guess;
    }

    @Override
    public Boolean isGameLost() {
        return !isGameWon() && remainingGuess <= 0;
    }

    //private methods ===//
    private void checkValidNumberRange() {
        validNumberRange = (guess >= smallest) && (guess <= biggest);
    }
}
