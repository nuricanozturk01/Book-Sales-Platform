package nuricanozturk.dev.service.order.service;

import callofproject.dev.library.exception.service.DataServiceException;
import nuricanozturk.dev.service.order.config.kafka.OrderKafkaProducer;
import nuricanozturk.dev.service.order.config.listenerdto.BookInfo;
import nuricanozturk.dev.service.order.config.listenerdto.UserInfo;
import nuricanozturk.dev.service.order.config.producerDTO.OrderStockInfo;
import nuricanozturk.dev.service.order.dal.OrderServiceHelper;
import nuricanozturk.dev.service.order.mapper.IBookMapper;
import nuricanozturk.dev.service.order.mapper.IUserMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static callofproject.dev.library.exception.util.CopDataUtil.doForDataService;

@Service
public class OrderService
{
    private final OrderServiceHelper m_orderServiceHelper;
    private final OrderKafkaProducer m_orderKafkaProducer;
    private final IUserMapper m_userMapper;
    private final IBookMapper m_bookMapper;

    public OrderService(OrderServiceHelper orderServiceHelper, OrderKafkaProducer orderKafkaProducer, IUserMapper userMapper, IBookMapper bookMapper)
    {
        m_orderServiceHelper = orderServiceHelper;
        m_orderKafkaProducer = orderKafkaProducer;
        m_userMapper = userMapper;
        m_bookMapper = bookMapper;
    }

    public void upsertUserUser(UserInfo userInfo)
    {
        var user = m_userMapper.toUser(userInfo);
        doForDataService(() -> m_orderServiceHelper.saveUser(user), "User could not be saved!");
    }

    public void upsertBook(BookInfo bookInfo)
    {
        var book = m_bookMapper.toBook(bookInfo);
        doForDataService(() -> m_orderServiceHelper.saveBook(book), "User could not be saved!");
    }


    public void removeBook(BookInfo bookInfo)
    {
        var book = doForDataService(() -> m_orderServiceHelper.findBookById(bookInfo.bookId()), "OrderService::removeBook");

        if (book.isEmpty())
            throw new DataServiceException("Book could not be found!");

        m_orderServiceHelper.removeBook(book.get());
    }

    public void buyBook(UUID bookId, UUID userId)
    {
        var user = doForDataService(() -> m_orderServiceHelper.findUserById(userId), "OrderService::buyUser");
        var book = doForDataService(() -> m_orderServiceHelper.findBookById(bookId), "OrderService::buyBook");

        if (book.isEmpty())
            throw new DataServiceException("Book could not be found!");

        if (user.isEmpty())
            throw new DataServiceException("User could not be found!");

        // Publish order info for stock service
        m_orderKafkaProducer.publishOrderInfo(new OrderStockInfo());
    }
}
