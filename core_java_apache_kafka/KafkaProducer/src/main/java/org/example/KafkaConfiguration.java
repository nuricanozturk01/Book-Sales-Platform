package org.example;

import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Properties;

public class KafkaConfiguration
{
    private KafkaConfiguration()
    {
    }

    public static final String STRING_SERIALIZATION = "org.apache.kafka.common.serialization.StringSerializer";

    public static Properties configureKafka(String bootstrapServers)
    {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, STRING_SERIALIZATION);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, STRING_SERIALIZATION);
        return properties;
    }
}
