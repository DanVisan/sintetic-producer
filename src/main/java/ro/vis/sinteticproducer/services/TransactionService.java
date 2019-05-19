package ro.vis.sinteticproducer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.vis.sinteticproducer.models.Transaction;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class TransactionService {
    private KafkaSender kafkaSender;

    @Autowired
    public TransactionService(KafkaSender kafkaSender) {
        this.kafkaSender = Objects.requireNonNull(kafkaSender, "kafkaSender must not be null!");
    }

    public boolean sendTransaction(String receiver, String sender, double value) {
        Transaction message = new Transaction(1L, sender, receiver, value, "RON", "taxi", LocalDateTime.now());
        return kafkaSender.sendToKafka(message);
    }
}
