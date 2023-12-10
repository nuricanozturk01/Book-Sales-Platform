package nuricanozturk.dev.service.order.config.listenerdto;

import com.fasterxml.jackson.annotation.JsonProperty;
import nuricanozturk.dev.service.order.dto.UserOperationStatus;

import java.util.UUID;

public record UserInfo(
        @JsonProperty("user_id")
        UUID userId,
        String username,
        double budget,
        @JsonProperty("operation_status")
        UserOperationStatus operationStatus)
{

}
