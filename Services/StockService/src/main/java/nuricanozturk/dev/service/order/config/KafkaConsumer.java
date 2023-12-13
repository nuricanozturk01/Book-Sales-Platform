package nuricanozturk.dev.service.order.config;

import nuricanozturk.dev.service.order.config.listenerdto.BookStockInfo;
import nuricanozturk.dev.service.order.config.listenerdto.OrderStockInfo;
import nuricanozturk.dev.service.order.service.StockService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer
{
    private final StockService m_stockService;

    public KafkaConsumer(StockService stockService)
    {
        m_stockService = stockService;
    }


    @KafkaListener(topics = "${spring.kafka.order-info-topic-name}", groupId = "${spring.kafka.consumer.order-info-group-id}", containerFactory = "configOrderInfoKafkaListener")
    public void consumeOrderInfo(OrderStockInfo orderStockInfo)
    {
        System.err.println("Order info consumed: " + orderStockInfo);
        m_stockService.checkStockAndTryPayment(orderStockInfo);
    }

    @KafkaListener(topics = "${spring.kafka.book-topic-name}", groupId = "${spring.kafka.consumer.book-group-id}", containerFactory = "configBookInfoKafkaListener")
    public void consumeBookInfo(BookStockInfo bookInfo)
    {
        m_stockService.createBookStock(bookInfo);
    }
}
