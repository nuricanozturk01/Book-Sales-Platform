package nuricanozturk.dev.service.notification.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "notification")
@Getter
@Setter
@NoArgsConstructor
public class Notification
{
    private String id;
    private UUID userId;
    private UUID bookId;
    private String bookName;
    private double cost;
    private double availableBalance;

    public Notification(UUID userId, UUID bookId, String bookName, double cost, double availableBalance)
    {
        this.userId = userId;
        this.bookId = bookId;
        this.bookName = bookName;
        this.cost = cost;
        this.availableBalance = availableBalance;
    }
}
