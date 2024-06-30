import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        List<String> arrayName = new ArrayList<>(); // ArrayList saves all data entered

        arrayName.add("Joao");
        arrayName.add("Paulo");
        arrayName.add("Fulano");
        arrayName.add("Alexandre");
        arrayName.add("Alexandre");
        arrayName.add("Alexandre");

        arrayName.remove("Fulano");
        arrayName.addFirst("Someone");

        int frequency = Collections.frequency(arrayName, "Alexandre"); // this counts how many times there is a certain element in the list
        
        arrayName.removeIf(name -> name.startsWith("A") | name.startsWith("S")); // remove with a condition

        Collections.reverse(arrayName); // leave the order of the list reversed
        
        System.out.println(arrayName); // [Paulo, Joao]
        System.out.println(arrayName.indexOf("Paulo")); // 1
        System.out.println(frequency); // 3

        
        Set<String> hashName = new HashSet<>(); // HashSet does not care about order and does not tolerate repeated data

        hashName.add("potato");
        hashName.add("banana");
        hashName.add("potato");
        hashName.add("pineapple");
        
        System.out.println(hashName); // [banana, pineapple, potato]
    }
}