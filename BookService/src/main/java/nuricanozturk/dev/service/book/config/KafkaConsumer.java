package nuricanozturk.dev.service.book.config;

import nuricanozturk.dev.service.book.dal.BookRepositoryServiceHelper;
import nuricanozturk.dev.service.book.config.listener.BookResponseInfo;
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

    private void updateBookStatus(BookResponseInfo bookResponseInfo, Book book)
    {
        book.setBookStatus(bookResponseInfo.bookStatus());
        m_serviceHelper.saveBook(book);
    }

    @KafkaListener(topics = "${spring.kafka.stock-topic-name}", groupId = "${spring.kafka.consumer.stock-group-id}")
    public void consumeStock(BookResponseInfo bookResponseInfo)
    {
        var bookOpt = m_serviceHelper.findBookById(bookResponseInfo.bookId());
        bookOpt.ifPresentOrElse(book -> updateBookStatus(bookResponseInfo, book), System.err::println);
    }
}