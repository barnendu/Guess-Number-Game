package consolelog.accademy.programming;

import consolelog.accademy.Game;
import consolelog.accademy.MessageGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleGuess {
    private final static Logger log = LoggerFactory.getLogger(ConsoleGuess.class);
    @Autowired
    private MessageGenerator messageGenerator;
    @Autowired
    private Game game;

    @EventListener(ContextRefreshedEvent.class)
    public void start(){
        log.info("Container ready to use.");

        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println(messageGenerator.getMainMessage());
            System.out.println(messageGenerator.getResultMassage());
            int guess = scanner.nextInt();
            scanner.nextLine();
            game.setGuess(guess);
            game.check();
            if(game.isGameWon() || game.isGameLost()){
                System.out.println(messageGenerator.getResultMassage());
                System.out.println("You want to play again (y/n)?");
                String playAgain = scanner.nextLine().trim();
                if(!playAgain.equalsIgnoreCase("y")){
                    break;
                }
                game.reset();
            }

        }
    }
}
