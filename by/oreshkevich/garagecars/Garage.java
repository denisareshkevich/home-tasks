package by.oreshkevich.garagecars;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lenovo on 09.03.2017.
 */
public class Garage {
    protected static Map<Car, Integer> garageHashMap = new HashMap<>();


    protected static int getCountOfType(Car car) {
        int count = 0;

        for (Car key : garageHashMap.keySet()) {
            if (key.equals(car)) {
                count++;
            }
        }
        return count;
    }

    public void parkCar(Car car) {
        garageHashMap.put(car, 0);
    }

    public void unpark(Car car) {
        garageHashMap.remove(car);
    }

    protected static Map<Car, Integer> getGarageHashMap() {
        return garageHashMap;
    }
}
