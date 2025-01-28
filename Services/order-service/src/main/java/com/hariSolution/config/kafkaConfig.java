package com.hariSolution.config;

import com.fasterxml.jackson.databind.ser.std.JsonValueSerializer;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import com.hariSolution.KafkaProducer.OrderConfirmation;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.LinkedHashMap;

@Configuration
public class kafkaConfig {

    @Bean
    public ProducerFactory<String, OrderConfirmation> producerFactory(){

        LinkedHashMap<String, Object> config=new LinkedHashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonValueSerializer.class);
        return new DefaultKafkaProducerFactory<>(config);


    }
    @Bean
    public KafkaTemplate<String,OrderConfirmation> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }


    @Bean
    public NewTopic newTopic() {
        return TopicBuilder.name("order-confirm").build();
    }

}
