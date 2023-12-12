package nuricanozturk.dev.service.order.config.producerdto;

import nuricanozturk.dev.service.order.dto.BookStatus;

import java.util.UUID;

public record StockInfo(UUID userId, UUID bookId, String bookName, double price, BookStatus bookStatus, String message)
{
}
