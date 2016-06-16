package js.junit.model;

/**
 * Created by valexeyc on 22.04.14.
 */
public class Patient extends Human{

    public void callDoctor(Doctor doctor) {

        System.out.println("Hi doctor: " + doctor.name + " " + doctor.surname);
        System.out.println(doctor);
    }
}
