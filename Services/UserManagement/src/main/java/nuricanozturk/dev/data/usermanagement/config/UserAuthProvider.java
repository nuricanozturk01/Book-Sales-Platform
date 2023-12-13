package nuricanozturk.dev.data.usermanagement.config;

import nuricanozturk.dev.data.usermanagement.dal.UserServiceHelper;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserAuthProvider implements AuthenticationProvider
{
    private final UserServiceHelper m_serviceHelper;
    private final PasswordEncoder passwordEncoder;

    public UserAuthProvider(UserServiceHelper userServiceHelper, PasswordEncoder passwordEncoder)
    {
        m_serviceHelper = userServiceHelper;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * @param authentication the authentication request object.
     * @return Authentication
     * @throws AuthenticationException if the authentication fails
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        var username = authentication.getName();
        var pwd = authentication.getCredentials().toString();
        var user = m_serviceHelper.findByUsername(username);

        if (user.isPresent())
        {
            if (passwordEncoder.matches(pwd, user.get().getPassword()))
                return new UsernamePasswordAuthenticationToken(username, pwd, null);

            else throw new BadCredentialsException("Invalid password!");
        } else throw new BadCredentialsException("No user registered with this details!");
    }


    @Override
    public boolean supports(Class<?> authentication)
    {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}