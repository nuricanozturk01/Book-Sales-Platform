package nuricanozturk.dev.service.order.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;

import static nuricanozturk.dev.service.order.util.BeanName.STOCK_BOOK_TOPIC;
import static nuricanozturk.dev.service.order.util.BeanName.STOCK_ORDER_TOPIC;

@Component
public class TopicProvider
{
    @Bean("stock-info-topic")
    public NewTopic provideStockTopic(@Value("${spring.kafka.stock-info-topic-name}") String topicName)
    {
        return TopicBuilder.name(topicName).build();
    }

    @Bean(STOCK_BOOK_TOPIC)
    public NewTopic provideStockBookTopic(@Value("${spring-kafka-book-status-topic-name}") String topicName)
    {
        return TopicBuilder.name(topicName).build();
    }

    @Bean(STOCK_ORDER_TOPIC)
    public NewTopic provideOrderTopic(@Value("${spring-kafka-order-status-topic-name}") String topicName)
    {
        return TopicBuilder.name(topicName).build();
    }
}