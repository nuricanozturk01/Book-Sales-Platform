package nuricanozturk.dev.service.order.config.listenerdto;


import nuricanozturk.dev.service.order.dto.BookStatus;

import java.util.UUID;

public record OrderInfo(
        UUID bookId,
        String bookName,
        BookStatus bookStatus
)
{
}
