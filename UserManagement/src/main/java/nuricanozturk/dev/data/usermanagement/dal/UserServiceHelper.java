package nuricanozturk.dev.data.usermanagement.dal;

import nuricanozturk.dev.data.usermanagement.entity.User;
import nuricanozturk.dev.data.usermanagement.repository.IUserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@Lazy
public class UserServiceHelper
{
    private final IUserRepository m_userRepository;

    public UserServiceHelper(IUserRepository userRepository)
    {
        m_userRepository = userRepository;
    }

    public Optional<User> findByUsername(String username)
    {
        return m_userRepository.findUserByUsername(username);
    }

    public User save(User user)
    {
        return m_userRepository.save(user);
    }

    public Optional<User> findById(UUID id)
    {
        return m_userRepository.findById(id);
    }

    public void deleteById(UUID id)
    {
        m_userRepository.deleteById(id);
    }

    public Iterable<User> findAll()
    {
        return m_userRepository.findAll();
    }

    public boolean existsById(UUID id)
    {
        return m_userRepository.existsById(id);
    }

    public long count()
    {
        return m_userRepository.count();
    }

    public void delete(User user)
    {
        m_userRepository.delete(user);
    }


}
