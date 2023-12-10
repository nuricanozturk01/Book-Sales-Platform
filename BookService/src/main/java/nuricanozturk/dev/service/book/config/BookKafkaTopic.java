package nuricanozturk.dev.service.book.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;

@Component
public class BookKafkaTopic
{
    @Bean
    public NewTopic provideBookTopic(@Value("${spring.kafka.book-info-topic-name}") String topicName)
    {
        return TopicBuilder.name(topicName).build();
    }
}
