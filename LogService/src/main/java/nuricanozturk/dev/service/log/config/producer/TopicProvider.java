package nuricanozturk.dev.service.log.config.producer;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;

@Component
public class TopicProvider
{
    @Value("${spring.kafka.log-topic-name}")
    private String topicName;

    @Bean
    public NewTopic provideTopic()
    {
        return TopicBuilder.name(topicName).build();
    }
}
