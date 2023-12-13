package nuricanozturk.dev.service.payment.config.consumer.dto;

import java.util.UUID;

public record StockInfo(UUID userId, UUID bookId, String bookName, double price, BookStatus bookStatus, String message)
{
}