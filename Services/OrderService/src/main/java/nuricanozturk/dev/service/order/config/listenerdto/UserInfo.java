package nuricanozturk.dev.service.order.config.listenerdto;

import java.util.UUID;

public record UserInfo(
        UUID userId,
        String username,
        double budget,

        UserOperationStatus operationStatus)
{

}
