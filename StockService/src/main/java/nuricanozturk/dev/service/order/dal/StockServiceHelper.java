package nuricanozturk.dev.service.order.dal;

import nuricanozturk.dev.service.order.repository.IStockRepository;
import org.springframework.stereotype.Component;

@Component
public class StockServiceHelper
{
    private final IStockRepository stockRepository;

    public StockServiceHelper(IStockRepository stockRepository)
    {
        this.stockRepository = stockRepository;
    }


}
