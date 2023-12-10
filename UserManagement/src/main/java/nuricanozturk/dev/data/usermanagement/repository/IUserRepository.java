package nuricanozturk.dev.data.usermanagement.repository;

import nuricanozturk.dev.data.usermanagement.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUserRepository extends CrudRepository<User, UUID>
{
    Optional<User> findUserByUsername(String username);
}
