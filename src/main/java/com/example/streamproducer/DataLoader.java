//package com.example.streamproducer;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.stereotype.Component;
//
//import static java.lang.Thread.sleep;
//
//@Component
//public class DataLoader implements ApplicationRunner {
//
//    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);
//
//    private final MessageChannel stream;
//
//    @Autowired
//    public DataLoader(ProducerChannels stream) {
//        this.stream = stream.streamProducerTest();
//    }
//
//    @Override
//    public void run(ApplicationArguments args) throws InterruptedException {
//        SpecialNumberEvent event;
//        for (int i = 0; i < 5; i++) {
//            if (i % 2 == 0) {
//                event = new SpecialNumberEvent("09116715043", EventType.ADD);
//            } else {
//                event = new SpecialNumberEvent("09116174053", EventType.REMOVE);
//            }
//            this.stream.send(MessageBuilder.withPayload(event).build());
//            log.info("{} {}", event.getEventType(), event.getSpecialNumber());
//            sleep(1000);
//        }
//    }
//}