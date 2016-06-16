package js.junit.model;

/**
 * Created by valexeyc on 22.04.14.
 */
public class Human {

    String name;
    String surname;
    Sex sex;
    int height;
    int weight;

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", sex=" + sex +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }
}
