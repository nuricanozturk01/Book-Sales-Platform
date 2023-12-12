package nuricanozturk.dev.service.notification.dto;

import nuricanozturk.dev.service.notification.config.kafka.consumer.EPaymentStatus;

import java.util.UUID;

public record NotificationSaveDTO(UUID userId, UUID bookId, String bookName,
                                  EPaymentStatus paymentStatus, double cost, double availableBalance,
                                  String message)
{
    @Override
    public String toString()
    {
        return "Payment Operation is " + paymentStatus + "\n" +
                "User Id: " + userId + "\n" +
                "Book Id: " + bookId + "\n" +
                "Book Name: " + bookName + "\n" +
                "Cost: " + cost + "\n" +
                "Available Balance: " + availableBalance + "\n" +
                "Message: " + message + "\n";
    }
}
