package nuricanozturk.dev.service.order.config;

import nuricanozturk.dev.service.order.config.producerdto.StockInfo;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaStockProducer
{
    private final NewTopic m_stockTopic;
    private final KafkaTemplate<String, StockInfo> m_kafkaProducer;

    public KafkaStockProducer(NewTopic stockTopic, KafkaTemplate<String, StockInfo> kafkaProducer)
    {
        m_stockTopic = stockTopic;
        m_kafkaProducer = kafkaProducer;
    }

    public void publishStockInfo(StockInfo stockInfo)
    {
        var message = MessageBuilder
                .withPayload(stockInfo)
                .setHeader(KafkaHeaders.TOPIC, m_stockTopic.name())
                .build();

        m_kafkaProducer.send(message);
    }
}
