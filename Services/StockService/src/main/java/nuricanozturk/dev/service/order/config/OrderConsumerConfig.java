package nuricanozturk.dev.service.order.config;

import nuricanozturk.dev.service.order.config.listenerdto.OrderStockInfo;
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
public class OrderConsumerConfig
{
    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String m_servers;

    @Value("${spring.kafka.consumer.order-info-group-id}")
    private String m_orderGroupId;

    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String m_offsetResetConfig;


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, OrderStockInfo> configOrderInfoKafkaListener()
    {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, OrderStockInfo>();
        factory.setConsumerFactory(orderInfoConsumerConfig());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, OrderStockInfo> orderInfoConsumerConfig()
    {
        var orderInfoProperties = new HashMap<String, Object>();
        orderInfoProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, m_servers);
        orderInfoProperties.put(ConsumerConfig.GROUP_ID_CONFIG, m_orderGroupId);
        orderInfoProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, m_offsetResetConfig);
        orderInfoProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        orderInfoProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        orderInfoProperties.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());
        orderInfoProperties.put(JsonDeserializer.VALUE_DEFAULT_TYPE, OrderStockInfo.class);
        orderInfoProperties.put("spring.json.use.type.headers", false);
        return new DefaultKafkaConsumerFactory<>(orderInfoProperties);
    }
}