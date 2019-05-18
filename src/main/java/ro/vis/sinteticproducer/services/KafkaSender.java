package ro.vis.sinteticproducer.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.MimeTypeUtils;
import ro.vis.sinteticproducer.models.Transaction;

import java.util.Objects;

@EnableBinding(KafkaChannels.class)
public class KafkaSender {
    private static final Logger LOG = LoggerFactory.getLogger(KafkaSender.class);

    private final KafkaChannels kafkaChannels;

    @Autowired
    public KafkaSender(final KafkaChannels kafkaChannels) {
        this.kafkaChannels = Objects.requireNonNull(kafkaChannels, "kafkaChannels must not be null");
    }

    @Async
    public boolean sendToKafka(final Transaction transactionMessage) {
        MessageChannel channel = kafkaChannels.eventsOutput();
        Message<String> eventMessage = MessageBuilder
                .withPayload(transactionMessage.toString())
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build();
        return sendMessage(channel, eventMessage);
    }

    private boolean sendMessage(final MessageChannel channel, final Message message) {
        try {
            final boolean messageSent = channel.send(message);
            if (!messageSent) {
                LOG.error("The message could not be sent due to a non-fatal reason:", message.getPayload());
                return false;
            }
            return true;
        } catch (RuntimeException ex) {
            LOG.error("Non-recoverable error. Unable to send message='{}'", message.getPayload(), ex);
            return false;
        }
    }
}
