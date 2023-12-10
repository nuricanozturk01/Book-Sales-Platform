package nuricanozturk.dev.data.usermanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

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
