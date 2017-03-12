package by.oreshkevich.garagecars;

/**
 * Created by Lenovo on 09.03.2017.
 */
public class RouteTaxi extends Car {
    public RouteTaxi(String registrationNumber, String model,
                     String mark, int weight, int yearOfIssue, int countIfPassengers, int pruninkCars) {
        super(registrationNumber, model, mark, weight, yearOfIssue, countIfPassengers);
        this.pruninkCars = pruninkCars;
    }

    private int pruninkCars;

    @Override
    public int hashCode() {
        return super.hashCode() * 31 + pruninkCars;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof RouteTaxi && super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
