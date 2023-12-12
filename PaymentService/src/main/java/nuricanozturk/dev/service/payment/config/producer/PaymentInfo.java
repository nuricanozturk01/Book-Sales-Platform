package nuricanozturk.dev.service.payment.config.producer;

import nuricanozturk.dev.service.payment.dto.EPaymentStatus;

import java.util.UUID;

public record PaymentInfo(UUID userId, UUID bookId, String bookName, double cost, double availableBalance,
                          EPaymentStatus paymentStatus,
                          String message)
{
}
