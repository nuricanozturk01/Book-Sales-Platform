package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import static java.util.stream.IntStream.range;

public class Main
{

    public static void main(String[] args)
    {
        var bootstrapServers = "localhost:9092";
        var topic1 = "str-topic";
        var topic2 = "user-topic";

        var stringProperties = KafkaConfiguration.configureKafka(bootstrapServers);
        var userProperties = KafkaConfiguration.configureKafka(bootstrapServers);

        try (var stringKafkaProducer = new KafkaProducer<String, String>(stringProperties);
             var userKafkaProducer = new KafkaProducer<String, String>(userProperties))
        {
            range(0, 5).forEach(i -> stringKafkaProducer.send(new ProducerRecord<>(topic1, "key-" + i, "Message " + i + " from Java Producer")));

            var user = new UserInfo("Nuri Can", "OZTURK", 30);

            var userRecord = new ProducerRecord<String, String>(topic2, "key-2", user.toString());
            userKafkaProducer.send(userRecord);
        } catch (Exception e)
        {
            System.err.println("Exception occurred while producing message: " + e.getMessage());
        }
    }
}