package nuricanozturk.dev.service.order.config.listenerdto;

import java.util.UUID;

public record StockInfo(UUID userId, UUID bookId, String message)
{
}
