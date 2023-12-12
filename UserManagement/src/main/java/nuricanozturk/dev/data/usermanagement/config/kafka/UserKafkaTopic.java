package nuricanozturk.dev.data.usermanagement.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;

@Component
public class UserKafkaTopic
{
    @Bean
    @Primary
    public NewTopic provideBookTopic(@Value("${spring.kafka.user-info-topic-name}") String topicName)
    {
        return TopicBuilder.name(topicName).build();
    }

    @Bean("log-topic")
    public NewTopic providePaymentTopic(@Value("log-producer-topic") String topicName)
    {
        return TopicBuilder.name(topicName).build();
    }
}
