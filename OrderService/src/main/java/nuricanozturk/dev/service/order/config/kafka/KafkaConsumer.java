package nuricanozturk.dev.service.order.config.kafka;

import nuricanozturk.dev.service.order.config.listenerdto.BookInfo;
import nuricanozturk.dev.service.order.config.listenerdto.BookStatus;
import nuricanozturk.dev.service.order.config.listenerdto.StockInfo;
import nuricanozturk.dev.service.order.config.listenerdto.UserInfo;
import nuricanozturk.dev.service.order.service.OrderService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer
{
    private final OrderService m_orderService;

    public KafkaConsumer(OrderService orderService)
    {
        m_orderService = orderService;
    }

    @KafkaListener(topics = "${spring.kafka.user-topic-name}", groupId = "${spring.kafka.consumer.user-group-id}", containerFactory = "configUserInfoKafkaListener")
    public void consumeUserInfo(UserInfo userInfo)
    {
        System.err.println("User info consumed: " + userInfo);
        switch (userInfo.operationStatus())
        {
            case CREATE -> m_orderService.saveUser(userInfo);
            case UPDATE -> m_orderService.updateUser(userInfo);
            case REMOVE -> m_orderService.removeUser(userInfo);

            default -> throw new UnsupportedOperationException("Operation is not supported");
        }
    }

    @KafkaListener(topics = "${spring.kafka.book-topic-name}", groupId = "${spring.kafka.consumer.book-group-id}", containerFactory = "configBookInfoKafkaListener")
    public void consumeBookInfo(BookInfo bookInfo)
    {
        System.err.println("Book info consumed: " + bookInfo);
        switch (bookInfo.bookStatus())
        {
            case AVAILABLE -> m_orderService.upsertBook(bookInfo);
            case FINISHED -> m_orderService.removeBook(bookInfo);
            default -> throw new UnsupportedOperationException("Operation is not supported");
        }
    }

    // if out of stock, remove book from db
    @KafkaListener(topics = "${spring.kafka.stock-topic-name}", groupId = "${spring.kafka.consumer.stock-group-id}", containerFactory = "configStockInfoKafkaListener")
    public void consumeStockInfo(StockInfo stockInfo)
    {
        if (stockInfo.bookStatus() == BookStatus.FINISHED)
        {
            m_orderService.removeBookByBookId(stockInfo.bookId());
            System.err.println(stockInfo.bookName() + " is out of stock. It is removed from db.");
        }
        else System.err.println(stockInfo.bookName() + " is available on stock.");
    }
}
