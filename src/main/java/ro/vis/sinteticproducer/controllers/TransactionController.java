package ro.vis.sinteticproducer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.vis.sinteticproducer.services.TransactionService;

import java.util.Objects;

@RestController
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = Objects.requireNonNull(transactionService, "transactionService must not be null!");
    }

    @GetMapping("/input")
    public ResponseEntity<String> mockCommand(@RequestParam final String to,
                                              @RequestParam final String from,
                                              @RequestParam final double value) {
        return transactionService.sendTransaction(to, from, value) ? ResponseEntity.ok("Message Sent!") : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send message");
    }
}
