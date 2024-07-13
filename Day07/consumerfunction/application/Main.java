package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import entities.Car;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        List<Car> cars = new ArrayList<>();

        cars.add(new Car("Chevrolet Onix", 100000));
        cars.add(new Car("Hyundai HB20", 85000));
        cars.add(new Car("Volkswagen Polo", 105000));
        cars.add(new Car("Fiat Argo", 75000));
        cars.add(new Car("Honda Civic", 185000));
        cars.add(new Car("Audi A4", 330000));

        System.out.println("10% discount on cars");

        cars.forEach(car -> car.setPrice(car.getPrice() * 0.9));
        
        cars.forEach(System.out::println);
        // Car: Chevrolet Onix, price with discount: $ 90000.00
        // Car: Hyundai HB20, price with discount: $ 76500.00
        // Car: Volkswagen Polo, price with discount: $ 94500.00
        // Car: Fiat Argo, price with discount: $ 67500.00
        // Car: Honda Civic, price with discount: $ 166500.00
        // Car: Audi A4, price with discount: $ 297000.00
    }
}


