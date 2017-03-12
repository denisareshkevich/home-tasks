package by.oreshkevich.garagecars;

public abstract class Car {

    protected String registrationNumber;

    public Car(String registrationNumber, String model, String mark, int weight, int yearOfIssue, int countIfPassengers) {
        this.registrationNumber = registrationNumber;
        this.model = model;
        this.mark = mark;
        this.weight = weight;
        this.yearOfIssue = yearOfIssue;
        this.countOfPassengers = countIfPassengers;
    }

    private String model;
    private String mark;
    private int weight;
    private int yearOfIssue;
    private int countOfPassengers;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getYearOfIssue() {
        return yearOfIssue;
    }

    public void setYearOfIssue(int yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }

    public int getCountOfPassengers() {
        return countOfPassengers;
    }

    public void setCountOfPassengers(int countOfPassengers) {
        this.countOfPassengers = countOfPassengers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;

        Car car = (Car) o;

        if (registrationNumber == car.registrationNumber) return false;
        if (weight != car.weight) return false;
        if (yearOfIssue != car.yearOfIssue) return false;
        if (countOfPassengers != car.countOfPassengers) return false;
        if (!model.equals(car.model)) return false;
        return mark.equals(car.mark);
    }

    @Override
    public int hashCode() {
        int result = model.hashCode();
        result = 31 * result + registrationNumber.hashCode();
        result = 31 * result + mark.hashCode();
        result = 31 * result + weight;
        result = 31 * result + yearOfIssue;
        result = 31 * result + countOfPassengers;
        return result;
    }
}
