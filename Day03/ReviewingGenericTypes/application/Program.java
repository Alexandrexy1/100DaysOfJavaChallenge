package application;

import entities.ArrayGenericService;

public class Program {
    public static void main(String[] args) {
        ArrayGenericService<Integer> agsInteger = new ArrayGenericService<>();

        agsInteger.addItem(1);
        agsInteger.addItem(2);
        agsInteger.addItem(3);
        agsInteger.showItems(); // [1, 2, 3]

        ArrayGenericService<String> agsString = new ArrayGenericService<>();

        agsString.addItem("Fabricio");
        agsString.addItem("Matheus");
        agsString.addItem("Lucas");
        agsString.showItems(); // [Fabricio, Matheus, Lucas]
    }
}