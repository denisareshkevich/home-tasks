package by.oreshkevich.garagecars;

/**
 * Created by Lenovo on 09.03.2017.
 */
public class Truck extends Car {
    public Truck(String registrationNumber, String model, String mark, int weight, int yearOfIssue,
                 int countIfPassengers, int tonnage) {
        super(registrationNumber, model, mark, weight, yearOfIssue, countIfPassengers);
        this.tonnage = tonnage;
    }

    private int tonnage;

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + tonnage;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Truck && super.equals(obj)
                && tonnage == ((Truck) obj).tonnage;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
