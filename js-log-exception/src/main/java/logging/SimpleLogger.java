package logging;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleLogger {
    static final Logger logger = Logger.getLogger(SimpleLogger.class.getName());

    public static void main(String[] args) {

        logger.setLevel(Level.FINEST);

        logger.log(Level.WARNING, "Houston, we have a problem");

        //TODO few parameters
        logger.info("hello {}" );

        logger.fine("everything OK");

    }
}
