package nuricanozturk.dev.service.order.entity;

import jakarta.persistence.*;
import nuricanozturk.dev.service.order.config.listenerdto.BookStatus;

import java.util.UUID;

@Entity
@Table(name = "book")
public class Book
{
    @Id
    @Column(name = "book_id")
    private UUID bookId;

    @Column(name = "book_name", nullable = false)
    private String bookName;
    @Column(name = "isbn", nullable = false, unique = true)
    private UUID bookIsbn;

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

    public Book(UUID bookId, String bookName, UUID bookIsbn, double price, BookStatus bookStatus)
    {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookIsbn = bookIsbn;
        this.price = price;
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
}
