package nuricanozturk.dev.service.notification.dto;

import java.util.UUID;

public record NotificationSaveDTO(UUID userId, UUID bookId, String bookName,
                                  PaymentStatus paymentStatus, double cost, Double availableBalance)
{
    @Override
    public String toString()
    {
        return "Payment Operation is " + paymentStatus + "\n" +
                "User Id: " + userId + "\n" +
                "Book Id: " + bookId + "\n" +
                "Book Name: " + bookName + "\n" +
                "Cost: " + cost + "\n" +
                "Available Balance: " + availableBalance + "\n";
    }
}
