package stackTrace;

public class WhoCallMe {

    public static void main(String[] args) {
        WhoCallMe callMe = new WhoCallMe();
        callMe.whoCallMe();
        callMe.foo();
    }

    public void foo() {
        bar();
    }

    private void bar() {
        String methodName = whoCallMe();
        System.out.println(methodName);

    }

    public String whoCallMe() {
        StackTraceElement stackTraceElement = new Exception().getStackTrace()[2];
        return stackTraceElement.getMethodName();
    }
}
