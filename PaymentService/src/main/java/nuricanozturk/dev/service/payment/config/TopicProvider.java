package nuricanozturk.dev.service.payment.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;

@Component
public class TopicProvider
{
    @Bean
    public NewTopic providePaymentTopic(@Value("${spring.kafka.payment-topic-name}") String topicName)
    {
        return TopicBuilder.name(topicName).build();
    }
}
