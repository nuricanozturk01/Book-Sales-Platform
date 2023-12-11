package nuricanozturk.dev.service.payment.config.consumer;

import nuricanozturk.dev.service.payment.config.consumer.dto.UserInfo;
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
public class UserConsumerConfig
{
    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String m_servers;

    @Value("${spring.kafka.consumer.user-group-id}")
    private String m_userGroupId;

    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String m_offsetResetConfig;


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, UserInfo> configUserInfoKafkaListener()
    {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, UserInfo>();
        factory.setConsumerFactory(userInfoConsumerConfig());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, UserInfo> userInfoConsumerConfig()
    {
        var orderInfoProperties = new HashMap<String, Object>();
        orderInfoProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, m_servers);
        orderInfoProperties.put(ConsumerConfig.GROUP_ID_CONFIG, m_userGroupId);
        orderInfoProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, m_offsetResetConfig);
        orderInfoProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        orderInfoProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        orderInfoProperties.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());
        orderInfoProperties.put(JsonDeserializer.VALUE_DEFAULT_TYPE, UserInfo.class);
        orderInfoProperties.put("spring.json.use.type.headers", false);
        return new DefaultKafkaConsumerFactory<>(orderInfoProperties);
    }
}