package nuricanozturk.dev.service.book.config;

import nuricanozturk.dev.service.book.config.listener.StockInfo;
import nuricanozturk.dev.service.book.service.BookService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer
{
    private final BookService m_serviceHelper;

    public KafkaConsumer(BookService serviceHelper)
    {
        m_serviceHelper = serviceHelper;
    }

    @KafkaListener(topics = "${spring-kafka-book-status-topic-name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeStock(StockInfo stockInfo)
    {
        m_serviceHelper.removeBook(stockInfo);
    }
}