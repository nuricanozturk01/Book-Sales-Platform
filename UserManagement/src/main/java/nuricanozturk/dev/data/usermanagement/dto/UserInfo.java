package nuricanozturk.dev.data.usermanagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.UUID;

public record UserInfo(
        UUID userId,
        String username,
        double budget,
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        UserOperationStatus operationStatus)
{
}
