package nuricanozturk.dev.service.order.service;

import nuricanozturk.dev.service.order.config.KafkaStockProducer;
import nuricanozturk.dev.service.order.config.listenerdto.BookStockInfo;
import nuricanozturk.dev.service.order.config.listenerdto.OrderInfo;
import nuricanozturk.dev.service.order.config.producerdto.StockInfo;
import nuricanozturk.dev.service.order.dto.StockStatus;
import nuricanozturk.dev.service.order.entity.Stock;
import nuricanozturk.dev.service.order.repository.IStockRepository;
import org.springframework.stereotype.Service;

@Service
public class StockService
{
    private final IStockRepository m_stockRepository;
    private final KafkaStockProducer m_kafkaStockProducer;

    public StockService(IStockRepository stockRepository, KafkaStockProducer kafkaStockProducer)
    {
        m_stockRepository = stockRepository;
        m_kafkaStockProducer = kafkaStockProducer;
    }

    public void createBookStock(BookStockInfo bookInfo)
    {
        var book = new Stock(bookInfo.bookId(), bookInfo.bookName(), bookInfo.stock());
        m_stockRepository.save(book);
    }

    public void checkStockAndCreateOrderResponse(OrderInfo orderInfo)
    {
        var stock = m_stockRepository.findByBookId(orderInfo.bookId());

        if (stock.isEmpty())
            return;

        if (stock.get().getStock() > 0)
        {
            stock.get().setStock(stock.get().getStock() - 1);
            m_stockRepository.save(stock.get());
            prepareOrderResponse(orderInfo, true);
        }
        else prepareOrderResponse(orderInfo, false);
    }

    private void prepareOrderResponse(OrderInfo orderInfo, boolean isAvailable)
    {
        if (isAvailable)
        {
            var stockInfo = new StockInfo(StockStatus.DECREASE);
            m_kafkaStockProducer.publishStockInfo(stockInfo);
        }
        else
        {
            var stockInfo = new StockInfo(StockStatus.FINISHED);
            m_kafkaStockProducer.publishStockInfo(stockInfo);
        }
    }
}
