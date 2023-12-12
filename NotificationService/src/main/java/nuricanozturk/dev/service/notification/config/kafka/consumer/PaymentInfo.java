package nuricanozturk.dev.service.notification.config.kafka.consumer;

import java.util.UUID;

public record PaymentInfo(UUID userId, UUID bookId, String bookName, double cost, double availableBalance)
{
}
