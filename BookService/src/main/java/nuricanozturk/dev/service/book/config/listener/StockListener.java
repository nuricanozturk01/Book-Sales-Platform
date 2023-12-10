package nuricanozturk.dev.service.book.config.listener;

import nuricanozturk.dev.service.book.entity.BookStatus;

import java.util.UUID;

public record StockListener(
        UUID bookId,
        BookStatus stock
)
{
}
