package nuricanozturk.dev.service.order.config.listenerdto;

import nuricanozturk.dev.service.order.dto.BookStatus;

import java.util.UUID;

public record BookInfo(
        UUID bookId,
        String bookName,
        UUID bookIsbn,
        double price,
        BookStatus bookStatus
)
{
}
