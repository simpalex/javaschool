package js.junit.calc;

import org.apache.log4j.Logger;

public class Calculator {

    private static Logger logger = Logger.getLogger(Calculator.class);


	public Integer maxValue(Integer a, Integer b) {

        logger.debug("a = " + a + "; b = " + b);

        if (a == null || b == null) {
            logger.error("Param must be not null");
			throw new IllegalArgumentException("Param must be not null");
		}

        // The JVM is caching Integer values. works normal for numbers not between -128 and 127
		if (a < b) {
			return b;
		}
		return a;
	}
}





