package nuricanozturk.dev.service.order.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;

import static nuricanozturk.dev.service.order.util.BeanName.BOOK_STATUS_TOPIC;
import static nuricanozturk.dev.service.order.util.BeanName.ORDER_STATUS_TOPIC;

@Component
public class TopicProvider
{
    @Bean
    @Primary
    public NewTopic provideStockTopic(@Value("${spring.kafka.stock-info-topic-name}") String topicName)
    {
        return TopicBuilder.name(topicName).build();
    }

    @Bean(ORDER_STATUS_TOPIC)
    public NewTopic provideStockBookTopic(@Value("${spring.kafka.order-status-info-topic-name}") String topicName)
    {
        return TopicBuilder.name(topicName).build();
    }

    @Bean(BOOK_STATUS_TOPIC)
    public NewTopic provideOrderTopic(@Value("${spring-kafka-book-status-topic-name}") String topicName)
    {
        return TopicBuilder.name(topicName).build();
    }
}