package nuricanozturk.dev.service.order.dto;

public record ErrorResponse(
        boolean status,
        String message
)
{
}
