package logging;

import java.io.IOException;
import java.util.logging.*;

public class LogHandler {
   static Logger logger = Logger.getLogger(LogHandler.class.getName());
    public static void main(String[] args) throws IOException {
//        FileHandler fileHandler = new FileHandler("log.txt");
//        fileHandler.setFormatter(new XMLFormatter());
//        fileHandler.setFormatter(new SimpleFormatter());
//        fileHandler.setLevel(Level.INFO);
//        logger.addHandler(fileHandler);

        logger.log(Level.INFO, "hello");
        logger.log(Level.WARNING, "Houston, we have a problem");
        logger.log(Level.FINE, "everything OK");
        logger.log(Level.SEVERE, "EXEPTION {0} ", new Exception());
        logger.log(Level.SEVERE, "EXEPTION {0} {1}", new Object[] {1, 2, 3});
    }
}
