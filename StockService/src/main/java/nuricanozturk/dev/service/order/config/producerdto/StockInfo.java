package nuricanozturk.dev.service.order.config.producerdto;

import com.fasterxml.jackson.annotation.JsonProperty;
import nuricanozturk.dev.service.order.dto.StockStatus;

public record StockInfo(
        @JsonProperty("stock_status")
        StockStatus stockStatus)
{
}
