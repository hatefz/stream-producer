package com.example.streamproducer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProducerChannels {
    String STREAM = "event-stream-test";

    @Output(STREAM)
    MessageChannel streamProducerTest();
}