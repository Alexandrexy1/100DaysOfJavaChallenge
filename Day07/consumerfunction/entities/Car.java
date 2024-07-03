package entities;

public class Car {
    private String name;
    private Double price;

    public Car(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String toString() {
        return "Car: " + name + ", price with discount: $ " + String.format("%.2f", price);
    }
}