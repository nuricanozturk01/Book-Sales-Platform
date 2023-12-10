package nuricanozturk.dev.service.order.config.producerDTO;


import nuricanozturk.dev.service.order.dto.BookStatus;

import java.util.UUID;

public record OrderStockInfo(
        UUID bookId,
        String bookName,
        BookStatus bookStatus
)
{
}
