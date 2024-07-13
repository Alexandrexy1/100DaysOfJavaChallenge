package bufferedwriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> messagesList = new ArrayList<>(Arrays.asList("Jesus loves you", "Jesus bless you", "Jesus is my lord"));
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("myFaith.txt"))){

            for (String message: messagesList) {
                bw.write(message);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
