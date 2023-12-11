package nuricanozturk.dev.service.book.config.listener;

import nuricanozturk.dev.service.book.dto.BookStatus;

import java.util.UUID;

public record BookResponseInfo(UUID bookId, BookStatus bookStatus)
{
}
