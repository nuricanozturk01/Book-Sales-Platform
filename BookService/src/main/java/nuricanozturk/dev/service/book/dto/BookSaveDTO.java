package nuricanozturk.dev.service.book.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BookSaveDTO(
        @JsonProperty("book_name")
        String bookName,
        @JsonProperty("book_description")
        String bookDescription,
        @JsonProperty("book_page_count")
        int bookPageCount,
        @JsonProperty("publisher_name")
        String publisherName,
        @JsonProperty("author_name")
        String authorName,
        double price,
        int stock)
{
}
