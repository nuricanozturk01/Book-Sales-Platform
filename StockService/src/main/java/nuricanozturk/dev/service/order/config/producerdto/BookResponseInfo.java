package nuricanozturk.dev.service.order.config.producerdto;

import nuricanozturk.dev.service.order.dto.BookStatus;

import java.util.UUID;

public record BookResponseInfo(UUID bookId, BookStatus bookStatus)
{
}
