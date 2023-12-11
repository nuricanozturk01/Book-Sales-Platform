package nuricanozturk.dev.service.book.dal;


import nuricanozturk.dev.service.book.entity.Book;
import nuricanozturk.dev.service.book.repository.IBookRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static nuricanozturk.dev.service.book.util.ConvertUtil.convert;


@Component
@Lazy
public class BookRepositoryServiceHelper
{
    private final IBookRepository m_bookRepository;


    public BookRepositoryServiceHelper(IBookRepository bookRepository)
    {
        m_bookRepository = bookRepository;
    }

    public Book saveBook(Book book)
    {
        book.setAuthorName(convert(book.getAuthorName()));
        book.setPublisherName(convert(book.getPublisherName()));
        book.setBookName(convert(book.getBookName()));
        return m_bookRepository.save(book);
    }

    public Iterable<Book> saveMultipleBook(List<Book> books)
    {
        books = books.stream().peek(book ->
        {
            book.setAuthorName(convert(book.getAuthorName()));
            book.setPublisherName(convert(book.getPublisherName()));
            book.setBookName(convert(book.getBookName()));

        }).toList();


        return m_bookRepository.saveAll(books);
    }

    public Iterable<Book> findAllBooks()
    {
        return m_bookRepository.findAll();
    }

    public Optional<Book> findBookById(UUID id)
    {
        return m_bookRepository.findById(id);
    }

    public void deleteBookById(UUID id)
    {
        m_bookRepository.deleteById(id);
    }

    public boolean existsBookById(UUID id)
    {
        return m_bookRepository.existsById(id);
    }

    public long countBooks()
    {
        return m_bookRepository.count();
    }

    public Iterable<Book> findAllBooksByBookIsbn(UUID isbn)
    {
        return m_bookRepository.findAllByBookIsbn(isbn);
    }

    public Optional<Book> findAllBooksByBookName(String name)
    {
        return m_bookRepository.findBooksByBookName(convert(name));
    }

    public Iterable<Book> findAllBooksByBookPageCount(int pageCount)
    {
        return m_bookRepository.findAllByBookPageCount(pageCount);
    }

    public Iterable<Book> findAllBooksByBookAuthorName(String authorName)
    {
        return m_bookRepository.findBooksByAuthorName(convert(authorName));
    }

    public Iterable<Book> findAllBooksByPublisherName(String publisherName)
    {
        return m_bookRepository.findBooksByPublisherName(convert(publisherName));
    }

    public Iterable<Book> findAllBooksByPrice(double price)
    {
        return m_bookRepository.findAllByPrice(price);
    }

    public Iterable<Book> findAllBooksByPriceBetween(double minPrice, double maxPrice)
    {
        return m_bookRepository.findAllByPriceBetween(minPrice, maxPrice);
    }

    public Iterable<Book> findAllBooksByPriceGreaterThanEqual(double minPrice)
    {
        return m_bookRepository.findAllByPriceGreaterThanEqual(minPrice);
    }

    public Iterable<Book> findAllBooksByPriceLessThanEqual(double maxPrice)
    {
        return m_bookRepository.findAllByPriceLessThanEqual(maxPrice);
    }
}
