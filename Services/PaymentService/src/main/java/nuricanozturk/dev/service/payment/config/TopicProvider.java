package nuricanozturk.dev.service.payment.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;

@Component
public class TopicProvider
{
    @Bean
    @Primary
    public NewTopic providePaymentTopic(@Value("${spring.kafka.payment-topic-name}") String topicName)
    {
        return TopicBuilder.name(topicName).build();
    }

    @Bean("log-topic")
    public NewTopic providePaymentLogTopic(@Value("${spring.kafka.log-topic-name}") String topicName)
    {
        return TopicBuilder.name(topicName).build();
    }
}
