package nuricanozturk.dev.service.order.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;

@Component
public class OrderKafkaTopic
{
    @Bean("kafka.topic.order-topic")
    public NewTopic provideBookTopic(@Value("${spring.kafka.order-topic-name}") String topicName)
    {
        return TopicBuilder.name(topicName).build();
    }
}
