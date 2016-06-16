package exceptions.withResources;


public class MyResource1 implements AutoCloseable {

    @Override
    public void close() throws Exception {
        System.err.println("I do very usefull job: I close resouces");
    }
}
