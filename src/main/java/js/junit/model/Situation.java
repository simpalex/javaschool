package js.junit.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by valexeyc on 22.04.14.
 */
public class Situation {

    public static final int CAPACITY = 1024;

    public static void main(String[] args) throws IOException, InterruptedException {

        Patient vasya = new Patient();

        Doctor alena = new Doctor();
        alena.sex = Sex.Female;
        alena.name = "Alena";
        alena.surname = "Alekseichenko";
        alena.specialization = Specialization.Pediatric;

        vasya.callDoctor(alena);

        processFileChannel();

    }

    public static void processFileChannel() throws IOException, InterruptedException {
        FileChannel channel = new FileOutputStream("C:\\1.txt").getChannel();

        channel.lock();

        int i = 0;

        while(i++ < 10) {
            Thread.sleep(1000);
            String s = "locked" + i + "\n";
            System.out.println(s);

            ByteBuffer wrap = ByteBuffer.wrap(s.getBytes());
            channel.write(wrap);
        }

        channel.close();
    }

}
