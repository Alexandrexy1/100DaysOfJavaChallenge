package entities;

public final class Car implements Comparable<Car> {
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
    
    @Override
    public int compareTo(Car other) {
        return this.price.compareTo(other.getPrice());
    }

    @Override
    public String toString() {
        return "Name " + name + ", Price " + String.format("%.2f", price);
    }
}
