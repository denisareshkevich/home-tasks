package by.oreshkevich.garagecars;

/**
 * Created by Lenovo on 09.03.2017.
 */
public class Main {
    public static void main(String[] args) {
        Bus bus1 = new Bus("2000-AM", "MAz", "109", 5000, 2001, 40,
                30);
        Car pas1 = new Passenger("2939-BM", "Tayota", "LandCruiser", 1999, 2015,
                4, 12);
        Car pas2 = new Passenger("3951-KK", "Tayota", "LandCruiser", 1999, 2015,
                4, 123);
        Car pas3 = new Passenger("2131-SS", "Tayota", "LandCruiser", 1999, 2015,
                4, 3333);

        Garage garage = new Garage();
        garage.parkCar(bus1);
        garage.parkCar(pas1);
        garage.parkCar(pas2);
        garage.parkCar(pas3);
        System.out.println(garage.getCountOfType(pas2));
    }
}
