package exceptions.multicatch;


public class MyException2 extends RuntimeException {
    public MyException2() {
        super();
    }

    public MyException2(String message) {
        super(message);
    }

    public MyException2(String message, Throwable cause) {
        super(message, cause);
    }

    public MyException2(Throwable cause) {
        super(cause);
    }

    protected MyException2(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
