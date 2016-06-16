package exceptions.withResources;


public class TryWithResources {

    public static void main(String[] args) {

        justClose();
        calmClose();
    }

    public static void justClose() {

        try (MyResource1 resource = new MyResource1()) {
            // some code
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final void calmClose() {
        try (MyResource2 resource = new MyResource2()) {
            // some code
        }
    }
}
