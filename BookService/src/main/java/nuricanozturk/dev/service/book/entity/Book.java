package nuricanozturk.dev.service.book.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "book")
public class Book
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "book_id")
    private UUID bookId;
    @Column(name = "book_name", nullable = false, length = 100, unique = true)
    private String bookName;
    @Column(name = "isbn", nullable = false, unique = true)
    private UUID bookIsbn;
    @Column(name = "description", nullable = false, length = 1000)
    private String bookDescription;
    @Column(name = "page_count", nullable = false)
    private int bookPageCount;
    @Column(name = "publisher_name", nullable = false)
    private String publisherName;
    @Column(name = "author_name", nullable = false)
    private String authorName;
    @Column(name = "price", nullable = false)
    private double price;
    @Enumerated(EnumType.STRING)
    @Column(name = "book_status", nullable = false)
    private BookStatus bookStatus;


    public Book()
    {
        bookStatus = BookStatus.AVAILABLE;
        bookIsbn = UUID.randomUUID();
    }

    public Book(String bookName, String bookDescription, int bookPageCount, String publisherName, String authorName, BookStatus bookStatus, double price)
    {
        this.price = price;
        this.bookStatus = bookStatus;
        this.bookName = bookName;
        this.bookDescription = bookDescription;
        this.bookPageCount = bookPageCount;
        this.publisherName = publisherName;
        this.authorName = authorName;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public BookStatus getBookStatus()
    {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus)
    {
        this.bookStatus = bookStatus;
    }

    public UUID getBookId()
    {
        return bookId;
    }

    public void setBookId(UUID bookId)
    {
        this.bookId = bookId;
    }

    public String getBookName()
    {
        return bookName;
    }

    public void setBookName(String bookName)
    {
        this.bookName = bookName;
    }

    public UUID getBookIsbn()
    {
        return bookIsbn;
    }

    public void setBookIsbn(UUID bookIsbn)
    {
        this.bookIsbn = bookIsbn;
    }

    public String getBookDescription()
    {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription)
    {
        this.bookDescription = bookDescription;
    }

    public int getBookPageCount()
    {
        return bookPageCount;
    }

    public void setBookPageCount(int bookPageCount)
    {
        this.bookPageCount = bookPageCount;
    }

    public String getPublisherName()
    {
        return publisherName;
    }

    public void setPublisherName(String publisherName)
    {
        this.publisherName = publisherName;
    }

    public String getAuthorName()
    {
        return authorName;
    }

    public void setAuthorName(String authorName)
    {
        this.authorName = authorName;
    }
}
