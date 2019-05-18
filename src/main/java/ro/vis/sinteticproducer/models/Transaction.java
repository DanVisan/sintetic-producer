package ro.vis.sinteticproducer.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Transaction {
    private long id;
    private String sender;
    private String receiver;
    private double value;
    private String currency;
    private String message;
    private LocalDateTime timestamp;

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id + "," +
                "\"sender\":\"" + sender + "\"," +
                "\"receiver\":\"" + receiver + "\"," +
                "\"value\":" + value + "," +
                "\"currency\":\"" + currency + "\"," +
                "\"message\":\"" + message + "\"," +
                "\"timestamp\":\"" + timestamp.toString() + "\"" +
                "}";
    }
}
