import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import entities.Car;
import services.CalculationPriceCarService;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        List<Car> carList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("cars.txt"))){
            String lineCar = bufferedReader.readLine();

            while (lineCar != null) {
                String[] carFields = lineCar.split(",");

                carList.add(new Car(carFields[0], Double.parseDouble(carFields[1])));

                lineCar = bufferedReader.readLine();
            }
            
            Car max = CalculationPriceCarService.highestPrice(carList);

            System.out.println("The highest car price: ");
            System.out.println(max); // Name Audi A4, Price 330000.00

        } catch(IOException e) {
            e.printStackTrace();
        }

    }
}