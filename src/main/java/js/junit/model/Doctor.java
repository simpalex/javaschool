package js.junit.model;

/**
 * Created by valexeyc on 22.04.14.
 */
public class Doctor extends Human{


    Specialization specialization;

    @Override
    public String toString() {
        return "Doctor{" +
                "specialization=" + specialization +
                "} " + super.toString();
    }
}
