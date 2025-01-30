package com.hariSolution.config;

import com.fasterxml.jackson.databind.ser.std.JsonValueSerializer;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import com.hariSolution.producer.OrderConfirmation;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.yaml.snakeyaml.serializer.Serializer;

import java.util.LinkedHashMap;

@Configuration
public class KafkaConfig {
    /*
    @Bean
    public ProducerFactory<String, OrderConfirmation> kafkaProducer(){
        LinkedHashMap<String,Object> configuration=new LinkedHashMap<>();
        configuration.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        configuration.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configuration.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonValueSerializer.class);

       return new DefaultKafkaProducerFactory<>(configuration);

    }
    public KafkaTemplate<String,OrderConfirmation> kafkaTemplate(){
        return new KafkaTemplate<>(kafkaProducer());

    }*/


    @Bean
    public NewTopic newTopic(){
        return TopicBuilder.name("order-confirmation").build();
    }


}
