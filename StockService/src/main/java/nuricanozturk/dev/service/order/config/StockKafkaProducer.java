package nuricanozturk.dev.service.order.config;

import nuricanozturk.dev.service.order.config.producerdto.BookResponseInfo;
import nuricanozturk.dev.service.order.config.producerdto.OrderResponseInfo;
import nuricanozturk.dev.service.order.config.producerdto.StockInfo;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class StockKafkaProducer
{
    private final NewTopic m_stockTopic;
    private final NewTopic m_bookStockTopic;

    private final NewTopic m_orderTopic;
    private final KafkaTemplate<String, StockInfo> m_kafkaProducer;
    private final KafkaTemplate<String, BookResponseInfo> m_bookKafkaProducer;
    private final KafkaTemplate<String, OrderResponseInfo> m_orderKafkaProducer;


    public StockKafkaProducer(@Qualifier("book-stock-topic") NewTopic bookStockTopic,
                              @Qualifier("stock-info-topic") NewTopic stockTopic,
                              @Qualifier("order-stock-topic") NewTopic orderTopic,
                              KafkaTemplate<String, StockInfo> kafkaProducer,
                              KafkaTemplate<String, BookResponseInfo> bookKafkaProducer,
                              KafkaTemplate<String, OrderResponseInfo> orderKafkaProducer)
    {
        m_stockTopic = stockTopic;
        m_bookStockTopic = bookStockTopic;
        m_orderTopic = orderTopic;
        m_kafkaProducer = kafkaProducer;
        m_bookKafkaProducer = bookKafkaProducer;
        m_orderKafkaProducer = orderKafkaProducer;
    }

    public void publishStockInfo(StockInfo stockInfo)
    {
        var message = MessageBuilder
                .withPayload(stockInfo)
                .setHeader(KafkaHeaders.TOPIC, m_stockTopic.name())
                .build();

        m_kafkaProducer.send(message);
    }

    public void publishBookInfo(BookResponseInfo bookInfo)
    {
        var message = MessageBuilder
                .withPayload(bookInfo)
                .setHeader(KafkaHeaders.TOPIC, m_bookStockTopic.name())
                .build();

        m_bookKafkaProducer.send(message);
    }

    public void publishOrderResponseInfo(OrderResponseInfo orderResponseInfo)
    {
        var message = MessageBuilder
                .withPayload(orderResponseInfo)
                .setHeader(KafkaHeaders.TOPIC, m_orderTopic.name())
                .build();

        m_orderKafkaProducer.send(message);
    }
}