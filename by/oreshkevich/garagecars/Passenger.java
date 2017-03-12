package by.oreshkevich.garagecars;

/**
 * Created by Denis Areshkevich on 09.03.2017.
 */
public class Passenger extends Car {

    public Passenger(String registrationNumber, String model, String mark, int weight, int yearOfIssue,
                     int countIfPassengers, int power) {
        super(registrationNumber, model, mark, weight, yearOfIssue, countIfPassengers);
        this.power = power;
    }

    private int power;

    @Override
    public int hashCode() {
        return super.hashCode() * 31 + power;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Passenger && super.equals(obj)
                && ((Passenger) obj).power == power;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
