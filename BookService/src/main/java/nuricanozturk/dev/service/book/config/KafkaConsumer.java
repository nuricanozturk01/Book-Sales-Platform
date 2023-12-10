package nuricanozturk.dev.service.book.config;

import nuricanozturk.dev.service.book.config.listener.StockListener;
import nuricanozturk.dev.service.book.dal.BookRepositoryServiceHelper;
import nuricanozturk.dev.service.book.entity.Book;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer
{
    private final BookRepositoryServiceHelper m_serviceHelper;

    public KafkaConsumer(BookRepositoryServiceHelper serviceHelper)
    {
        m_serviceHelper = serviceHelper;
    }

    private void updateBookStatus(StockListener stockListener, Book book)
    {
        book.setBookStatus(stockListener.stock());
        m_serviceHelper.saveBook(book);
    }

    @KafkaListener(topics = "${spring.kafka.stock-topic-name}", groupId = "${spring.kafka.consumer.stock-group-id}")
    public void consumeStock(StockListener stockListener)
    {
        var bookOpt = m_serviceHelper.findBookById(stockListener.bookId());
        bookOpt.ifPresentOrElse(book -> updateBookStatus(stockListener, book), () -> System.err.println("Book not found"));
    }
}
