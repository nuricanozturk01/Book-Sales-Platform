package nuricanozturk.dev.service.order.repository;

import nuricanozturk.dev.service.order.entity.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IStockRepository extends MongoRepository<Stock, String>
{
    Optional<Stock> findByBookId(UUID bookId);
}
