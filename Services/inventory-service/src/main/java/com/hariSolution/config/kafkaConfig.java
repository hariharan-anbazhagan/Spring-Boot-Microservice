package com.hariSolution.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class kafkaConfig {
    public NewTopic newTopic(){
        return TopicBuilder
                .name("quantity_update")
                .build();
    }
}
