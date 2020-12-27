package consolelog.accademy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class NumberGeneratorImpl implements NumberGenerator {
    private final Random random = new Random();

    private final int maxNumber;

    @Autowired
    public NumberGeneratorImpl( @MaxNumber int maxNumber) {
        // it's always good practice to have somthing autowired inside the constructor as it's make things immutaable.
        this.maxNumber = maxNumber;
    }

    @Override
    public int next() {
        return random.nextInt(maxNumber);
    }

    @Override
    public int getMaxNumber() {
        return maxNumber;
    }
}
