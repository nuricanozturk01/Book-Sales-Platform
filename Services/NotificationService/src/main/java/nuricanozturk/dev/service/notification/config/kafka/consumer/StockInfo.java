package nuricanozturk.dev.service.notification.config.kafka.consumer;


import java.util.UUID;

public record StockInfo(UUID userId, UUID bookId, String bookName, double price, BookStatus bookStatus, String message)
{
}
