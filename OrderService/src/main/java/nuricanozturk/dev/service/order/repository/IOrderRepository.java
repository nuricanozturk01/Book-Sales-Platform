package nuricanozturk.dev.service.order.repository;

import nuricanozturk.dev.service.order.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IOrderRepository extends CrudRepository<Order, UUID>
{
}
