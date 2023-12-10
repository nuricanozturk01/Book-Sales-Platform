package nuricanozturk.dev.service.order.repository;

import nuricanozturk.dev.service.order.entity.Stock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IStockRepository extends CrudRepository<Stock, UUID>
{
    Optional<Stock> findByBookId(UUID bookId);
}
