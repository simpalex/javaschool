package exceptions.multicatch;


import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("ThrowableInstanceNeverThrown")
public class MultiCatch {

    public static void main(String[] args) {
        Exception ex[] = {new MyException1("E1"), new MyException2("E2"), new Exception("lol")};
        for (int i = 0; i < 10; i++) {
            try {

                int e = Math.abs(ThreadLocalRandom.current().nextInt());
                throw ex[e % 3];

            } catch (MyException1 | MyException2 e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
