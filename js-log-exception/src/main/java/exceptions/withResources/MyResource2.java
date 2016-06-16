package exceptions.withResources;


public class MyResource2 implements AutoCloseable {
    @Override
    public void close()  {
        try {
            System.out.println("Resourcers closed, exceptions are not handed");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
