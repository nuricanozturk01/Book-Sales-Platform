package nuricanozturk.dev.service.book.config.listener;


import nuricanozturk.dev.service.book.dto.BookStatus;

import java.util.UUID;

public record StockInfo(UUID userId, UUID bookId, String bookName, double price, BookStatus bookStatus, String message)
{
}
