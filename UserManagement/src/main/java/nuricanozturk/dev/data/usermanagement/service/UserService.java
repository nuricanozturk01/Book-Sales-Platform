package nuricanozturk.dev.data.usermanagement.service;

import nuricanozturk.dev.data.usermanagement.config.UserAuthProvider;
import nuricanozturk.dev.data.usermanagement.config.kafka.UserKafkaProducer;
import nuricanozturk.dev.data.usermanagement.dal.UserServiceHelper;
import nuricanozturk.dev.data.usermanagement.dto.UserInfo;
import nuricanozturk.dev.data.usermanagement.dto.UserLoginDTO;
import nuricanozturk.dev.data.usermanagement.dto.UserOperationStatus;
import nuricanozturk.dev.data.usermanagement.dto.UserSaveDTO;
import nuricanozturk.dev.data.usermanagement.entity.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static callofproject.dev.library.exception.util.CopDataUtil.doForDataService;
import static callofproject.dev.service.jwt.JwtUtil.generateToken;

@Service
public class UserService
{
    private final UserServiceHelper m_userServiceHelper;
    private final PasswordEncoder m_passwordEncoder;
    private final UserKafkaProducer m_kafkaProducer;
    private final UserAuthProvider m_authenticationProvider;

    public UserService(UserServiceHelper userServiceHelper, PasswordEncoder passwordEncoder, UserKafkaProducer kafkaProducer, UserAuthProvider authenticationProvider)
    {
        m_userServiceHelper = userServiceHelper;
        m_passwordEncoder = passwordEncoder;
        m_kafkaProducer = kafkaProducer;
        m_authenticationProvider = authenticationProvider;
    }

    public String createUser(UserSaveDTO userSaveDTO)
    {
        var user = new User(userSaveDTO.name(), userSaveDTO.surname(), userSaveDTO.username(),
                m_passwordEncoder.encode(userSaveDTO.password()), userSaveDTO.budget(), userSaveDTO.gender());

        var savedUser = m_userServiceHelper.save(user);

        var userInfo = new UserInfo(savedUser.getUserId(), savedUser.getUsername(), savedUser.getBudget(), UserOperationStatus.CREATE);
        System.err.println(userInfo);
        m_kafkaProducer.sendUserInfo(userInfo);
        m_kafkaProducer.sendUserInfo("Created user: " + userInfo);

        return generateToken(user.getUsername());
    }

    public String login(UserLoginDTO userLoginDTO)
    {
        var authProvider = new UsernamePasswordAuthenticationToken(userLoginDTO.username(), userLoginDTO.password());

        doForDataService(() -> m_authenticationProvider.authenticate(authProvider), "Invalid username or password!");

        return generateToken(userLoginDTO.username());
    }
}
