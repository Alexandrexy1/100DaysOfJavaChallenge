package entities;

import java.util.ArrayList;
import java.util.List;

public class ArrayGenericService<T> {
    private List<T> genericList = new ArrayList<>();

    public void addItem(T item) {
        try {
            genericList.add(item);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showItems() {
        if (genericList.isEmpty()) throw new IllegalStateException("List must not be empty.");

        System.out.print("[");
        System.out.print(genericList.get(0));

        for(int count = 1; count < genericList.size(); count++) {
            System.out.print(", " + genericList.get(count));
        }
        System.out.println("]");
    }
}
