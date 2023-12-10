package nuricanozturk.dev.service.order.repository;

import nuricanozturk.dev.service.order.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IUserRepository extends CrudRepository<User, UUID>
{

}
