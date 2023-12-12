package nuricanozturk.dev.service.order.config;

import nuricanozturk.dev.service.order.config.producerdto.StockInfo;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static nuricanozturk.dev.service.order.util.BeanName.BOOK_STATUS_TOPIC;
import static nuricanozturk.dev.service.order.util.BeanName.ORDER_STATUS_TOPIC;

@Service
public class StockKafkaProducer
{
    private final NewTopic m_stockTopic;
    private final NewTopic m_stockOrderTopic;

    private final NewTopic m_bookStatusTopic;
    private final KafkaTemplate<String, StockInfo> m_kafkaProducer;

    public StockKafkaProducer(@Qualifier(ORDER_STATUS_TOPIC) NewTopic stockOrderTopic,
                              @Qualifier(BOOK_STATUS_TOPIC) NewTopic bookStatusTopic,
                              NewTopic stockTopic,
                              KafkaTemplate<String, StockInfo> kafkaProducer)
    {
        m_stockTopic = stockTopic;
        m_stockOrderTopic = stockOrderTopic;
        m_bookStatusTopic = bookStatusTopic;
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

    public void publishStockOrderInfo(StockInfo stockInfo)
    {
        var message = MessageBuilder
                .withPayload(stockInfo)
                .setHeader(KafkaHeaders.TOPIC, m_stockOrderTopic.name())
                .build();

        m_kafkaProducer.send(message);
    }

    public void publishBookStatusInfo(StockInfo stockInfo)
    {
        var message = MessageBuilder
                .withPayload(stockInfo)
                .setHeader(KafkaHeaders.TOPIC, m_bookStatusTopic.name())
                .build();

        m_kafkaProducer.send(message);
    }
}