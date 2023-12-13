package nuricanozturk.dev.service.notification.config.kafka.consumer;

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
public class PaymentConsumerConfig
{
    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String m_servers;

    @Value("${spring.kafka.consumer.payment-group-id}")
    private String m_bookGroupId;

    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String m_offsetResetConfig;


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PaymentInfo> configPaymentInfoKafkaListener()
    {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, PaymentInfo>();
        factory.setConsumerFactory(paymentInfoConsumerConfig());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, PaymentInfo> paymentInfoConsumerConfig()
    {
        var paymentProperties = new HashMap<String, Object>();
        paymentProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, m_servers);
        paymentProperties.put(ConsumerConfig.GROUP_ID_CONFIG, m_bookGroupId);
        paymentProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, m_offsetResetConfig);
        paymentProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        paymentProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        paymentProperties.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());
        paymentProperties.put(JsonDeserializer.VALUE_DEFAULT_TYPE, PaymentInfo.class);
        paymentProperties.put("spring.json.use.type.headers", false);
        return new DefaultKafkaConsumerFactory<>(paymentProperties);
    }
}
