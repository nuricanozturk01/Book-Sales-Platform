package nuricanozturk.dev.service.order.config.kafka;

import nuricanozturk.dev.service.order.config.listenerdto.BookInfo;
import nuricanozturk.dev.service.order.config.listenerdto.StockInfo;
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
public class OrderStatusConsumerConfig
{
    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String m_servers;

    @Value("${spring.kafka.consumer.order-status-info-group-id}")
    private String m_bookGroupId;

    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String m_offsetResetConfig;


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, StockInfo> configOrderStatusInfoKafkaListener()
    {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, StockInfo>();
        factory.setConsumerFactory(orderStatusInfoConsumerConfig());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, StockInfo> orderStatusInfoConsumerConfig()
    {
        var props = new HashMap<String, Object>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, m_servers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, m_bookGroupId);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, m_offsetResetConfig);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, StockInfo.class);
        // props.put(JsonDeserializer.TRUSTED_PACKAGES, "nuricanozturk.dev.data.usermanagement.dto.UserInfo");
        props.put("spring.json.use.type.headers", false);
        return new DefaultKafkaConsumerFactory<>(props);
    }
}
