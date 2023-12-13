package nuricanozturk.dev.service.payment.config.consumer.dto;

import java.util.UUID;

public record UserInfo(
        UUID userId,
        String username,
        double budget,
        UserOperationStatus operationStatus)
{
}
