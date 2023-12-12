package nuricanozturk.dev.service.notification.config.kafka.consumer;


import java.util.UUID;

public record OrderStockInfo(
        UUID userId,
        UUID bookId,
        String bookName,
        BookStatus bookStatus,
        double price
)
{
}
