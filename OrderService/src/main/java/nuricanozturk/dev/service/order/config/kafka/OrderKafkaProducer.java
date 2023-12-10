package nuricanozturk.dev.service.order.config.kafka;

import nuricanozturk.dev.service.order.config.producerDTO.OrderStockInfo;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderKafkaProducer
{
    private final NewTopic m_OrderTopic;
    private final KafkaTemplate<String, OrderStockInfo> m_kafkaProducer;

    public OrderKafkaProducer(NewTopic orderTopic, KafkaTemplate<String, OrderStockInfo> kafkaProducer)
    {
        m_OrderTopic = orderTopic;
        m_kafkaProducer = kafkaProducer;
    }

    public void publishOrderInfo(OrderStockInfo orderStockInfo)
    {
        var message = MessageBuilder
                .withPayload(orderStockInfo)
                .setHeader(KafkaHeaders.TOPIC, m_OrderTopic.name())
                .build();

        m_kafkaProducer.send(message);
    }
}
