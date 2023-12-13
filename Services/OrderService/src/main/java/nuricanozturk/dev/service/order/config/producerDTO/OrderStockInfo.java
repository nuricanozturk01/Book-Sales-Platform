package nuricanozturk.dev.service.order.config.producerDTO;


import nuricanozturk.dev.service.order.config.listenerdto.BookStatus;

import java.util.UUID;

public record OrderStockInfo(UUID orderId, UUID userId, UUID bookId, String bookName, BookStatus bookStatus,
                             double price)
{
}
