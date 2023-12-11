package nuricanozturk.dev.service.order.config.producerdto;

import java.util.UUID;

public record StockInfo(UUID userId, UUID bookId, String bookName, double price)
{
}
