package nuricanozturk.dev.service.order.config.kafka;

import nuricanozturk.dev.service.order.config.producerDTO.OrderStockInfo;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderKafkaProducer
{
    @Value("${spring.kafka.log-topic-name}")
    private String m_logTopic;
    private final NewTopic m_OrderTopic;
    private final KafkaTemplate<String, OrderStockInfo> m_kafkaProducer;
    private final KafkaTemplate<String, String> m_logKafkaProducer;

    public OrderKafkaProducer(NewTopic orderTopic,
                              KafkaTemplate<String, OrderStockInfo> kafkaProducer,
                              KafkaTemplate<String, String> logKafkaProducer)
    {
        m_OrderTopic = orderTopic;
        m_kafkaProducer = kafkaProducer;
        m_logKafkaProducer = logKafkaProducer;
    }

    public void publishOrderInfo(OrderStockInfo orderStockInfo)
    {
        var message = MessageBuilder
                .withPayload(orderStockInfo)
                .setHeader(KafkaHeaders.TOPIC, m_OrderTopic.name())
                .build();

        m_kafkaProducer.send(message);
    }

    public void sendLog(String log)
    {
        m_logKafkaProducer.send(m_logTopic, log);
    }
}
