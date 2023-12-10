package nuricanozturk.dev.service.book.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.UUID;

import static nuricanozturk.dev.service.book.util.ConvertUtil.convertInverse;

public final class BookViewDTO
{
    @JsonProperty("book_name")
    private final String bookName;
    @JsonProperty("book_isbn")
    private final UUID bookIsbn;
    @JsonProperty("book_description")
    private final String bookDescription;
    @JsonProperty("book_page_count")
    private final int bookPageCount;
    @JsonProperty("publisher_name")
    private final String publisherName;
    @JsonProperty("author_name")
    private final String authorName;
    private final double price;

    public BookViewDTO(
            String bookName,
            UUID bookIsbn,
            String bookDescription,
            int bookPageCount,
            String publisherName,
            String authorName,
            double price
    )
    {
        this.bookName = convertInverse(bookName);
        this.bookIsbn = bookIsbn;
        this.bookDescription = bookDescription;
        this.bookPageCount = bookPageCount;
        this.publisherName = convertInverse(publisherName);
        this.authorName = convertInverse(authorName);
        this.price = price;
    }

    @JsonProperty("book_name")
    public String bookName()
    {
        return bookName;
    }

    @JsonProperty("book_isbn")
    public UUID bookIsbn()
    {
        return bookIsbn;
    }

    @JsonProperty("book_description")
    public String bookDescription()
    {
        return bookDescription;
    }

    @JsonProperty("book_page_count")
    public int bookPageCount()
    {
        return bookPageCount;
    }

    @JsonProperty("publisher_name")
    public String publisherName()
    {
        return publisherName;
    }

    @JsonProperty("author_name")
    public String authorName()
    {
        return authorName;
    }

    public double price()
    {
        return price;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (BookViewDTO) obj;
        return Objects.equals(this.bookName, that.bookName) &&
                Objects.equals(this.bookIsbn, that.bookIsbn) &&
                Objects.equals(this.bookDescription, that.bookDescription) &&
                this.bookPageCount == that.bookPageCount &&
                Objects.equals(this.publisherName, that.publisherName) &&
                Objects.equals(this.authorName, that.authorName) &&
                Double.doubleToLongBits(this.price) == Double.doubleToLongBits(that.price);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(bookName, bookIsbn, bookDescription, bookPageCount, publisherName, authorName, price);
    }

    @Override
    public String toString()
    {
        return "BookViewDTO[" +
                "bookName=" + bookName + ", " +
                "bookIsbn=" + bookIsbn + ", " +
                "bookDescription=" + bookDescription + ", " +
                "bookPageCount=" + bookPageCount + ", " +
                "publisherName=" + publisherName + ", " +
                "authorName=" + authorName + ", " +
                "price=" + price + ']';
    }

}
