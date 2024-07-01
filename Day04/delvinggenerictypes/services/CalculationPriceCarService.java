package services;

import java.util.List;
import java.util.Comparator;


public abstract class CalculationPriceCarService {
    public static <T extends Comparable<? super T>> T highestPrice(List<T> list) {
        if (list.isEmpty() || list == null) throw new IllegalArgumentException("List must not be empty or null.");
        
        return list.stream().max(Comparator.naturalOrder()).orElseThrow();
    }
}
