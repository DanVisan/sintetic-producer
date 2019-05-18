package ro.vis.sinteticproducer.services;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface KafkaChannels {
    String EVENTS_OUTPUT = "events";

    @Output(KafkaChannels.EVENTS_OUTPUT)
    MessageChannel eventsOutput();

}
