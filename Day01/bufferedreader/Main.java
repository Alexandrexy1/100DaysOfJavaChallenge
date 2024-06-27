package bufferedreader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("myFaith.txt"))){
            String message = br.readLine();
            while (message != null) {
                System.out.println(message); // Jesus loves you | Jesus bless you | Jesus is my lord
                message = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
