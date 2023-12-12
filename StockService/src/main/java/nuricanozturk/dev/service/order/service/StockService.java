package nuricanozturk.dev.service.order.service;

import nuricanozturk.dev.service.order.config.StockKafkaProducer;
import nuricanozturk.dev.service.order.config.listenerdto.BookStockInfo;
import nuricanozturk.dev.service.order.config.listenerdto.OrderStockInfo;
import nuricanozturk.dev.service.order.config.producerdto.StockInfo;
import nuricanozturk.dev.service.order.dto.BookStatus;
import nuricanozturk.dev.service.order.entity.Stock;
import nuricanozturk.dev.service.order.repository.IStockRepository;
import org.springframework.stereotype.Service;

@Service
public class StockService
{
    private final IStockRepository m_stockRepository;
    private final StockKafkaProducer m_stockKafkaProducer;

    public StockService(IStockRepository stockRepository, StockKafkaProducer stockKafkaProducer)
    {
        m_stockRepository = stockRepository;
        m_stockKafkaProducer = stockKafkaProducer;
    }

    public void createBookStock(BookStockInfo bookInfo)
    {
        var book = new Stock(bookInfo.bookId(), bookInfo.bookName(), bookInfo.stock());
        m_stockRepository.save(book);
    }

    public void checkStockAndTryPayment(OrderStockInfo orderStockInfo)
    {
        var stock = m_stockRepository.findByBookId(orderStockInfo.bookId());

        if (stock.isEmpty() || stock.get().getStock() <= 0)
        {
            System.out.println("no stock!");
            failPayment(orderStockInfo);
            return;
        }
        System.out.println("on stock!");
        // route to payment service
        stock.get().reduceStock();
        m_stockRepository.save(stock.get());
        preparePayment(orderStockInfo);
    }

    private void preparePayment(OrderStockInfo orderStockInfo)
    {
        var stockInfo = new StockInfo(orderStockInfo.userId(), orderStockInfo.bookId(), orderStockInfo.bookName(), orderStockInfo.price(),
                BookStatus.AVAILABLE, "available on stock!");
        m_stockKafkaProducer.publishStockInfo(stockInfo);
    }

    // Send message to user service to fail order and send message to book service to finish book
    private void failPayment(OrderStockInfo orderStockInfo)
    {
        var stockInfo = new StockInfo(orderStockInfo.userId(), orderStockInfo.bookId(), orderStockInfo.bookName(), orderStockInfo.price(),
                BookStatus.FINISHED, "finish on stock!");

        m_stockKafkaProducer.publishStockOrderInfo(stockInfo);
        m_stockKafkaProducer.publishBookStatusInfo(stockInfo);
    }
}
