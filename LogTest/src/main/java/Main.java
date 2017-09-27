import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    final static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        int a = 3;
        int b = 4;
        logger.info("a={}, b={}", a, b);

        try {
            int c = 4 / 0;

        } catch (Exception e){
            logger.error("something wrong: {}", e);
        }
    }
}
