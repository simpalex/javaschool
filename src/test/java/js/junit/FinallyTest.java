package js.junit;

import org.junit.Assert;
import org.junit.Test;

import java.io.PrintStream;
import java.text.MessageFormat;

/**
 * Class for Try-Catch-Finally functionality testing
 *
 * @author <a href="mailto:Alexeychenko.Vladimir@t-systems.ru">Alexeychenko.Vladimir</a>
 */
public class FinallyTest {

    public static final int TRY_RETURN = 1;
    public static final int CATCH_RETURN = 2;
    public static final int FINALLY_RETURN = 3;

    @Test
    public void testFinally_NoExceptions() throws Exception {

        // 1. No exceptions
        System.out.println("Stage 1");
        int result = testMethod(false, null);
        compareWithFinallyValue(result);
    }

    @Test
    public void testFinally_CaughtException() throws Exception {

        // 2. Caught exception
        System.out.println("Stage 2");
        int result = testMethod(true, new IllegalArgumentException("I wonna be caught!"));
        compareWithFinallyValue(result);
    }

    @Test
    public void testFinally_NonCaughtException() throws Exception {

        // 3. Non-caught exception
        System.out.println("Stage 3");
        int result = testMethod(true, new NullPointerException("I DON'T wonna be caught!"));
        compareWithFinallyValue(result);
    }

    private <T extends Throwable> int testMethod(boolean exceptionNeeded, T exception) {
        try {
            if (exceptionNeeded) {
                throw exception;
            }
            return TRY_RETURN;
        } catch (IllegalArgumentException e) {
            printException(System.err, e);
            return CATCH_RETURN;
        } finally {
            System.out.println("Finally: Reports return statements inside of finally blocks. While occasionally intended, such return statements may mask exceptions thrown, and tremendously complicate debugging.");
            return FINALLY_RETURN;
        }
    }

    private void compareWithFinallyValue(int result) {
        System.out.println("Result: " + result);
        Assert.assertEquals(FINALLY_RETURN, result);
    }

    private void printException(PrintStream stream, IllegalArgumentException e) {
        String report = MessageFormat.format("Caught: {0}. Message: {1}. StackTrace: {2}", e.getClass().getCanonicalName(), e.getMessage(), getStackTraceInfo());
        stream.println(report);
    }

    private String getStackTraceInfo() {
        StackTraceElement[] stackTrace = new Exception().getStackTrace();
        StackTraceElement targetFrame = stackTrace[2];

        return MessageFormat.format("[File: {0}; Class: {1}; Method->Line {2}->{3}]",
                targetFrame.getFileName(), targetFrame.getClassName(), targetFrame.getMethodName(), targetFrame.getLineNumber());
    }
}
