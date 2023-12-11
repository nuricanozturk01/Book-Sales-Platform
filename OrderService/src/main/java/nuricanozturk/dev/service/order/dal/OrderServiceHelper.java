package nuricanozturk.dev.service.order.dal;

import nuricanozturk.dev.service.order.entity.Book;
import nuricanozturk.dev.service.order.entity.User;
import nuricanozturk.dev.service.order.repository.IBookRepository;
import nuricanozturk.dev.service.order.repository.IOrderRepository;
import nuricanozturk.dev.service.order.repository.IUserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

import static callofproject.dev.library.exception.util.CopDataUtil.doForRepository;

@Component
public class OrderServiceHelper
{
    private final IUserRepository m_userRepository;

    private final IOrderRepository m_orderRepository;

    private final IBookRepository m_bookRepository;

    public OrderServiceHelper(IUserRepository userRepository, IOrderRepository orderRepository, IBookRepository bookRepository)
    {
        m_userRepository = userRepository;
        m_orderRepository = orderRepository;
        m_bookRepository = bookRepository;
    }

    public User saveUser(User userInfo)
    {
        return doForRepository(() -> m_userRepository.save(userInfo), "User could not be saved!");
    }

    public Book saveBook(Book book)
    {
        return doForRepository(() -> m_bookRepository.save(book), "Book could not be saved!");
    }

    public Optional<Book> findBookById(UUID uuid)
    {
        return doForRepository(() -> m_bookRepository.findById(uuid), "Book could not be found!");
    }

    public void removeBook(Book book)
    {
        doForRepository(() -> m_bookRepository.delete(book), "Book could not be removed!");
    }

    public Optional<User> findUserById(UUID userId)
    {
        return doForRepository(() -> m_userRepository.findById(userId), "User could not be found!");
    }

    public void removeUser(User user)
    {
        doForRepository(() -> m_userRepository.delete(user), "User could not be removed!");
    }
}
