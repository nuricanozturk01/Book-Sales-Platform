package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.example.KafkaConfig.configureKafka;

public class Main
{

    public static void main(String[] args)
    {
        var bootstrapServers = "localhost:9092";

        var topic1 = "str-topic";
        var topic2 = "user-topic";

        var strGroupId = "str-group";
        var userGroupId = "user-group";

        var strProperties = configureKafka(bootstrapServers, StringDeserializer.class.getName(), StringDeserializer.class.getName(), strGroupId);
        var userProperties = configureKafka(bootstrapServers, StringDeserializer.class.getName(), StringDeserializer.class.getName(), userGroupId);

        try (var strConsumer = new KafkaConsumer<String, String>(strProperties); // Auto closeable
             var userConsumer = new KafkaConsumer<String, String>(userProperties))
        {
            strConsumer.subscribe(List.of(topic1, topic2));
            userConsumer.subscribe(List.of(topic1, topic2));

            var objectMapper = new ObjectMapper();

            while (true)
            {
                var strRecords = strConsumer.poll(Duration.ofMillis(TimeUnit.MINUTES.toMillis(1)));
                var userRecords = userConsumer.poll(Duration.ofMillis(TimeUnit.MINUTES.toMillis(1)));

                for (var userRecord : userRecords)
                {
                    try
                    {
                        var userInfo = userRecord.value();
                        var user = objectMapper.writeValueAsString(userInfo);
                        System.out.printf("Key: %s, Value: %s, Topic: %s%n", userRecord.key(), user, userRecord.topic());
                    }
                    catch (Exception e)
                    {
                        System.err.println("fsdfsdfsdfsdf occurred while consuming message: " + e.getMessage());
                    }
                }
            }
        } catch (Exception e)
        {
            System.err.println("Exception occurred while consuming message: " + e.getMessage());
        }
    }

}