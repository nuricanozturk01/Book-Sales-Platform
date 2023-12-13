package nuricanozturk.dev.service.order.config;

import nuricanozturk.dev.service.order.config.listenerdto.BookStockInfo;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;

@EnableKafka
@Configuration
public class BookConsumerConfig
{
    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String m_servers;

    @Value("${spring.kafka.consumer.book-group-id}")
    private String m_bookGroupId;

    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String m_offsetResetConfig;


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, BookStockInfo> configBookInfoKafkaListener()
    {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, BookStockInfo>();
        factory.setConsumerFactory(bookInfoConsumerConfig());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, BookStockInfo> bookInfoConsumerConfig()
    {
        var bookInfoProperties = new HashMap<String, Object>();
        bookInfoProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, m_servers);
        bookInfoProperties.put(ConsumerConfig.GROUP_ID_CONFIG, m_bookGroupId);
        bookInfoProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, m_offsetResetConfig);
        bookInfoProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        bookInfoProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        bookInfoProperties.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());
        bookInfoProperties.put(JsonDeserializer.VALUE_DEFAULT_TYPE, BookStockInfo.class);
        bookInfoProperties.put("spring.json.use.type.headers", false);
        return new DefaultKafkaConsumerFactory<>(bookInfoProperties);
    }
}
