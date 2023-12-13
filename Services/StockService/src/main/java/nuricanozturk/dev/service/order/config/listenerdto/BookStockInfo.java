package nuricanozturk.dev.service.order.config.listenerdto;

import java.util.UUID;

public record BookStockInfo(UUID bookId, String bookName, UUID bookIsbn, double price, int stock)
{
}
