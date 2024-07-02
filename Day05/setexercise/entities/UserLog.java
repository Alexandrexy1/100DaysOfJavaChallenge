package entities;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class UserLog {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").withZone(ZoneId.systemDefault());
    private String username;
    private Instant date;

    public UserLog(String username, Instant date) {
        this.username = username;
        this.date = date;
    }

    public String getUsername() { 
        return username;
    };

    public Instant getDate() {
        return date;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserLog other = (UserLog) obj;
        return Objects.equals(username, other.username);
    }

    @Override
    public String toString() {
        return "User [username=" + username + ", date=" + dtf.format(date) + "]";
    }
} 
