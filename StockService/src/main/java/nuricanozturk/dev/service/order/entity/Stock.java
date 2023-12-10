package nuricanozturk.dev.service.order.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "stock")
public class Stock
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "stock_id")
    private UUID stockId;

    @Column(name = "book_id", nullable = false)
    private UUID bookId;

    @Column(name = "book_name", nullable = false)
    private String bookName;


    @Column(name = "stock", nullable = false)
    private int stock;

    public Stock()
    {
    }

    public Stock(UUID bookId, String bookName, int stock)
    {
        this.bookId = bookId;
        this.bookName = bookName;
        this.stock = stock;
    }

    public UUID getStockId()
    {
        return stockId;
    }

    public void setStockId(UUID stockId)
    {
        this.stockId = stockId;
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

    public int getStock()
    {
        return stock;
    }

    public void setStock(int stock)
    {
        this.stock = stock;
    }
}
