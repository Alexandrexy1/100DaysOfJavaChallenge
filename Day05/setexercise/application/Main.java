package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import entities.UserLog;

public class Main {
    public static void main(String[] args) {
        Set<UserLog> userLogs = new HashSet<>();

        try (BufferedReader bReader = new BufferedReader(new FileReader("logs.txt"))){
            String line = bReader.readLine();

            while (line != null ){ 
                String[] userInfo = line.split(" "); 

                userLogs.add(new UserLog(userInfo[0], Instant.parse(userInfo[1])));

                line = bReader.readLine();
            }

            for (UserLog userLog: userLogs) {
                System.out.println(userLog);
                // User [username=smithzen, date=30/05/2024 11:12:00]
                // User [username=alexandrexy, date=01/07/2024 17:42:30]
                // User [username=amanda, date=28/06/2024 18:55:20]
                // User [username=joaodarley, date=21/06/2024 04:48:18]
                // User [username=bigbro, date=16/06/2024 15:23:54]
            }

        } catch(IOException e) {
            e.printStackTrace();
        }

    }
}
