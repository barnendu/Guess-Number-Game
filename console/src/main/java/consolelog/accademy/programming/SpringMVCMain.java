package consolelog.accademy.programming;

import consolelog.accademy.config.AppConfig;
import consolelog.accademy.Game;
import consolelog.accademy.MessageGenerator;
import consolelog.accademy.NumberGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringMVCMain {
    private final static Logger log = LoggerFactory.getLogger(SpringMVCMain.class);
//    This id not required as we are moving from ClassPathXMLApplicationContext to AnnotationConfigApplicationContext
//    private final static String BEAN_CONFIG ="beans.xml";
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        NumberGenerator numberGenerator = context.getBean(NumberGenerator.class);
        log.info("next number={}", numberGenerator.next());
        Game game = context.getBean(Game.class);
        MessageGenerator messageGenerator = context.getBean(MessageGenerator.class);
        log.info("messageGenerator ={}", messageGenerator.getMainMessage());
        context.close();
    }

}
