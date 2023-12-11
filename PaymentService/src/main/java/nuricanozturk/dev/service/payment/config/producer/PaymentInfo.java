package nuricanozturk.dev.service.payment.config.producer;

import java.util.UUID;

public record PaymentInfo(UUID userId, UUID bookId, String bookName, double cost, double availableBalance)
{
}
