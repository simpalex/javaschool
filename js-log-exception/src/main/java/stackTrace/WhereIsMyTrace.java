package stackTrace;
/**                                                                                                                                                                                                -XX:-OmitStackTraceInFastThrow**/

import java.util.concurrent.ThreadLocalRandom;

public class WhereIsMyTrace {


    public static final int EXPERIMENT = 1_000_000_000;
//    public static final int EXPERIMENT = 1_000;

    public static void main(String[] args) {
        double sorted = 0D;
        for (int i = 0; i < EXPERIMENT; i++) {
            try {
                int length = 10;//ThreadLocalRandom.current().nextInt(100, 10000);
                long[] array = ThreadLocalRandom.current().longs(length).toArray();
                if (isSorted(array))
                    ++sorted;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(sorted);
        System.out.println(sorted / EXPERIMENT);
    }


    public static boolean isSorted(long[] array) {
        for (int i = 0; i < array.length ; i++) {
            if (array[i] > array[i+1]) {
                return false;
            }
        }
        return true;
    }
}
