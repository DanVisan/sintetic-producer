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

    public boolean sendTransaction() {
        Transaction message = new Transaction(1L, "gigi", "relu", 10.2, "RON", "taxi", LocalDateTime.now());
        return kafkaSender.sendToKafka(message);
    }
}
