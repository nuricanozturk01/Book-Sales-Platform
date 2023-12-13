package nuricanozturk.dev.service.payment.repository;

import nuricanozturk.dev.service.payment.entity.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IPaymentRepository extends CrudRepository<Payment, UUID>
{
}
