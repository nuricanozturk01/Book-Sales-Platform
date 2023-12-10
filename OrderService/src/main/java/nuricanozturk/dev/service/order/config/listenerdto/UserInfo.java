package nuricanozturk.dev.service.order.config.listenerdto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.UUID;

public record UserInfo(
        UUID userId,
        String username,
        double budget,

        UserOperationStatus operationStatus)
{

}
