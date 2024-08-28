package com.example.streamproducer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
public class EventController {

    private final String HEADER = "EVENT_TYPE";
    private final String SPECIAL_NUMBER_EVENT = "SPECIAL_NUMBER_EVENT";
    private final String OPERATOR_EVENT = "OPERATOR_EVENT";

    private static final Logger log = LoggerFactory.getLogger(EventController.class);

    private final MessageChannel stream;

    public EventController(ProducerChannels stream) {
        this.stream = stream.streamProducerTest();
    }

    @PostMapping("/special-number/{phone-number}")
    ResponseEntity<String> addPhoneNumber(@PathVariable("phone-number") String phoneNumber) {
        //add phone number
        SpecialNumberEvent event = new SpecialNumberEvent(phoneNumber, EventType.ADD);
        this.stream.send(MessageBuilder
                .withPayload(event)
                .setHeader(HEADER, SPECIAL_NUMBER_EVENT)
                .build());
        log.info("{} {}", event.getEventType(), event.getSpecialNumber());
        return ResponseEntity.ok("add-number signal was produced");
    }

    @DeleteMapping("/special-number/{phone-number}")
    ResponseEntity<String> removePhoneNumber(@PathVariable("phone-number") String phoneNumber) {
        //remove phone number
        SpecialNumberEvent event = new SpecialNumberEvent(phoneNumber, EventType.REMOVE);
        this.stream.send(MessageBuilder
                .withPayload(event)
                .setHeader(HEADER, SPECIAL_NUMBER_EVENT)
                .build());
        log.info("{} {}", event.getEventType(), event.getSpecialNumber());
        return ResponseEntity.ok("remove-number signal was produced");
    }

    @PostMapping("/operator/{operator}")
    ResponseEntity<String> addOperator(@PathVariable("operator") String operator) {
        //add operator
        OperatorEvent event = new OperatorEvent(operator, new Random().nextInt(), EventType.ADD);
        this.stream.send(MessageBuilder
                .withPayload(event)
                .setHeader(HEADER, OPERATOR_EVENT)
                .build());
        log.info("{} {} {}", event.getEventType(), event.getName(), event.getPrice());
        return ResponseEntity.ok("add-operator signal was produced");
    }

    @DeleteMapping("/operator/{operator}")
    ResponseEntity<String> removeOperator(@PathVariable("operator") String operator) {
        //remove operator
        OperatorEvent event = new OperatorEvent(operator, new Random().nextInt(), EventType.REMOVE);
        this.stream.send(MessageBuilder
                .withPayload(event)
                .setHeader(HEADER, OPERATOR_EVENT)
                .build());
        log.info("{} {} {}", event.getEventType(), event.getName(), event.getPrice());
        return ResponseEntity.ok("remove-operator signal was produced");
    }
}