package nuricanozturk.dev.service.order.config.producerdto;

import java.util.UUID;

public record OrderResponseInfo(UUID userId, UUID bookId, String message)
{
}
