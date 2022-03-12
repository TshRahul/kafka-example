package com.stereo.kafkaexample.config;

import com.stereo.kafkaexample.MessageRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics = "stereo", groupId = "group_id")
    void listener(MessageRequest data){
        System.out.println("Listner received: " + data + " :)");
    }
}
