package nuricanozturk.dev.data.usermanagement.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;

@Component
public class UserKafkaTopic
{
    @Bean
    public NewTopic provideBookTopic(@Value("${spring.kafka.user-info-topic-name}") String topicName)
    {
        return TopicBuilder.name(topicName).build();
    }
}
