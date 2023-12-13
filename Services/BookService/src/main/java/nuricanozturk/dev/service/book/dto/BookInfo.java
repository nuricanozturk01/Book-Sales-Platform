package nuricanozturk.dev.service.book.dto;

import java.util.UUID;

public record BookInfo(UUID bookId, String bookName, UUID bookIsbn, double price, BookStatus bookStatus, int stock)
{
}
