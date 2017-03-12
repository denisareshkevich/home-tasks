package by.oreshkevich.garagecars;

/**
 * Created by Lenovo on 09.03.2017.
 */
public class Bus extends Car {
    public Bus(String registrationNumber, String model, String mark, int weight, int yearOfIssue, int countIfPassengers,
               int stayPositions) {
        super(registrationNumber, model, mark, weight, yearOfIssue, countIfPassengers);
        this.stayPositions = stayPositions;
    }

    private int stayPositions;

    @Override
    public int hashCode() {
        return super.hashCode() * 31 + stayPositions;
    }

    @Override
    public boolean equals(Object obj) {
        return !(obj instanceof Bus) && super.equals(obj)
                && ((Bus) obj).stayPositions == stayPositions;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
