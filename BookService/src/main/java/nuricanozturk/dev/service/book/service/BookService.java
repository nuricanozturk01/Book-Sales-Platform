package nuricanozturk.dev.service.book.service;


import nuricanozturk.dev.service.book.config.ProducerKafka;
import nuricanozturk.dev.service.book.dal.BookRepositoryServiceHelper;
import nuricanozturk.dev.service.book.dto.BookInfo;
import nuricanozturk.dev.service.book.dto.BookSaveDTO;
import nuricanozturk.dev.service.book.dto.BookViewDTO;
import nuricanozturk.dev.service.book.dto.BooksViewDTO;
import nuricanozturk.dev.service.book.mapper.IBookMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static callofproject.dev.library.exception.util.CopDataUtil.doForDataService;
import static callofproject.dev.util.stream.StreamUtil.toList;

@Service
@Lazy
public class BookService
{
    private final BookRepositoryServiceHelper m_serviceHelper;
    private final IBookMapper m_bookMapper;
    private final ProducerKafka m_producerKafka;


    public BookService(BookRepositoryServiceHelper serviceHelper, IBookMapper bookMapper, ProducerKafka producerKafka)
    {
        m_serviceHelper = serviceHelper;
        m_bookMapper = bookMapper;
        m_producerKafka = producerKafka;
    }


    public BooksViewDTO saveMultipleBook(List<BookSaveDTO> books)
    {
        return m_bookMapper.toBooksViewDTO(toList(m_serviceHelper.saveMultipleBook(m_bookMapper.toBookList(books)), m_bookMapper::toBookDTO));
    }

    public BookViewDTO saveBook(BookSaveDTO bookSaveDTO)
    {
        var book = m_bookMapper.toBook(bookSaveDTO);

        var savedBook = doForDataService(() -> m_serviceHelper.saveBook(book), "BookService.saveBook");

        m_producerKafka.sendBookInfo(new BookInfo(savedBook.getBookId(), savedBook.getBookName(), savedBook.getBookIsbn(),
                savedBook.getPrice(), savedBook.getBookStatus(), bookSaveDTO.stock()));

        return m_bookMapper.toBookDTO(savedBook);
    }

    public BooksViewDTO getAllBooks()
    {
        return doForDataService(() -> m_bookMapper.toBooksViewDTO(toList(m_serviceHelper.findAllBooks(), m_bookMapper::toBookDTO)), "BookService.getAllBooks");
    }

    public BookViewDTO getBookByBookName(String bookName)
    {
        var book = doForDataService(() -> m_serviceHelper.findAllBooksByBookName(bookName), "BookService.getAllBooksByBookName");

        return m_bookMapper.toBookDTO(book.orElse(null));
    }

    public BooksViewDTO getAllBooksByBookIsbn(UUID bookIsbn)
    {
        var books = doForDataService(() -> m_serviceHelper.findAllBooksByBookIsbn(bookIsbn), "BookService.getAllBooksByBookIsbn");

        return m_bookMapper.toBooksViewDTO(toList(books, m_bookMapper::toBookDTO));
    }

    public BooksViewDTO getAllByAuthorName(String authorName)
    {
        var books = doForDataService(() -> m_serviceHelper.findAllBooksByBookAuthorName(authorName), "BookService.getAllByAuthorName");

        return m_bookMapper.toBooksViewDTO(toList(books, m_bookMapper::toBookDTO));
    }

    public BooksViewDTO getAllByBookPublisherName(String publisherName)
    {
        var books = doForDataService(() -> m_serviceHelper.findAllBooksByPublisherName(publisherName), "BookService.getAllByBookPublisherName");

        return m_bookMapper.toBooksViewDTO(toList(books, m_bookMapper::toBookDTO));
    }


    public BooksViewDTO getAllByBookPrice(double price)
    {
        var books = doForDataService(() -> m_serviceHelper.findAllBooksByPrice(price), "BookService.getAllByBookPrice");

        return m_bookMapper.toBooksViewDTO(toList(books, m_bookMapper::toBookDTO));
    }


    public BooksViewDTO getAllByBookPriceBetween(double minPrice, double maxPrice)
    {
        var books = doForDataService(() -> m_serviceHelper.findAllBooksByPriceBetween(minPrice, maxPrice), "BookService.getAllByBookPriceBetween");

        return m_bookMapper.toBooksViewDTO(toList(books, m_bookMapper::toBookDTO));
    }

    public BooksViewDTO getAllByBookPriceGreaterThanEqual(double minPrice)
    {
        var books = doForDataService(() -> m_serviceHelper.findAllBooksByPriceGreaterThanEqual(minPrice), "BookService.getAllByBookPriceGreaterThanEqual");

        return m_bookMapper.toBooksViewDTO(toList(books, m_bookMapper::toBookDTO));
    }

    public BooksViewDTO getAllByBookPriceLessThanEqual(double maxPrice)
    {
        var books = doForDataService(() -> m_serviceHelper.findAllBooksByPriceLessThanEqual(maxPrice), "BookService.getAllByBookPriceLessThanEqual");

        return m_bookMapper.toBooksViewDTO(toList(books, m_bookMapper::toBookDTO));
    }

    public boolean removeBook(UUID bookId)
    {
        doForDataService(() -> m_serviceHelper.deleteBookById(bookId), "BookService.removeBook");
        return true;
    }
}
