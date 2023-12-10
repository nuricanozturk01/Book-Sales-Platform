package nuricanozturk.dev.service.order.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "stock")
public class Stock
{
    @Id
    private String id;
    private UUID bookId;
    private String bookName;
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

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
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
