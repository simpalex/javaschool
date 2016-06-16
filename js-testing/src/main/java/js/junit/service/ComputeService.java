package js.junit.service;

import js.junit.calc.Calculator;
import js.junit.model.Connection;
import org.apache.log4j.Logger;

/**
 * Compute Service
 *
 * @author <a href="mailto:Alekseychenko.Vladimir@t-systems.ru">Alekseychenko.Vladimir</a>
 */
public class ComputeService {

    private Calculator calculator;
    private Connection connection;

    private static Logger logger = Logger.getLogger(ComputeService.class);

    public ComputeService(Calculator calculator, Connection connection) {
        this.calculator = calculator;
        this.connection = connection;
    }

    public Integer computeMaxValue(Integer a, Integer b) {

        if (!connection.isActive()) {
            throw new RuntimeException("Can't compute max value because connection is not active");
        }

        Integer maxValue = calculator.maxValue(a, b);

        String report = "a = " + a + "; b = " + b + "; maxValue = " + maxValue;

        sendReport(report);

        return maxValue;
    }

    private void sendReport(String report) {
        logger.info(report);

        // send report functionality
    }
}
