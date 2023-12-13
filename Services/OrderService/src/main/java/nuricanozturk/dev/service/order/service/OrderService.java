package nuricanozturk.dev.service.order.service;

import callofproject.dev.library.exception.service.DataServiceException;
import nuricanozturk.dev.service.order.config.kafka.OrderKafkaProducer;
import nuricanozturk.dev.service.order.config.listenerdto.BookInfo;
import nuricanozturk.dev.service.order.config.listenerdto.UserInfo;
import nuricanozturk.dev.service.order.config.producerDTO.OrderStockInfo;
import nuricanozturk.dev.service.order.dal.OrderServiceHelper;
import nuricanozturk.dev.service.order.entity.Order;
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

    public void saveUser(UserInfo userInfo)
    {
        var user = m_userMapper.toUser(userInfo);
        doForDataService(() -> m_orderServiceHelper.saveUser(user), "User could not be saved!");
    }

    public void updateUser(UserInfo userInfo)
    {
        var user = m_orderServiceHelper.findUserById(m_userMapper.toUser(userInfo).getUserId());

        if (user.isEmpty())
            throw new DataServiceException("User could not be found!");
        user.get().setOperationStatus(userInfo.operationStatus());
        doForDataService(() -> m_orderServiceHelper.saveUser(user.get()), "User could not be saved!");
    }

    public void upsertBook(BookInfo bookInfo)
    {
        var book = m_bookMapper.toBook(bookInfo);
        doForDataService(() -> m_orderServiceHelper.saveBook(book), "User could not be saved!");
        m_orderKafkaProducer.sendLog(String.format("ORDER-SERVICE: Book with id [%s], name: [%s], stock: [%d], price: $[%.2f] has been added",
                book.getBookId().toString(), book.getBookName(), bookInfo.stock(), bookInfo.price()));
    }


    public void removeBook(BookInfo bookInfo)
    {
        var book = doForDataService(() -> m_orderServiceHelper.findBookById(bookInfo.bookId()), "OrderService::removeBook");

        if (book.isEmpty())
            throw new DataServiceException("Book could not be found!");

        m_orderServiceHelper.removeBook(book.get());

        m_orderKafkaProducer.sendLog(String.format("ORDER-SERVICE: Book with id [%s], name: [%s], stock: [%d], price: $[%.2f] has been removed",
                book.get().getBookId().toString(), book.get().getBookName(), 0, book.get().getPrice()));
    }

    public void removeBookByBookId(UUID bookId)
    {
        var book = doForDataService(() -> m_orderServiceHelper.findBookById(bookId), "OrderService::removeBook");

        if (book.isEmpty())
            throw new DataServiceException("Book could not be found!");
        m_orderKafkaProducer.sendLog(String.format("ORDER-SERVICE: Book with id [%s], name: [%s], stock: [%d], price: $[%.2f] has been removed",
                book.get().getBookId().toString(), book.get().getBookName(), 0, book.get().getPrice()));
        m_orderServiceHelper.removeBook(book.get());
    }

    // publish orderStock info for stock service
    public void buyBook(UUID bookId, UUID userId)
    {
        var user = doForDataService(() -> m_orderServiceHelper.findUserById(userId), "OrderService::buyUser");
        var book = doForDataService(() -> m_orderServiceHelper.findBookById(bookId), "OrderService::buyBook");

        if (book.isEmpty())
            throw new DataServiceException("Book could not be found!");

        if (user.isEmpty())
            throw new DataServiceException("User could not be found!");

        var savedOrder = m_orderServiceHelper.saveOrder(new Order(user.get(), book.get().getBookId()));

        // Publish order info for stock service
        m_orderKafkaProducer.publishOrderInfo(new OrderStockInfo(savedOrder.getOrderId(), userId, bookId, book.get().getBookName(), book.get().getBookStatus(), book.get().getPrice()));
        // Log order info
        m_orderKafkaProducer.sendLog(String.format("ORDER-SERVICE - [ORDER]: user id: [%s] book id [%s], name: [%s], count: [%d], price: $[%.2f] has been ordered",
                userId, bookId, book.get().getBookName(), 1, book.get().getPrice()));
    }

    public void removeUser(UserInfo userInfo)
    {
        var user = doForDataService(() -> m_orderServiceHelper.findUserById(userInfo.userId()), "OrderService::removeUser");

        if (user.isEmpty())
            throw new DataServiceException("User could not be found!");

        m_orderServiceHelper.removeUser(user.get());
    }
}
