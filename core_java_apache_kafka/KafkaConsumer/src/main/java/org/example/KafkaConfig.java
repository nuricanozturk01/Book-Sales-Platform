package org.example;

import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.util.Properties;

public final class KafkaConfig
{
    private KafkaConfig()
    {
    }

    public static Properties configureKafka(String bootstrapServers, String keyDeserialization, String valueDeserialization, String groupId)
    {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserialization);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeserialization);
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        return properties;
    }
}
